package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.completion.providers.*
import com.github.idevelopthings.arc.psi.*
import com.intellij.codeInsight.completion.*
import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.*

class ArcCompletionContributor : CompletionContributor() {


		init {


				extend(
						CompletionType.BASIC,
						Patterns.localVarPsiPattern(),
						LocalScopeCompletionProvider()
				)

				extend(
						CompletionType.BASIC,
						Patterns.memberCompletionPsiPattern(),
						MemberCompletionProvider()
				)

				extend(
						CompletionType.BASIC,
						Patterns.localVarPsiPattern(),
						ArcGlobalCompletionProvider()
				)

				extend(
						CompletionType.BASIC,
						Patterns.typeCompletionPsiPattern(),
						TypeNameCompletionProvider()
				)
				extend(
						CompletionType.BASIC,
						Patterns.importStatementPsiPattern(),
						ImportStatementPathCompletionProvider()
				)

		}

		object Patterns {
				fun localVarPsiPattern(): PsiElementPattern.Capture<PsiElement> {
						return PlatformPatterns.psiElement()
								.inFile(PlatformPatterns.psiFile(ArcFile::class.java))
								.and(PlatformPatterns.psiElement(ArcTypes.ID))
								.andNot(PlatformPatterns.psiElement().afterLeaf("."))
								.and(PlatformPatterns.psiElement().inside(PlatformPatterns.psiElement(ArcTypes.BLOCK_BODY)))
								.andNot(typeCompletionPsiPattern())
				}


				fun typeCompletionPsiPattern(): PsiElementPattern.Capture<PsiElement> {
						return PlatformPatterns.psiElement()
								.inFile(PlatformPatterns.psiFile(ArcFile::class.java))
								.inside(PlatformPatterns.psiElement(ArcTypes.TYPE))
				}

				fun importStatementPsiPattern(): PsiElementPattern.Capture<PsiElement> {
						return PlatformPatterns.psiElement()
								.inFile(PlatformPatterns.psiFile(ArcFile::class.java))
								.inside(PlatformPatterns.psiElement(ArcTypes.IMPORT_STATEMENT))
						// .and(PlatformPatterns.psiElement().inside(PlatformPatterns.psiElement(ArcTypes.VALUE_STRING)))
				}

				fun memberCompletionPsiPattern(): PsiElementPattern.Capture<PsiElement> {
						return PlatformPatterns.psiElement()
								.inFile(PlatformPatterns.psiFile(ArcFile::class.java))
								.andOr(
										PlatformPatterns.psiElement().andOr(
												PlatformPatterns.psiElement().withParent(PlatformPatterns.psiElement(ArcTypes.REF_EXPR)),
												PlatformPatterns.psiElement().afterLeaf(".")
										),
										PlatformPatterns.psiElement().andOr(
												PlatformPatterns.psiElement().withParent(PlatformPatterns.psiElement(ArcTypes.REF_EXPR)),
												PlatformPatterns.psiElement().afterLeaf("::")
										)
								)
								.and(PlatformPatterns.psiElement().inside(PlatformPatterns.psiElement(ArcTypes.BLOCK_BODY)))
								.andNot(typeCompletionPsiPattern())


				}

		}


		override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
				super.fillCompletionVariants(parameters, result)
		}
}

