package com.github.idevelopthings.arc.language

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons

object LanguageInfo {

		val builtinGlobalFunctions = listOf(
				"fmt::printf",
				"fmt::println",
				"fmt::print",
				"error::panic"
		)

		val potentialKeywordOrIdentifier = listOf(
				"object",
				"http",
				"from",
				"GET",
				"POST",
				"PUT",
				"DELETE",
				"PATCH",
				"HEAD",
				"OPTIONS",
				"body",
				"query",
				"route",
				"as",
				"to",
				"return",
				"var",
				"func",
				"if",
				"else",
				"for",
		)

		val builtinTypeNames = listOf(
				"int",
				"string",
				"bool",
				"float",
				"void",
				"none",
				"any",
		)

		val builtinTypeLookupElements = builtinTypeNames
				.filter {
						it != "none" && it != "any" && it != "void"
				}
				.map {
						LookupElementBuilder.create(it)
								.withBoldness(true)
								.withIcon(AllIcons.Nodes.Type)
				}

}
