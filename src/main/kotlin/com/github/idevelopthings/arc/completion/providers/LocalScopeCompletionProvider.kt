package com.github.idevelopthings.arc.completion.providers

import com.github.idevelopthings.arc.completion.Common
import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.processor.ResolverChain
import com.github.idevelopthings.arc.completion.resolution.ArgumentTypeResolver
import com.github.idevelopthings.arc.completion.resolution.FuncReceiverTypeResolver
import com.github.idevelopthings.arc.completion.resolution.VarTypeResolver
import com.github.idevelopthings.arc.psi.PsiElementWithLookup
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.util.ProcessingContext

class LocalScopeCompletionProvider : CompletionProvider<CompletionParameters?>() {

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, originalResultSet: CompletionResultSet) {
				val offset = parameters.offset
				val charSeq = parameters.editor.document.immutableCharSequence
				val currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset)


				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}

				if (
						originalResultSet.prefixMatcher.prefix.isEmpty() &&
						Common.isAfterBadIdentifier(charSeq, offset)
				) {
						return
				}

				// If the previous character is a dot, we are completing a field/method...
				// This should be handled by a different completion provider/contributor
				if (Common.prevIsDot(charSeq, offset)) {
						return
				}

				val resolver = ResolverChain(currentElement).apply {
						resolver(VarTypeResolver())
						resolver(FuncReceiverTypeResolver())
						resolver(ArgumentTypeResolver())
						collector(ScopeCollector())
						withDeepLoading(true)
				}.resolve()

				if (resolver.results.isEmpty()) {
						thisLogger().warn("resolver.results is empty")
						return
				}

				originalResultSet.addAllElements(
						resolver.results.map { it.getLookupElement() }
				)

		}

}
