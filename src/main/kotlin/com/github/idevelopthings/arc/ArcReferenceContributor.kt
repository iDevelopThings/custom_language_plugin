package com.github.idevelopthings.arc

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.github.idevelopthings.arc.psi.*
import org.jetbrains.annotations.NotNull

class ArcReferenceContributor : PsiReferenceContributor() {
		override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {

				registrar.registerReferenceProvider(
						PlatformPatterns.psiElement(ArcVariableDeclaration::class.java)
								.withLanguage(ArcLanguage.INSTANCE),
//						PlatformPatterns.psiElement()
//								.andOr(
//										PlatformPatterns.psiElement(ArcNamedElement::class.java),
//										PlatformPatterns.psiElement(ArcValueExpr::class.java),
//								),
						object : PsiReferenceProvider() {
								override fun getReferencesByElement(
										@NotNull element: PsiElement,
										@NotNull context: ProcessingContext
								): Array<out PsiReference> {
										thisLogger().warn("getReferencesByElement: nodeType: ${element.node.elementType} - text: ${element.text}")
										return arrayOf(ArcReference(element, element.textRangeInParent))
								}
						}
				)
//				registrar.registerReferenceProvider(
////						PlatformPatterns.psiElement(ArcMemberCallExpr::class.java),
//						PlatformPatterns.psiElement(),
//						object : PsiReferenceProvider() {
//								override fun getReferencesByElement(
//										@NotNull element: PsiElement,
//										@NotNull context: ProcessingContext
//								): Array<out PsiReference> {
//										thisLogger().warn("getReferencesByElement: nodeType: ${element.node.elementType} - text: ${element.text}")
//
//										if (element.parent is ArcType) {
//												return arrayOf(ArcReference(element, element.textRangeInParent))
//										}
//
//										if (element is ArcVariableDeclaration) {
//												element.type?.let {
//														return arrayOf(ArcReference(element, it.textRangeInParent))
//												}
//										}
//
//										return PsiReference.EMPTY_ARRAY
//								}
//						}
//				)


		}
}
