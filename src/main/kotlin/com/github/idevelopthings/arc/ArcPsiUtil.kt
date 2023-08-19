package com.github.idevelopthings.arc

import com.github.idevelopthings.arc.psi.*
import com.intellij.psi.PsiElement


class ArcPsiUtil {

		enum class ResolutionKind {
				TYPE_DECLARATION,
				FIELD,
				FUNCTION,
		}

		companion object {

				fun resolveMemberAccessDeclaration(expr: PsiElement?, kind: ResolutionKind): PsiElement? {

						when (expr) {

								is ArcMemberAccessExpr -> {
										val lhs = resolveMemberAccessDeclaration(expr.lhs, kind)

										when (lhs) {
												is ArcObjectDeclaration -> {
														if (kind == ResolutionKind.TYPE_DECLARATION)
																return lhs

														return lhs
												}

												is ArcObjectFieldDeclaration -> {
														if(expr.rhs?.text == lhs.objectFieldKey.text) {
																return lhs
														}

														val fieldType = resolveMemberAccessDeclaration(lhs.type, kind)
														if(fieldType != null) {
																if(fieldType is ArcObjectDeclaration) {
																		val member = fieldType.getMember(expr.getRHS()?.text!!)
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
														val member = obj.getMember(expr.getRHS()?.text!!)
														if (member != null) {

																if (kind == ResolutionKind.FIELD)
																		return member

																val nested = resolveMemberAccessDeclaration(member, kind)

																if (nested != null)
																		return nested
														}

														if (expr.isCallExpression && kind == ResolutionKind.TYPE_DECLARATION) {
																return obj
														}

														return null
												}

												else -> {
														return lhs
												}
										}
								}

								is ArcObjectFieldDeclaration -> {
										return resolveMemberAccessDeclaration(expr.type, kind)
								}

								is ArcType, is ArcValueExpr -> {
										val resolved = expr.reference?.resolve()
										when (resolved) {
												is ArcVarId -> {
														return (resolved.parent as ArcVariableDeclaration).type?.reference?.resolve()
												}
												is ArcObjectId -> {
														return (resolved.parent as ArcObjectDeclaration)
												}
										}
										if (resolved is ArcObjectDeclaration) {
												return resolved
										}

										return null
								}
						}

						/*if (expr is ArcType && kind == ResolutionKind.TYPE_DECLARATION) {
								val resolved = expr.reference?.resolve()
								if (resolved != null) {
										return (resolved as ArcObjectId).parent
								}
								return null
						}*/

						return expr
						/*


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
						*/

				}

				/*	fun resolveMemberAccessType(element: PsiElement?) {
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

					}*/


		}
}

