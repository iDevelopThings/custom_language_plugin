package com.github.idevelopthings.arc.completion.data

import com.github.idevelopthings.arc.completion.processor.ResolverChainContext
import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.*
import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElement

enum class TypeKind {
		// Used for types like Int, String, etc.
		PRIMITIVE, OBJECT, ENUM, FUNCTION, VAR, MEMBER
}


interface TypeInfo {
		val kind: TypeKind

		var name: String
		var fields: List<TypeInfo>
		var functions: List<TypeInfo>

		// This is mainly used for functions
		// For example, a function can have a receiver, which binds it to the object
		// It can also be used when we're describing a var, it has a name and an associated type
		var type: TypeInfo?

		fun getLookupElement(): LookupElement?
		fun loadDeeper(ctx: ResolverChainContext)

		fun getDeclaration(): ArcBaseDeclaration? {
				return null
		}

		fun getDeclarationElement(): PsiElement? {
				return null
		}
}

@Suppress("MemberVisibilityCanBePrivate")
abstract class PrimitiveTypeInfo(override var name: String) : TypeInfo {
		override val kind: TypeKind = TypeKind.PRIMITIVE

		override var fields: List<TypeInfo> = emptyList()
		override var functions: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		companion object {
				val INT = IntTypeInfo()
				val STRING = StringTypeInfo()
				val BOOLEAN = BooleanTypeInfo()
				val NULL = NullTypeInfo()
				val ANY = AnyTypeInfo()

				fun isPrimitive(type: TypeInfo): Boolean {
						return type == INT || type == STRING || type == BOOLEAN || type == NULL || type == ANY
				}

				fun isPrimitive(type: ArcType): Boolean = isPrimitive(type.id.text)

				fun isPrimitive(typeName: String): Boolean {
						return when (typeName) {
								"int" -> true
								"string" -> true
								"bool" -> true
								"any" -> true
								else -> false
						}
				}

				fun toPrimitive(type: ArcType): PrimitiveTypeInfo? {
						return toPrimitive(type.id?.text!!)
				}

				fun toPrimitive(typeName: String): PrimitiveTypeInfo? {
						return when (typeName) {
								"int" -> INT
								"string" -> STRING
								"bool" -> BOOLEAN
								"any" -> ANY
								else -> null
						}
				}

		}

		class IntTypeInfo : PrimitiveTypeInfo("int")
		class StringTypeInfo : PrimitiveTypeInfo("string")
		class BooleanTypeInfo : PrimitiveTypeInfo("boolean")
		class NullTypeInfo : PrimitiveTypeInfo("null")
		class AnyTypeInfo : PrimitiveTypeInfo("any")

		override fun getLookupElement(): LookupElement? {
				return LookupElementBuilder.create(name)
		}

		override fun loadDeeper(ctx: ResolverChainContext) {
		}

