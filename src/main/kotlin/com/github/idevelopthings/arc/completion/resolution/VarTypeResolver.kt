package com.github.idevelopthings.arc.completion.resolution

import com.github.idevelopthings.arc.completion.data.PrimitiveTypeInfo
import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.github.idevelopthings.arc.completion.data.VarTypeInfo
import com.github.idevelopthings.arc.completion.data.typeInfoFromNamedElement
import com.github.idevelopthings.arc.completion.references.ArcTypeReference
import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.github.idevelopthings.arc.psi.ArcFuncReceiverName
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.github.idevelopthings.arc.psi.ArcVariableDeclaration
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState

class VarTypeResolver : TypeResolverBase() {

		override fun canAccept(element: PsiElement): Boolean {
				return element is ArcVariableDeclaration
		}

		override fun resolveType(element: PsiElement): TypeInfo? {
				val varTypeRef = TypeLookup.getTypeElement(element as ArcVariableDeclaration)
				if (varTypeRef == null) {
						thisLogger().warn("resolveType:varTypeRef == null; element: $element")
						return null
				}

				var varType: TypeInfo? = null

				if (PrimitiveTypeInfo.isPrimitive(varTypeRef)) {
						varType = PrimitiveTypeInfo.toPrimitive(varTypeRef)
				} else {

						// val state = ResolveState.initial().put(ArcTypeReference.RESOLVE_TYPE_BASE_DECLARATION_ONLY_KEY, true)

						val resolvedTypeReference = varTypeRef.reference?.resolve()
						if (resolvedTypeReference == null) {
								thisLogger().warn("resolveType:resolvedTypeReference == null; element: $element")
								return null
						}


						if (resolvedTypeReference is ArcNamedElement) {
								varType = typeInfoFromNamedElement(resolvedTypeReference)
						}
				}

				return VarTypeInfo(element).apply {
						type = varType
				}
		}

}
