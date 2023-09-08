package com.github.idevelopthings.arc.completion.resolution

import com.github.idevelopthings.arc.completion.data.PrimitiveTypeInfo
import com.github.idevelopthings.arc.completion.data.ResolvableVarTypeInfo
import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.github.idevelopthings.arc.completion.data.typeInfoFromNamedElement
import com.github.idevelopthings.arc.completion.references.ArcTypeReference
import com.github.idevelopthings.arc.psi.ArcArgumentDeclaration
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState

class ArgumentTypeResolver : TypeResolverBase() {

		override fun canAccept(element: PsiElement): Boolean {
				return element is ArcArgumentDeclaration
		}

		override fun resolveType(element: PsiElement): TypeInfo? {
				if (element !is ArcArgumentDeclaration) {
						return null
				}

				val varTypeRef = element.type
				var varType: TypeInfo? = null

				if (PrimitiveTypeInfo.isPrimitive(varTypeRef)) {
						varType = PrimitiveTypeInfo.toPrimitive(varTypeRef)
				} else {
						val state = ResolveState.initial().put(ArcTypeReference.RESOLVE_TYPE_BASE_DECLARATION_ONLY_KEY, true)

						val resolvedTypeReference = varTypeRef.resolve(state)
						if (resolvedTypeReference == null) {
								thisLogger().warn("resolveType:resolvedTypeReference == null; element: $element")
								return null
						}
						if (resolvedTypeReference is ArcNamedElement) {
								varType = typeInfoFromNamedElement(resolvedTypeReference)
						}
				}

				return ResolvableVarTypeInfo(element).apply {
						type = varType
				}
		}

}
