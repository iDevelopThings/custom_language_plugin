package com.github.idevelopthings.arc.completion.providers

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.Common
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.util.ProcessingContext

class ArcGlobalCompletionProvider : CompletionProvider<CompletionParameters?>() {

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, originalResultSet: CompletionResultSet) {
				val offset = parameters.offset
				val charSeq = parameters.editor.document.immutableCharSequence
				var currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset)
				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}


				if (Common.isAfterBadIdentifier(charSeq, offset)) return

				// currentElement = currentElement.prevSibling.prevSibling
				// if (currentElement == null) {
				// 		thisLogger().warn("currentElement is null")
				// 		return
				// }

				ArcUtil.findAllDeclarations(parameters.originalFile.project).forEach {
						originalResultSet.addElement(it.getLookupElement())
				}

		}


}


