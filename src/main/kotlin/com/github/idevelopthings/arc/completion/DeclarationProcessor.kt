package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.findParentOfType

class DeclarationProcessor(
		private val reference: PsiElement,
) : PsiScopeProcessor {

		var resolved: PsiElementResolveResult? = null

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				when (reference) {
						is ArcMemberAccessExpr -> {
								return executeMemberAccessExpr(reference, element, state)
						}

						is ArcObjectFieldKey -> {
								return executeObjectFieldKey(reference, element, state)
						}

						is ArcType -> {
								return executeType(reference, element, state)
						}

						is ArcValueExpr -> {
								return executeVarId(reference, element, state)
						}
				}

				return true
		}


		private fun executeMemberAccessExpr(reference: ArcMemberAccessExpr, element: PsiElement, state: ResolveState): Boolean {

				if (reference.isCallExpression) {
						return executeMemberCallExpr(reference, element, state)
				}

				val resolvedDeclaration = ArcPsiUtil.resolveMemberAccessDeclaration(
						reference,
						ArcPsiUtil.ResolutionKind.FIELD
				)

				if (resolvedDeclaration != null) {
						when (resolvedDeclaration) {
								is ArcObjectDeclaration -> {
										resolved = PsiElementResolveResult(resolvedDeclaration.objectId)
										return false
								}

								is ArcObjectFieldDeclaration -> {
										resolved = PsiElementResolveResult(resolvedDeclaration.objectFieldKey)
										return false
								}
						}
				}


				return true


				var lhsTypeNode: ArcType? = null
				var lhsDeclaration: ArcObjectDeclaration? = null

				do {
						lhsTypeNode = resolveMemberAccessType(reference.lhs)
						lhsDeclaration = (lhsTypeNode?.reference?.resolve()?.parent as ArcObjectDeclaration?)

						if (lhsDeclaration != null) {
								val member = lhsDeclaration.getMember(reference.getRHS()?.text!!)
								if (member != null) {
										resolved = PsiElementResolveResult(member)
										return false
								}
						}

				} while (lhsTypeNode != null && lhsDeclaration != null)


				thisLogger().warn("Not a call expression, it's ${reference.text}")

				return true
		}

		private fun executeMemberCallExpr(reference: ArcMemberAccessExpr, element: PsiElement, state: ResolveState): Boolean {
				if (element !is ArcFuncDeclaration) {
						return true
				}


				// Ensure our function name matches
				// func (r Type) funcName() Type
				// Then ensure our receiver type matches
				if (element.getName() == reference.getRHS()?.text) {

						val receiver = element.getFuncReceiverDeclaration()
						if (receiver == null) {
								resolved = PsiElementResolveResult(element.funcId!!)
								return false
						}

						val resolvedDeclaration = ArcPsiUtil.resolveMemberAccessDeclaration(
								reference,
								ArcPsiUtil.ResolutionKind.TYPE_DECLARATION
						)

						if (resolvedDeclaration != null && resolvedDeclaration is ArcObjectDeclaration) {
								if (resolvedDeclaration.name == receiver.type.name) {
										resolved = PsiElementResolveResult(element.funcId!!)
										return false
								}
						}

						return true
				}

				return true
		}

		private fun resolveMemberAccessType(lhs: PsiElement?): ArcType? {
				// Essentially we need to keep resolving the lhs until we hit a var id
				// Then we can resolve the type of the variable declaration
				if (lhs == null) {
						return null
				}


				var current: PsiElement? = lhs

				do {
						val resolvedValue = current?.reference?.resolve()

						when (resolvedValue) {
								is ArcVarId -> {
										return (resolvedValue.parent as ArcVariableDeclaration).type
								}
						}

						current = resolvedValue

				} while (current != null)

				return null
		}

		private fun executeVarId(ref: PsiElement, element: PsiElement, state: ResolveState): Boolean {
				if (element is ArcStatement) {
						element.variableDeclaration?.let {
								if (it.varId.text == ref.text) {
										resolved = PsiElementResolveResult(it.varId)
										return false
								}
						}
				}

				if (element is ArcFuncReceiverDeclaration) {
						element.funcReceiverName.let {
								if (it.id.text == ref.text) {
										resolved = PsiElementResolveResult(it.id)
										return false
								}
						}
				}

				return true
		}

		private var objectInstExpr: ArcObjectInstantiateExpr? = null

		private fun executeObjectFieldKey(reference: ArcObjectFieldKey, element: PsiElement, state: ResolveState): Boolean {
				if (element !is ArcObjectDeclaration) {
						return true
				}

				if (objectInstExpr == null) {
						objectInstExpr = reference.findParentOfType<ArcObjectInstantiateExpr>()
				}

				// If we have a field reference set, and we're looking up a ArcType
				// Then we're trying to resolve the declaration of the field on the object declaration

				if (objectInstExpr is ArcObjectInstantiateExpr) {
						if (objectInstExpr?.type?.text != element.objectId.id.text) {
								return true
						}

						val field = element.getMember(reference.id.text)
						if (field != null) {
								resolved = PsiElementResolveResult(field.objectFieldKey)
								return false
						}
				}

				return true
		}

		private fun executeType(ref: ArcType, element: PsiElement, state: ResolveState): Boolean {
				if (element !is ArcObjectDeclaration) {
						return true
				}

				if (element.objectId.text == ref.text) {
						resolved = PsiElementResolveResult(element.objectId)
						return false
				}

				return true
		}

}