		override fun toString(): String {
				return "PrimitiveTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

abstract class DeclarationTypeInfo(e: ArcBaseDeclaration) : TypeInfo {
		var baseDeclaration: ArcBaseDeclaration? = e

		override var functions: List<TypeInfo> = emptyList()

		override fun getLookupElement(): LookupElement? {
				return baseDeclaration?.getLookupElement()
		}

		override fun getDeclarationElement() = getDeclaration()

		override fun toString(): String {
				return "DeclarationTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

class ObjectTypeInfo(e: ArcObjectDeclaration) : DeclarationTypeInfo(e) {

		override val kind: TypeKind = TypeKind.OBJECT
		override var name: String = e.objectId.text
		override var fields: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		override fun getDeclaration() = baseDeclaration as ArcObjectDeclaration?

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (baseDeclaration == null) return
				if (baseDeclaration !is ArcObjectDeclaration) return

				loadFields(ctx)
				loadFunctions(ctx)
		}

		private fun loadFunctions(ctx: ResolverChainContext) {
				val funcDecls = getDeclaration()!!.getMethods()
				funcDecls.forEach {
						functions += ctx.beginLoad(it) {
								FunctionTypeInfo(it).apply {
										type = typeInfoFromNamedElement(it)
								}
						}
				}

				functions.forEach {
						ctx.loadNested { (it as DeclarationTypeInfo).loadDeeper(ctx) }
				}
		}

		private fun loadFields(ctx: ResolverChainContext) {
				for (fieldDecl in getDeclaration()!!.objectFieldDeclarationList) {
						val fieldType = fieldDecl.type!!

						fields += ctx.beginLoad(fieldType) {
								val field = MemberTypeInfo(fieldDecl).apply {
										if (PrimitiveTypeInfo.isPrimitive(fieldType.id.text)) {
												type = PrimitiveTypeInfo.toPrimitive(fieldType.id.text)
										} else {
												fieldType.resolve()?.let { type ->
														this.type = typeInfoFromNamedElement(type)
												}
										}
								}

								field
						}

				}

				fields.forEach {
						ctx.loadNested { (it as DeclarationTypeInfo).loadDeeper(ctx) }
				}
		}

		override fun toString(): String {
				return "ObjectTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

class EnumTypeInfo(e: ArcEnumDeclaration) : DeclarationTypeInfo(e) {
		override val kind: TypeKind = TypeKind.ENUM
		override var name: String = e.enumId.text
		override var fields: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		enum class EnumFieldKind {
				ENUM_LITERAL_VALUE,
				ENUM_VALUE_WITH_VALUES
		}

		override fun getDeclaration() = baseDeclaration as ArcEnumDeclaration?

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (baseDeclaration == null) return
				if (baseDeclaration !is ArcEnumDeclaration) return

				addFields(ctx)
		}

		private fun addFields(ctx: ResolverChainContext) {
				for (enumFieldDecl in getDeclaration()!!.enumFieldDeclarationList) {
						if (enumFieldDecl.isConstructorField()) {
								functions += ctx.beginLoad(enumFieldDecl) {
										EnumMemberTypeInfo(enumFieldDecl)
								}
						} else {
								fields += ctx.beginLoad(enumFieldDecl) {
										EnumMemberTypeInfo(enumFieldDecl)
								}
						}
				}

				ctx.loadNested {
						fields.forEach {
								(it as DeclarationTypeInfo).loadDeeper(ctx)
						}
				}
		}

		override fun toString(): String {
				return "EnumTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}

		class EnumMemberTypeInfo(e: ArcBaseDeclaration) : DeclarationTypeInfo(e) {
				override val kind: TypeKind = TypeKind.MEMBER
				override var name: String = e.name!!
				override var fields: List<TypeInfo> = emptyList()
				override var type: TypeInfo? = null

				var enumFieldKind: EnumFieldKind

				init {
						val eField = (e as ArcEnumFieldDeclaration)
						if (eField.isConstructorField()) {
								enumFieldKind = EnumFieldKind.ENUM_VALUE_WITH_VALUES
								eField.enumValueCtorArgList?.enumValueCtorArgList?.forEach {
										// it.id
								}


						} else {
								enumFieldKind = EnumFieldKind.ENUM_LITERAL_VALUE
								// type = PrimitiveTypeInfo
						}
				}

				override fun getDeclaration() = baseDeclaration as ArcEnumFieldDeclaration

				override fun loadDeeper(ctx: ResolverChainContext) {
						if (baseDeclaration == null) return

						if (enumFieldKind == EnumFieldKind.ENUM_LITERAL_VALUE) {

						} else {

						}

						/*

						ctx.beginLoad(baseDeclaration as PsiElement) {
								if (type == null) return@beginLoad this
								if (type !is DeclarationTypeInfo) return@beginLoad this

								this
						}

						ctx.loadNested {
								(type as DeclarationTypeInfo).loadDeeper(ctx)
						} */
				}

				override fun toString(): String {
						return "EnumMemberTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
				}
		}

}

class FunctionTypeInfo(e: ArcFuncDeclaration) : DeclarationTypeInfo(e) {
		override val kind: TypeKind = TypeKind.FUNCTION
		override var name: String = e.funcId?.text ?: "Unknown name... psi issue"
		override var fields: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		override fun getDeclaration() = baseDeclaration as ArcFuncDeclaration?

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (baseDeclaration == null) return
		}

		override fun toString(): String {
				return "FunctionTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

class VarTypeInfo(e: ArcVariableDeclaration) : DeclarationTypeInfo(e) {
		override val kind: TypeKind = TypeKind.VAR
		override var name: String = e.name!!
		override var fields: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		override fun getDeclaration() = baseDeclaration as ArcVariableDeclaration?

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (baseDeclaration == null) return
				if (type == null) return
				if (type !is DeclarationTypeInfo) return

				ctx.beginLoad(baseDeclaration as PsiElement) { this }
				ctx.loadNested { (type as DeclarationTypeInfo).loadDeeper(ctx) }
		}

		override fun toString(): String {
				return "VarTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

// Any implementations that have `ArcResolvable` interface can use this...
// For example, a func receiver name, func parameter... etc
class ResolvableVarTypeInfo(val originalDeclaration: ArcResolvable) : TypeInfo {
		override val kind: TypeKind = TypeKind.VAR
		override var name: String = originalDeclaration.getIdentifier().text
		override var fields: List<TypeInfo> = emptyList()
		override var functions: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		override fun getLookupElement(): LookupElement? {
				if (originalDeclaration is PsiElementWithLookup)
						return originalDeclaration.getLookupElement()

				return ArcPsiUtilImpl.getLookupElement(originalDeclaration)
		}

		override fun getDeclarationElement(): PsiElement? {
				return originalDeclaration
		}

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (type == null) return
				if (type !is DeclarationTypeInfo) return

				ctx.beginLoad(originalDeclaration as PsiElement) { this }
				ctx.loadNested {
						(type as DeclarationTypeInfo).loadDeeper(ctx)
				}
		}

		override fun toString(): String {
				return "ResolvableVarTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}

class MemberTypeInfo(e: ArcBaseDeclaration) : DeclarationTypeInfo(e) {
		override val kind: TypeKind = TypeKind.MEMBER
		override var name: String = e.name!!
		override var fields: List<TypeInfo> = emptyList()
		override var type: TypeInfo? = null

		override fun getDeclaration() = baseDeclaration

		override fun loadDeeper(ctx: ResolverChainContext) {
				if (baseDeclaration == null) return

				ctx.beginLoad(baseDeclaration as PsiElement) {
						if (type == null) return@beginLoad this
						if (type !is DeclarationTypeInfo) return@beginLoad this

						this
				}

				ctx.loadNested {
						if (type != null && type is DeclarationTypeInfo)
								(type as DeclarationTypeInfo).loadDeeper(ctx)
				}

		}

		override fun toString(): String {
				return "MemberTypeInfo(name='$name', fields=$fields, functions=$functions, type=$type)"
		}
}


fun typeInfoFromNamedElement(element: PsiElement): TypeInfo {
		return when (element) {
				is ArcVarId -> VarTypeInfo(element.parent!! as ArcVariableDeclaration)
				is ArcObjectId -> ObjectTypeInfo(element.parent!! as ArcObjectDeclaration)
				is ArcEnumId -> EnumTypeInfo(element.parent!! as ArcEnumDeclaration)
				is ArcFuncId -> FunctionTypeInfo(element.parent!! as ArcFuncDeclaration)

				is ArcVariableDeclaration -> VarTypeInfo(element)
				is ArcObjectDeclaration -> ObjectTypeInfo(element)
				is ArcEnumDeclaration -> EnumTypeInfo(element)
				is ArcFuncDeclaration -> FunctionTypeInfo(element)

				else -> {
						if (PrimitiveTypeInfo.isPrimitive(element.text)) {
								return PrimitiveTypeInfo.toPrimitive(element.text)!!
						}

						logger<TypeInfo>().warn("No type info converter for ${element.javaClass.name}")

						throw Exception("Unknown type(${element}): ${element.text}")
				}
		}
}
