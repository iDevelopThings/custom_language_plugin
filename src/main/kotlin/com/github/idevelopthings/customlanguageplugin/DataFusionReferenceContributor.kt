package com.github.idevelopthings.customlanguageplugin

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.github.idevelopthings.customlanguageplugin.psi.*
import com.github.idevelopthings.customlanguageplugin.psi.impl.DataFusionVarIdImpl
import com.github.idevelopthings.customlanguageplugin.psi.impl.DataFusionVariableDeclarationImpl
import org.jetbrains.annotations.NotNull

class DataFusionReferenceContributor : PsiReferenceContributor() {
		override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {

				registrar.registerReferenceProvider(
						PlatformPatterns.psiElement(DataFusionVariableDeclaration::class.java)
								.withLanguage(DataFusionLanguage.INSTANCE),
//						PlatformPatterns.psiElement()
//								.andOr(
//										PlatformPatterns.psiElement(DataFusionNamedElement::class.java),
//										PlatformPatterns.psiElement(DataFusionValueExpr::class.java),
//								),
						object : PsiReferenceProvider() {
								override fun getReferencesByElement(
										@NotNull element: PsiElement,
										@NotNull context: ProcessingContext
								): Array<out PsiReference> {
										thisLogger().warn("getReferencesByElement: nodeType: ${element.node.elementType} - text: ${element.text}")
										return arrayOf(DataFusionReference(element, element.textRangeInParent))
								}
						}
				)
//				registrar.registerReferenceProvider(
////						PlatformPatterns.psiElement(DataFusionMemberCallExpr::class.java),
//						PlatformPatterns.psiElement(),
//						object : PsiReferenceProvider() {
//								override fun getReferencesByElement(
//										@NotNull element: PsiElement,
//										@NotNull context: ProcessingContext
//								): Array<out PsiReference> {
//										thisLogger().warn("getReferencesByElement: nodeType: ${element.node.elementType} - text: ${element.text}")
//
//										if (element.parent is DataFusionType) {
//												return arrayOf(DataFusionReference(element, element.textRangeInParent))
//										}
//
//										if (element is DataFusionVariableDeclaration) {
//												element.type?.let {
//														return arrayOf(DataFusionReference(element, it.textRangeInParent))
//												}
//										}
//
//										return PsiReference.EMPTY_ARRAY
//								}
//						}
//				)


		}
}
