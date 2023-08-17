package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.completion.DeclarationProcessor
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.startOffset
import com.github.idevelopthings.customlanguageplugin.psi.*
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiReference
import com.intellij.psi.ResolveState


class DataFusionPsiImplUtil {
		companion object {

				fun getName(element: ASTWrapperPsiElement): String {
						return when (element) {
								is DataFusionTypeImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.text
								} ?: ""

								is DataFusionObjectIdImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.text
								} ?: ""

								is DataFusionBaseExpressionElementImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.text
								} ?: ""

								is DataFusionVarIdImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
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
								is DataFusionTypeImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.psi
								}

								is DataFusionObjectIdImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.psi
								}

								is DataFusionBaseExpressionElementImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
										return it.psi
								}

								is DataFusionVarIdImpl -> element.node.findChildByType(DataFusionTypes.ID)?.let {
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
								is DataFusionNamedElementImpl -> {
										val idNode = element.node.findChildByType(DataFusionTypes.ID)
										if (idNode != null) {
												val newIdNode = DataFusionElementFactory.createIdentifier(element.project, name).node
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

				fun getReference(element: ASTWrapperPsiElement): PsiReference? {
						return when (element) {
								is DataFusionValueExprImpl, is DataFusionNamedElementImpl -> {
										element.node.findChildByType(DataFusionTypes.ID).let {
												if (it == null) {
														return null
												}

												val startOffset: Int = it.startOffset - element.textRange.startOffset
												val range = TextRange(startOffset, startOffset + it.textLength)

												return DataFusionReference(element, range)
										}
								}

								else -> {
										thisLogger().warn("getReference: unknown element type: ${element.javaClass}")
										null
								}
						}
				}
		}
}
