package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.completion.references.contributors.VariableReferenceContributor
import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class ArcReferenceContributor : PsiReferenceContributor() {
		override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {

				// registrar.registerReferenceProvider(
				// 		PlatformPatterns.psiElement(),
				// 		MemberAccessContributor()
				// )
				// registrar.registerReferenceProvider(
				// 		PlatformPatterns.psiElement()
				// 				.withElementType(ArcTypes.SIMPLE_REF_EXPR)
				// 				.inFile(PlatformPatterns.psiFile(ArcFile::class.java)),
				//
				// 		VariableReferenceContributor()
				// )

				// registrar.registerReferenceProvider(
				// 		PlatformPatterns.psiElement(ArcTypes.IMPORT_STATEMENT)
				// 				.inFile(PlatformPatterns.psiFile(ArcFile::class.java)),
				// 		ImportStatementReferenceContributor()
				// )
		}


}

/* class ImportStatementReferenceContributor : PsiReferenceProvider() {
		override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {

				thisLogger().warn("element: $element -> ${element.text}")

				return arrayOf()
		}
} */



