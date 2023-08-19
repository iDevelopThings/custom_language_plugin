package com.github.idevelopthings.arc.syntax

import com.intellij.psi.*


class ArcSyntaxReferenceContributor : PsiReferenceContributor() {
		override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
//				registrar.registerReferenceProvider(
						// We need to begin capturing the input when, imagine we type:
						// `myFieldName : ` <- here we need to start capturing the input
						// `myFieldName : MyType` <- here we need to stop capturing the input
//						PlatformPatterns.psiElement()
//								.afterLeaf(PlatformPatterns.psiElement(ArcTypes.COLON))
//								.withLanguage(ArcLanguage.INSTANCE)
//								.withParent(PlatformPatterns.psiElement(ArcTypes.FIELD_DEFINITION)),
//
//						object : PsiReferenceProvider() {
//								override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<out PsiReference> {
//										val textRange = TextRange(0, element.textLength)
//										return arrayOf(ArcPsiTypeReference(element, textRange))
//								}
//						}
//				)
		}
}
