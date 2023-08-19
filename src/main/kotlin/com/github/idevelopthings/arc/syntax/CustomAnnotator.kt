package com.github.idevelopthings.arc.syntax

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.github.idevelopthings.arc.psi.ArcTypes


class CustomSyntaxAnnotator : Annotator {
		override fun annotate(element: PsiElement, holder: AnnotationHolder) {
				val elementType = element.node.elementType
//				if (elementType !== ArcTypes.ID) {
//						return
//				}


//				when (element.node.treeParent) {
//						is ArcType -> {
//								setHighlighting(element, holder, ArcSyntaxHighlighter.TYPE_NAME)
//								logger<CustomSyntaxAnnotator>().warn("1st matcher")
//								return
//						}
//				}
//
//				when (element.node) {
//						is ArcType -> {
//								setHighlighting(element, holder, ArcSyntaxHighlighter.TYPE_NAME)
//								logger<CustomSyntaxAnnotator>().warn("2nd matcher")
//								return
//						}
//				}

				when (elementType) {
						ArcTypes.TYPE -> {
								setHighlighting(element, holder, ArcSyntaxHighlighter.TYPE_REFERENCE)
								return
						}

						ArcTypes.ID -> {
								return when (element.parent) {
//
//										is ArcType -> {
//												setHighlighting(element, holder, ArcSyntaxHighlighter.TYPE_REFERENCE)
//												logger<CustomSyntaxAnnotator>().warn("ArcTypes.ID -> ArcType")
//												return
//										}
										else -> {

										}
								}
						}
				}

//				when (element.parent) {
//
//						is ArcObjectDeclaration -> {
//
//						}
//
//				}
//						is ArcFieldKeyImpl -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(ArcSyntaxHighlighter.KEY)
//										.create()
//						}
//
//						is ArcTypeReferenceImpl -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(ArcSyntaxHighlighter.TYPE_REFERENCE)
//										.create()
//						}
//						is ArcTypeAlias -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(ArcSyntaxHighlighter.TYPE_ALIAS)
//										.create()
//						}
//				}


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