/*
class ArcLocalVarCompletionProvider : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {

				val element = parameters.originalFile.findElementAt(parameters.position.textOffset)
				if (element == null) {
						thisLogger().warn("Element is null")
						return
				}

				val ident = tryGetCorrectElementForCompletions(element, parameters)
				if (ident == null) {
						thisLogger().warn("[ArcLocalVarCompletionProvider] Resolve Ident is null")
						return
				}


				val processor = DeclarationLookupPartialProcessor(ident)

				PsiTreeUtil.treeWalkUp(
						processor,
						ident,
						ident.containingFile,
						ResolveState.initial()
				)

				if (processor.resolved.isNotEmpty()) {
						thisLogger().warn("Resolved ${processor.resolved.size} possible references/declarations")

						val added = mutableListOf<String>()
						val nonElementLookups = mutableListOf<PsiElementResolveResult>()
						nonElementLookups.addAll(processor.resolved.filter {
								return@filter it.element !is PsiElementWithLookup
						})

						processor.resolved.filter {
								return@filter it.element is PsiElementWithLookup
						}.forEach {
								added.add(it.element.text)
								result.addElement((it.element as PsiElementWithLookup).getLookupElement())
						}

						if (added.isEmpty()) {
								val extraLookups = mutableListOf<LookupElement>()

								nonElementLookups.forEach {
										if (it.element is ArcNamedElement && !added.contains(it.element.text)) {
												ArcPsiImplUtil.getLookupElement(it.element)?.let {
														extraLookups.add(it)
												}
										} else if (it.element.parent is ArcNamedElement && !added.contains(it.element.parent.text)) {
												ArcPsiImplUtil.getLookupElement(it.element.parent)?.let {
														extraLookups.add(it)
												}
										}
								}

								result.addAllElements(extraLookups)
						}
				}

		}
}

fun canUseCompletionElement(element: PsiElement?): Boolean {
		return element is ArcSimpleRefExpr || element is ArcRefExpr || element?.elementType == ArcTypes.ID
}

fun tryGetCorrectElementForCompletions(element: PsiElement, parameters: CompletionParameters): PsiElement? {
		if (element.elementType == ArcTypes.ID) {
				if (element.parent is ArcSimpleRefExpr) {
						return element.parent
				}
				return element
		}

		var parent: PsiElement? = element.findParentOfType<ArcSimpleRefExpr>()
		if (canUseCompletionElement(parent)) {
				return parent
		}
		parent = element.findParentOfType<ArcRefExpr>()
		if (canUseCompletionElement(parent)) {
				return parent
		}
		parent = parameters.position.node.treePrev?.treePrev?.psi
		if (canUseCompletionElement(parent)) {
				return parent
		}

		if (parent is PsiWhiteSpace) {
				var ident: PsiElement? = parent
				while ((ident !is ArcRefExpr)) {
						if (ident == null) {
								return null
						}

						ident = ident.prevSibling
				}

				if (canUseCompletionElement(ident)) {
						return ident
				}
		}

		return null
}
*/

/* class ArcCompletionProvider : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
				val position = parameters.position
				val element = parameters.originalFile.findElementAt(position.textOffset) ?: return

				val ident = tryGetCorrectElementForCompletions(element, parameters)
				if (ident == null) {
						thisLogger().warn("[ArcCompletionProvider] Resolve Ident is null")
						return
				}


				ArcUtil.provideVarRefCompletions(ident).forEach {
						result.addElement(it.getLookupElement())
				}

		}
} */

/*class ArcKeywordCompletionTemplateProviders : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
				var element = parameters.originalFile.findElementAt(parameters.position.textOffset)

				if (element !is PsiWhiteSpace) {
						element = element?.parent?.prevSibling
				}

				PsiTreeUtil.findFirstParent(element ?: parameters.originalFile.findElementAt(parameters.position.textOffset))
				{
						it is ArcBlockBody
				}?.let {
						ArcTokenSets.TEMPLATE_SCOPE_COMPLETABLE_KEYWORDS.forEach { (_, v) ->
								result.addElement(LookupElementBuilder.create(v))
						}
						return
				}

				if (element?.findParentOfType<ArcFile>() != null) {
						ArcTokenSets.TEMPLATE_TOP_LEVEL_COMPLETABLE_KEYWORDS.forEach { (_, v) ->
								result.addElement(LookupElementBuilder.create(v))
						}
				}

				thisLogger().warn("Keyword completions: Couldn't find correct scope for keywords?")


		}

}*/
