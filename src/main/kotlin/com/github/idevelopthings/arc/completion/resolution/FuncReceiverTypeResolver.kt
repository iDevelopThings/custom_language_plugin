package com.github.idevelopthings.arc.completion.resolution

import com.github.idevelopthings.arc.completion.data.*
import com.github.idevelopthings.arc.completion.references.ArcTypeReference
import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.github.idevelopthings.arc.psi.ArcFuncReceiverDeclaration
import com.github.idevelopthings.arc.psi.ArcFuncReceiverName
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.github.idevelopthings.arc.psi.ArcVariableDeclaration
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState

class FuncReceiverTypeResolver : TypeResolverBase() {

		override fun canAccept(element: PsiElement): Boolean {
				return element is ArcFuncReceiverName
		}

		override fun resolveType(element: PsiElement): TypeInfo? {
				if (element !is ArcFuncReceiverName) return null
				// if (element.id == null) return null

				// TODO: We can have static functions that don't have a receiver name
				// For ex:
				// Regular: `func (u User) Name()`
				// Static:  `func (User) Name()`

				val receiver = element.parent as ArcFuncReceiverDeclaration
				val receiverType = receiver.type

				var varType: TypeInfo? = null

				if (PrimitiveTypeInfo.isPrimitive(receiverType)) {
						varType = PrimitiveTypeInfo.toPrimitive(receiverType)
				} else {
						val state = ResolveState.initial().put(ArcTypeReference.RESOLVE_TYPE_BASE_DECLARATION_ONLY_KEY, true)
						val resolvedTypeReference = receiverType.resolve(state)
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
