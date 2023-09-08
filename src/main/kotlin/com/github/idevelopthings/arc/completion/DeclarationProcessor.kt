package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.findParentOfType

/* class DeclarationProcessor(
		private var reference: PsiElement,
) : PsiScopeProcessor {

		var resolved: PsiElementResolveResult? = null

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				if (element == reference) {
						return true
				}

				return resolveReference(element, state, reference)
		}

		private fun resolveReference(element: PsiElement, state: ResolveState, reference: PsiElement): Boolean {

//				thisLogger().warn("resolveReference: element is ${reference.javaClass.name} ${reference.text}")

				when (reference) {
						is ArcCallExpr -> {
								return executeCallExpr(reference, element, state)
						}

						is ArcObjectFieldKey -> {
								return executeObjectFieldKey(reference, element, state)
						}

						is ArcType -> {
								return executeType(reference, element, state)
						}

						is ArcSimpleRefExpr -> {
//								if (reference.parent is ArcCallExpr) {
//										this.reference = reference.parent as ArcCallExpr
//										return resolveReference(element, state, this.reference)
//								}
								return executeVarId(reference, element, state)
						}

						is ArcRefExpr -> {
//								if (reference.parent is ArcCallExpr) {
//										this.reference = reference.parent as ArcCallExpr
//										return resolveReference(element, state, this.reference)
//								}
								return executeMemberAccessExpr(reference, element, state)
						}

						is ArcValueExpr -> {
								return executeVarId(reference, element, state)
						}
				}

				return true
		}

		private fun executeVarMemberAccessExpr(reference: ArcRefExpr, element: PsiElement, state: ResolveState): Boolean {
//				if (element !is ArcObjectFieldDeclaration) {
//						return true
//				}

				val refElement = reference.expression?.reference?.resolve()
				if (refElement == null) {
						thisLogger().warn("executeVarMemberAccessExpr: refElement is null for ${reference.text}")
						return true
				}

				when (refElement) {
						is ArcVarId -> {
								 *//*val varDecl = (refElement.parent as ArcVariableDeclaration?)
								if (varDecl == null) {
										thisLogger().warn("executeVarMemberAccessExpr: varDecl is null for ${reference.text}")
										return true
								}

								val varType = TypeLookup.getVariableType(varDecl)
								if (varType == null) {
										thisLogger().warn("executeVarMemberAccessExpr: varType is null for ${reference.text}")
										return true
								}*//*


								 *//*val typeDecl = varType.reference?.resolve()
								if (typeDecl == null) {
										thisLogger().warn("executeVarMemberAccessExpr: typeDecl is null for ${reference.text}")
										return true
								}

								if (typeDecl is ArcObjectId) {
										val objDecl = (typeDecl.parent as ArcObjectDeclaration)
										val member = objDecl.getMember(reference.id.text)
										if (member == null) {
												thisLogger().warn("executeVarMemberAccessExpr: member is null for ${reference.text}")
												return true
										}

										resolved = PsiElementResolveResult(member)
										return false
								} else {
										thisLogger().warn("executeVarMemberAccessExpr: typeDecl is not ArcObjectDeclaration for ${reference.text}, it is: ${typeDecl.javaClass}")
								}*//*
						}

						 *//*is ArcObjectFieldDeclaration -> {
								resolved = PsiElementResolveResult(refElement.type)
								return false
						}*//*

						else -> {
								thisLogger().warn("executeVarMemberAccessExpr: refElement is not ArcVarId for ${reference.text}, it is: ${refElement.javaClass}")
						}
				}


				return true
		}


		private fun executeMemberAccessExpr(reference: ArcRefExpr, element: PsiElement, state: ResolveState): Boolean {
				if (!reference.isMemberAccess() && reference.parent is ArcCallExpr) {
						if (reference.isStatic()) {
								val resolvedDeclaration = ArcPsiUtil.resolveMemberAccessDeclaration(
										reference,
										ArcPsiUtil.ResolutionKind.FIELD
								)
								if (resolvedDeclaration != null) {
										resolved = PsiElementResolveResult(resolvedDeclaration)
										return false
								}
						}
						return executeMemberCallExpr(reference.parent as ArcCallExpr, element, state)
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

								// is ArcEnumValueAssigned, is ArcEnumValueCtor -> {
								// 		resolved = PsiElementResolveResult(resolvedDeclaration)
								// 		return false
								// }
						}
				}

				return true
		}

		private fun executeMemberCallExpr(reference: ArcCallExpr, element: PsiElement, state: ResolveState): Boolean {
				// if (element is ArcEnumValueCtor) {
				// 		if (element.id.text == reference.refExpr.id.text) {
				// 				resolved = PsiElementResolveResult(element)
				// 				return false
				// 		}
				// }


				if (element is ArcFuncDeclaration) {
						// Ensure our function name matches
						// func (r Type) funcName() Type
						// Then ensure our receiver type matches
						if (element.getName() == reference.refExpr.id.text) {
								val receiver = element.getFuncReceiverDeclaration()
								if (receiver == null) {
										resolved = PsiElementResolveResult(element.funcId!!)
										return false
								}

								val resolvedDeclaration = ArcPsiUtil.resolveMemberAccessDeclaration(
										reference.refExpr,
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
				}


				return true
		}

		private fun executeCallExpr(reference: ArcCallExpr, element: PsiElement, state: ResolveState): Boolean {
				// if (element is ArcEnumValueCtor) {
				// 		if (element.id.text == reference.refExpr.id.text) {
				// 				resolved = PsiElementResolveResult(element)
				// 				return false
				// 		}
				// }

				if (element !is ArcFuncDeclaration) {
						return true
				}

				if (element.funcReceiverDeclaration != null) {
						if (reference?.refExpr?.id?.text == element.funcId?.id?.text) {
								resolved = PsiElementResolveResult(element.funcId!!)
								return false
						}
				}

				if (element.funcId?.id?.text == reference.refExpr.id.text) {
						resolved = PsiElementResolveResult(element.funcId!!)
						return false
				}

				return true
		}

		 *//*
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
		*//*

		private fun executeVarId(ref: PsiElement, element: PsiElement, state: ResolveState): Boolean {
				if (element is ArcStatement) {
						element.variableDeclaration?.let {
								if (it.varId.text == ref.text) {
										resolved = PsiElementResolveResult(it.varId)
										return false
								}
						}
				}

				if (element is ArcFuncReceiverName) {
						if (element.id.text == ref.text) {
								resolved = PsiElementResolveResult(element)
								return false
						}
				}

				if (element is ArcFuncReceiverDeclaration) {
						element.funcReceiverName.let {
								if (it?.id?.text == ref.text) {
										resolved = PsiElementResolveResult(it!!)
										return false
								}
						}
				}

				if (element is ArcEnumDeclaration) {
						if (reference is ArcRefExpr) {
								if ((reference?.parent as ArcRefExpr).isStatic()) {
										if (element.enumId.id.text == (reference as ArcRefExpr).id.text) {
												resolved = PsiElementResolveResult(element)
												return false
										}
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

} */
