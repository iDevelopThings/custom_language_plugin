package com.github.idevelopthings.customlanguageplugin.syntax

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.github.idevelopthings.customlanguageplugin.DataFusionLanguage



class DataFusionSyntaxReferenceContributor : PsiReferenceContributor() {
		override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
//				registrar.registerReferenceProvider(
						// We need to begin capturing the input when, imagine we type:
						// `myFieldName : ` <- here we need to start capturing the input
						// `myFieldName : MyType` <- here we need to stop capturing the input
//						PlatformPatterns.psiElement()
//								.afterLeaf(PlatformPatterns.psiElement(DataFusionTypes.COLON))
//								.withLanguage(DataFusionLanguage.INSTANCE)
//								.withParent(PlatformPatterns.psiElement(DataFusionTypes.FIELD_DEFINITION)),
//
//						object : PsiReferenceProvider() {
//								override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<out PsiReference> {
//										val textRange = TextRange(0, element.textLength)
//										return arrayOf(DataFusionPsiTypeReference(element, textRange))
//								}
//						}
//				)
		}
}
