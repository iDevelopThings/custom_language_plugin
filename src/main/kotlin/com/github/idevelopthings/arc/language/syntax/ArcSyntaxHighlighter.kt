package com.github.idevelopthings.arc.language.syntax

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.github.idevelopthings.arc.ArcLexerAdapter
import com.github.idevelopthings.arc.psi.ArcTokenSets
import com.github.idevelopthings.arc.psi.ArcTypes


class ArcSyntaxHighlighter : SyntaxHighlighterBase() {

		companion object {
				val EMPTY_KEYS = arrayOf<TextAttributesKey>()
				val BAD_CHARACTER: TextAttributesKey = createTextAttributesKey("ARC_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

				val ID = createTextAttributesKey("ARC_ID", DefaultLanguageHighlighterColors.IDENTIFIER)

				val KEYWORD = createTextAttributesKey("ARC_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

				val STRING = createTextAttributesKey("ARC_STRING", DefaultLanguageHighlighterColors.STRING)
				val NUMBER = createTextAttributesKey("ARC_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

				val LINE_COMMENT = createTextAttributesKey("ARC_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
				val BLOCK_COMMENT = createTextAttributesKey("ARC_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)

				val DOT = createTextAttributesKey("ARC_DOT", DefaultLanguageHighlighterColors.DOT)
				val COMMA = createTextAttributesKey("ARC_COMMA", DefaultLanguageHighlighterColors.COMMA)
				val COLON = createTextAttributesKey("ARC_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN)
				val SEMICOLON = createTextAttributesKey("ARC_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)

				val TYPE_NAME = createTextAttributesKey("ARC_TYPE_NAME", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
				val TYPE_REFERENCE = createTextAttributesKey("ARC_TYPE_REFERENCE")

				val ENUM_FIELD = createTextAttributesKey("ARC_ENUM_FIELD", DefaultLanguageHighlighterColors.CONSTANT)

				val FUNCTION_CALL = createTextAttributesKey("ARC_GLOBAL_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL)
				val FUNCTION_DECLARATION = createTextAttributesKey("ARC_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)

				val RECEIVER_LOCAL_REF = createTextAttributesKey("ARC_RECEIVER_LOCAL_REF")
				val LOCAL_VARIABLE = createTextAttributesKey("ARC_LOCAL_VARIABLE", DefaultLanguageHighlighterColors.IDENTIFIER)

				val STD_RUNTIME_LIB_NS = createTextAttributesKey("ARC_STD_RUNTIME_LIB_NS", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

				val PARENS = createTextAttributesKey("ARC_PARENS", DefaultLanguageHighlighterColors.PARENTHESES)
				val BRACKETS = createTextAttributesKey("ARC_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
				val BRACES = createTextAttributesKey("ARC_BRACES", DefaultLanguageHighlighterColors.BRACES)
		}

		override fun getHighlightingLexer() = ArcLexerAdapter()

		override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {

				if (ArcTokenSets.KEYWORDS.contains(tokenType))
						return arrayOf(KEYWORD)

				return when (tokenType) {

						ArcTypes.ID -> {
								// if (ArcTokenSets.CUSTOM_KEYWORDS.types.firstOrNull { it.debugName == tokenType.debugName } != null) {
								// 		arrayOf(KEYWORD)
								// } else {
								arrayOf(ID)
								// }
						}

						ArcTypes.DOT -> arrayOf(DOT)
						ArcTypes.COMMA -> arrayOf(COMMA)
						ArcTypes.COLON -> arrayOf(COLON)
						ArcTypes.SEMICOLON -> arrayOf(SEMICOLON)

						ArcTypes.LPAREN, ArcTypes.RPAREN -> arrayOf(PARENS)
						ArcTypes.LBRACK, ArcTypes.RBRACK -> arrayOf(BRACKETS)
						ArcTypes.LCURLY, ArcTypes.RCURLY -> arrayOf(BRACES)

						ArcTypes.VALUE_STRING,
						ArcTypes.DOUBLE_QUOUTE_STRING,
						ArcTypes.SINGLE_QUOUTE_STRING,
						ArcTypes.BACKTICK_STRING,
						-> arrayOf(STRING)

						ArcTypes.VALUE_INTEGER, ArcTypes.VALUE_FLOAT -> arrayOf(NUMBER)


						ArcTypes.LINE_COMMENT -> arrayOf(LINE_COMMENT)
						ArcTypes.BLOCK_COMMENT -> arrayOf(BLOCK_COMMENT)

						TokenType.BAD_CHARACTER -> arrayOf(BAD_CHARACTER)

						else -> return EMPTY_KEYS
				}
		}

}
