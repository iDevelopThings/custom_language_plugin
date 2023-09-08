package com.github.idevelopthings.arc.completion.providers

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.Common
import com.github.idevelopthings.arc.language.LanguageInfo
import com.github.idevelopthings.arc.psi.ArcFuncDeclaration
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.util.ProcessingContext

class TypeNameCompletionProvider : CompletionProvider<CompletionParameters?>() {

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, originalResultSet: CompletionResultSet) {
				val offset = parameters.offset
				val charSeq = parameters.editor.document.immutableCharSequence
				val currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset)
				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}

				if (Common.isAfterBadIdentifier(charSeq, offset)) return

				thisLogger().warn("Current element str: ${currentElement.text}")

				val declarations = ArcUtil.findAllDeclarations(parameters.originalFile.project)
						.filter { it !is ArcFuncDeclaration }

				declarations.forEach {
						originalResultSet.addElement(it.getLookupElement())
				}

				originalResultSet.addAllElements(LanguageInfo.builtinTypeLookupElements)

		}


}
