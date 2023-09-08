package com.github.idevelopthings.arc.language.syntax

import com.github.idevelopthings.arc.ArcKeys
import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.util.elementType


class ArcSyntaxHighlightingAnnotator : Annotator, DumbAware {

		override fun annotate(element: PsiElement, holder: AnnotationHolder) {
				when (element) {

						is ArcCallExpr -> {
								if (element.isBuiltin) {
										setHighlighting(element.refExpr.expression!!, holder, ArcSyntaxHighlighter.STD_RUNTIME_LIB_NS)
										setHighlighting(element.refExpr.id, holder, ArcSyntaxHighlighter.FUNCTION_CALL)
										return
								}

								if (element.getCopyableUserData(ArcKeys.PSI_NODE_ENUM_MARKER_KEY) == true) {
										setHighlighting(element.refExpr.expression!!, holder, ArcSyntaxHighlighter.TYPE_REFERENCE)
										setHighlighting(element.refExpr.id, holder, ArcSyntaxHighlighter.ENUM_FIELD)
										return
								}

								setHighlighting(element.refExpr.id, holder, ArcSyntaxHighlighter.FUNCTION_CALL)

								val lhs = element.lhs ?: return

								lhs.reference?.resolve()?.let {
										if (it.elementType == ArcTypes.FUNC_RECEIVER_NAME) {
												setHighlighting(lhs, holder, ArcSyntaxHighlighter.RECEIVER_LOCAL_REF)
												return
										}
								}


						}

						is ArcTypeRef -> {
								setHighlighting(element.type?.id!!, holder, ArcSyntaxHighlighter.TYPE_REFERENCE)
						}

						is ArcType -> {
								setHighlighting(element.id!!, holder, ArcSyntaxHighlighter.TYPE_REFERENCE)
						}

						is ArcFuncId -> setHighlighting(element, holder, ArcSyntaxHighlighter.FUNCTION_DECLARATION)

						is ArcFuncReceiverName -> setHighlighting(element, holder, ArcSyntaxHighlighter.RECEIVER_LOCAL_REF)

						is ArcSimpleRefExpr -> {
								if (element.firstChild.node.elementType == ArcTypes.ID) {
										element.reference?.resolve()?.let {
												if (it.elementType == ArcTypes.FUNC_RECEIVER_NAME) {
														setHighlighting(element, holder, ArcSyntaxHighlighter.RECEIVER_LOCAL_REF)
														return
												}
												if (element.parent is ArcCallExpr) {
														return
												}
												setHighlighting(element, holder, ArcSyntaxHighlighter.LOCAL_VARIABLE)
										}
								}
						}

						is ArcRefExpr -> {
								ArcPsiUtil.findDeepestIdElement(element)?.let {
										setHighlighting(it, holder, ArcSyntaxHighlighter.LOCAL_VARIABLE)
								}
						}
				}
		}

		private fun setHighlighting(element: PsiElement, holder: AnnotationHolder, key: TextAttributesKey) {
				holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
						.range(element)
						.textAttributes(key)
						.create()

				//				holder.newAnnotation(HighlightSeverity.INFORMATION, "").range(element).textAttributes(key).create()
//				holder.createInfoAnnotation(element, null).enforcedTextAttributes = TextAttributes.ERASE_MARKER
//				val description = if (ApplicationManager.getApplication().isUnitTestMode) key.externalName else null
//				holder.createInfoAnnotation(element, description).setTextAttributes(key)
		}

}
