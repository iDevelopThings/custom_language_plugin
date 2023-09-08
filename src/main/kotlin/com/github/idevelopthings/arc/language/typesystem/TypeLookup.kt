package com.github.idevelopthings.arc.language.typesystem

import com.github.idevelopthings.arc.psi.*
import com.intellij.psi.PsiElement

class TypeLookup {

		companion object {

				/**
				 * This should only be used to find the correct place to look for our reference type element
				 * For example, with variable declarations;
				 * `var x SomeTypeName;`
				 * `var x = SomeTypeName{}`
				 * We need to check for the type after `x`, if that doesn't exist, we look
				 * at the value, and try to extract the type name/element for that
				 */
				fun getTypeElement(element: PsiElement, allowTypeRef: Boolean = false): ArcType? {
						when (element) {

								is ArcVariableDeclaration -> {
										val type = element.type
										if (type is ArcTypeRef) {
												if (allowTypeRef) return type

												// TypeRef would contain `[]` for arrays, `?` for option etc
												return type.type
										}
										if (type is ArcType) {
												return type
										}
										if (element.expression == null) {
												return null
										}

										return getExpressionType(element.expression!!)
								}

								is ArcFuncReceiverDeclaration -> return element.type
								is ArcFuncReceiverName -> {
										val receiver = element.parent as ArcFuncReceiverDeclaration

										return receiver.type
								}
						}

						return null
				}

				private fun getExpressionType(expr: ArcExpression): ArcType? {
						when (expr) {
								is ArcObjectInstantiateExpr -> {
										return expr.type
								}
						}

						return null
				}

				/*fun getDeclaration(expr: ArcExpression?): ArcNamedElement? {
						var currentElement: PsiElement? = expr
						var currentType: ArcNamedElement? = null

						if (expr is ArcNamedElement) {
								currentType = expr
						}

						thisLogger().warn("getDecl")

						while (currentElement != null) {
								currentType = if (currentType == null) {
										thisLogger().warn("getDecl:currType == null")
										// Resolve our initial type, which we can do via `reference.resolve()`
										// In most cases, this would be the var declaration
										val farLhsVar = PsiTreeUtil.findChildOfType(currentElement, ArcRefExpr::class.java)
										val farLhsDecl = farLhsVar?.reference?.resolve() as ArcNamedElement?

										val varType = getVariableType(farLhsDecl?.parent as ArcVariableDeclaration)
										varType?.reference?.resolve() as ArcNamedElement
								} else {
										val refId = currentElement.firstChild.firstChild.text
										thisLogger().warn("getDecl:findChildDecl")
										currentType.findChildDeclaration(refId)
								}

								if (currentType == null) {
										thisLogger().warn("getDecl:break")
										break
								}

								thisLogger().warn("getDecl:getParentRef")
								// Traverse upwards to the next reference expression
								currentElement = PsiTreeUtil.getParentOfType(currentElement, ArcRefExprImpl::class.java)
						}

						thisLogger().warn("getDecl:ret")
						return null
				}*/

				fun <T> getTypeFromDeclaration(decl: PsiElement): T? {
						return decl.parent as T?
				}


		}

}
