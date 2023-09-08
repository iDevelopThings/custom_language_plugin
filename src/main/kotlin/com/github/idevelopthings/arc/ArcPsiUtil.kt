package com.github.idevelopthings.arc

import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.github.idevelopthings.arc.psi.*
import com.github.weisj.jsvg.o
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.RecursionManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.*


object ArcPsiUtil {

		enum class ResolutionKind {
				TYPE_DECLARATION,
				FIELD,
				FUNCTION,
		}

		/*

		fun resolveMemberAccessDeclaration(expr: PsiElement?, kind: ResolutionKind): PsiElement? {
				when (expr) {

						is ArcSimpleRefExpr -> {
								return resolveVarReferenceDeclaration(expr)
						}

						is ArcType, is ArcValueExpr -> {
								return resolveVarReferenceDeclaration(expr)
						}

						is ArcRefExpr -> {

								// when we're resolving a member access expr, `a.b.c`, this would be hit when
								// we hit `a` since it has no child "expr"
								val lhs = resolveMemberAccessDeclaration(expr.expression, kind)

								when (lhs) {
										is ArcObjectDeclaration -> {
												if (kind == ResolutionKind.TYPE_DECLARATION)
														return lhs

												return lhs
										}

										is ArcObjectFieldDeclaration -> {
												if (expr.expression?.text == lhs.objectFieldKey.text) {
														return lhs
												}

												val fieldType = resolveMemberAccessDeclaration(lhs.type, kind)
												if (fieldType != null) {
														if (fieldType is ArcObjectDeclaration) {
																val member = fieldType.getMember(expr.id.text!!)
																if (member != null) {

																		if (kind == ResolutionKind.FIELD)
																				return member

																		return null
																}
														}
												}
										}

										is ArcObjectId -> {
												val obj = (lhs.parent as ArcObjectDeclaration)
												val member = obj.getMember(expr.id.text!!)
												if (member != null) {

														if (kind == ResolutionKind.FIELD)
																return member

														val nested = resolveMemberAccessDeclaration(member, kind)

														if (nested != null)
																return nested
												}

												if (expr.parent is ArcCallExpr && kind == ResolutionKind.TYPE_DECLARATION) {
														return obj
												}

												return null
										}

												*/
/* is ArcEnumDeclaration -> {
												val enumDecl = lhs as ArcEnumDeclaration

												for (it in enumDecl.enumValueDeclarationList) {
														if (expr.parent is ArcCallExpr) {
																if (it.enumValueCtor != null) {
																		if (it.enumValueCtor?.id?.text == expr.id.text) {
																				return it.enumValueCtor
																		}
																}
																continue
														}
														if (it.enumValueAssigned?.id?.text == expr.id.text) {
																return it.enumValueAssigned
														}
												}

												return null
										} */		/*


										else -> {
												return lhs
										}
								}
						}

						is ArcObjectFieldDeclaration -> {
								return resolveMemberAccessDeclaration(expr.type, kind)
						}


				}

				return expr
		}

		private fun resolveVarReferenceDeclaration(expr: PsiElement): PsiElement? {
				val resolved = expr.reference?.resolve()

				if (resolved is ArcNamedElement) {
						val decl = resolved.getDeclaringElement()

						when (resolved) {
								is ArcVarId -> {
										return TypeLookup.getTypeElement(decl as ArcVariableDeclaration)?.reference?.resolve()
								}

								is ArcObjectId -> return decl
								is ArcEnumId -> return decl
						}
				}

				if (resolved?.elementType == ArcTypes.FUNC_RECEIVER_NAME) {
						resolved?.findParentOfType<ArcFuncReceiverDeclaration>()?.let {
								val objTypeId = it.type.reference?.resolve()
								return objTypeId
						}
				}

				if (resolved is ArcEnumDeclaration) {
						return resolved
				}

				if (resolved is ArcObjectDeclaration) {
						return resolved
				}

				return null
		}
		*/


		/*fun findStaticFunction(typeName: String, functionName: String): ArcFuncDeclaration? {
				val located = ArcBuiltinsProvider.findFunction(typeName, functionName)
				if (located != null) {
						return located
				}

				return null
		}*/

		/* fun lookupFunction(access: ArcCallExpr): Boolean {
//						val lhs = access.refExpr.expression
//						val rhs = access.refExpr.id

				// If we have `::` we're doing static function call
				// Type -> Func
				if (access.refExpr.isStatic()) {
						return access.isBuiltin
				}


				val declaration = access.refExpr.reference?.resolve()
				if (declaration != null) {
						if (declaration is ArcFuncId) {
								return true
						}
				}

				return false
		} */

		/**
		 * Example cached value usage:
		 *
		 * RecursionManager.doPreventingRecursion(psiElement, true, Computable {
		 * 		CachedValuesManager.getCachedValue(psiElement) {
		 * 				CachedValueProvider.Result.create(somePsiElementResult, PsiModificationTracker.MODIFICATION_COUNT)
		 * 		}
		 * })
		 */

		fun findDeepestIdElement(psiElement: PsiElement): PsiElement? {
				// Check if the current element is an ID.
				if (psiElement.node.elementType == ArcTypes.ID) {
						return psiElement
				}

				// Traverse the children looking for the deepest ID.
				var deepestId: PsiElement? = null
				for (child in psiElement.children) {
						val childId = findDeepestIdElement(child)
						if (childId != null) {
								deepestId = childId
						}
				}

				return deepestId
		}


		fun getOuterScopeBlock(el: PsiElement): ArcBlockBody? {
				return when (el) {
						is ArcBlockBody -> el
						is ArcFuncDeclaration -> el.blockBody

						is ArcStatement -> {
								val parent = el.parent
								if (parent is ArcBlockBody) {
										return parent
								}
								null
						}

						is ArcDeleteStatement,
						is ArcExpression,
						is ArcForLoopStatement,
						is ArcIfStatement,
						is ArcReturnStatement,
						is ArcVariableDeclaration
						-> {
								val parent = PsiTreeUtil.findFirstParent(el, true) { it is ArcBlockBody } as ArcBlockBody?

								if (parent != null) {
										return parent
								}
								null
						}

						else -> {

								val p = PsiTreeUtil.findFirstParent(el, true) {
										it is ArcBlockBody
								} as ArcBlockBody?
								if (p != null) {
										return p
								}


								null
						}
				}
		}

		fun getMemberAccessSegments(el: ArcRefExpr): MutableList<ArcRefExpr> {
				val segments = mutableListOf<ArcRefExpr>()

				var current = el
				while (current.expression != null) {
						segments.add(current)
						current = current.expression as ArcRefExpr
				}

				segments.add(current)

				segments.reverse()

				return segments
		}

}

