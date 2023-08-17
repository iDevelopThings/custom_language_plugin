package com.github.idevelopthings.customlanguageplugin.syntax

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionObjectDeclaration
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionObjectFieldDeclaration
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionObjectFieldKey
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTokenType
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionType
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes


class CustomSyntaxAnnotator : Annotator {
		override fun annotate(element: PsiElement, holder: AnnotationHolder) {
				val elementType = element.node.elementType
//				if (elementType !== DataFusionTypes.ID) {
//						return
//				}


//				when (element.node.treeParent) {
//						is DataFusionType -> {
//								setHighlighting(element, holder, DataFusionSyntaxHighlighter.TYPE_NAME)
//								logger<CustomSyntaxAnnotator>().warn("1st matcher")
//								return
//						}
//				}
//
//				when (element.node) {
//						is DataFusionType -> {
//								setHighlighting(element, holder, DataFusionSyntaxHighlighter.TYPE_NAME)
//								logger<CustomSyntaxAnnotator>().warn("2nd matcher")
//								return
//						}
//				}

				when (elementType) {
						DataFusionTypes.TYPE -> {
								setHighlighting(element, holder, DataFusionSyntaxHighlighter.TYPE_REFERENCE)
								return
						}

						DataFusionTypes.ID -> {
								return when (element.parent) {
//
//										is DataFusionType -> {
//												setHighlighting(element, holder, DataFusionSyntaxHighlighter.TYPE_REFERENCE)
//												logger<CustomSyntaxAnnotator>().warn("DataFusionTypes.ID -> DataFusionType")
//												return
//										}
										else -> {

										}
								}
						}
				}

//				when (element.parent) {
//
//						is DataFusionObjectDeclaration -> {
//
//						}
//
//				}
//						is DataFusionFieldKeyImpl -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(DataFusionSyntaxHighlighter.KEY)
//										.create()
//						}
//
//						is DataFusionTypeReferenceImpl -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(DataFusionSyntaxHighlighter.TYPE_REFERENCE)
//										.create()
//						}
//						is DataFusionTypeAlias -> {
//								holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
//										.range(element)
//										.textAttributes(DataFusionSyntaxHighlighter.TYPE_ALIAS)
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
