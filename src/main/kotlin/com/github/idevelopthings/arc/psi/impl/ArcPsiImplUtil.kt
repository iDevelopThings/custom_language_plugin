package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.language.presentation.ArgumentDeclarationListPresentation
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.startOffset
import com.github.idevelopthings.arc.psi.*
import com.intellij.psi.PsiReference
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.findParentOfType


class ArcPsiImplUtil {
		companion object {

				@JvmStatic
				fun processDeclarations(argDeclList: ArcArgumentDeclarationList, processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
						argDeclList.argumentDeclarationList.forEach {
								if (!processor.execute(it, state)) {
										return false
								}
						}

						return true
				}

				@JvmStatic
				fun getPresentation(argumentList: ArcArgumentDeclarationList): ArgumentDeclarationListPresentation {
						return ArgumentDeclarationListPresentation(argumentList)
				}

				fun getName(element: ASTWrapperPsiElement): String {
						return when (element) {
								is ArcTypeImpl -> element.node.findChildByType(ArcTypes.ID)?.let {
										return it.text
								} ?: ""

								is ArcObjectIdImpl -> element.node.findChildByType(ArcTypes.ID)?.let {
										return it.text
								} ?: ""

								is ArcBaseExpressionElementImpl -> element.node.findChildByType(ArcTypes.ID)?.let {
										return it.text
								} ?: ""

								is ArcVarIdImpl -> element.node.findChildByType(ArcTypes.ID)?.let {
										return it.text
								} ?: ""

								else -> {
										thisLogger().warn("getName: unknown element type: ${element.javaClass}")
										""
								}
						}
				}

				fun getNameIdentifier(element: ASTWrapperPsiElement): PsiElement? {
						return when (element) {
								is ArcTypeImpl,
								is ArcObjectIdImpl,
								is ArcBaseExpressionElementImpl,
								is ArcVarIdImpl,
								is ArcFuncId,
								-> element.node.findChildByType(ArcTypes.ID)?.let {
										return it.psi
								}


								else -> {
										thisLogger().warn("getNameIdentifier: unknown element type: ${element.javaClass}")
										null
								}
						}
				}


				fun setName(element: ASTWrapperPsiElement, name: String): PsiElement {
						return when (element) {
								is ArcNamedElementImpl -> {
										val idNode = element.node.findChildByType(ArcTypes.ID)
										if (idNode != null) {
												val newIdNode = ArcElementFactory.createIdentifier(element.project, name).node
												element.node.replaceChild(idNode, newIdNode)
										}
										element
								}

								else -> {
										thisLogger().warn("setName: unknown element type: ${element.javaClass}")
										element
								}
						}
				}

				@JvmStatic
				fun getReference(element: ASTWrapperPsiElement): PsiReference? {
						return when (element) {
								is ArcValueExprImpl,
								is ArcNamedElementImpl -> {
										element.node.findChildByType(ArcTypes.ID).let {
												if (it == null) {
														return null
												}

												val startOffset: Int = it.startOffset - element.textRange.startOffset
												val range = TextRange(startOffset, startOffset + it.textLength)

												return ArcReference(element, range)
										}
								}

								is ArcObjectFieldKey -> {
										val fieldRef = element as ArcObjectFieldKeyImpl

										// If our parent is an object instantiate expr then we're trying to go to the declaration of the field
										val objInstExpr = fieldRef.findParentOfType<ArcObjectInstantiateExpr>()
										if (objInstExpr != null) {
												val startOffset: Int = fieldRef.startOffset - fieldRef.parent.textRange.startOffset
												val range = TextRange(startOffset, startOffset + fieldRef.textLength)

												return ArcReference(fieldRef, range)
										}

										return null
								}

								is ArcFuncDeclarationImpl -> {
										return getReference(element.funcId as ASTWrapperPsiElement)
								}

								is ArcMemberAccessExprImpl -> {
										return null
								}

								else -> {
										thisLogger().warn("getReference: unknown element type: ${element.javaClass}")
										null
								}
						}
				}
		}
}
