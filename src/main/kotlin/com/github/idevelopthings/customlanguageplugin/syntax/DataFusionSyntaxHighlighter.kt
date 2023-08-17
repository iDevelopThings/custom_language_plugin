package com.github.idevelopthings.customlanguageplugin.syntax

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.github.idevelopthings.customlanguageplugin.DataFusionLexerAdapter
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTokenSets
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes


class DataFusionSyntaxHighlighter : SyntaxHighlighterBase() {

		companion object {
				val EMPTY_KEYS = arrayOf<TextAttributesKey>()
				val BAD_CHARACTER: TextAttributesKey = createTextAttributesKey("DATA_FUSION_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

				val ID = createTextAttributesKey("DATA_FUSION_ID", DefaultLanguageHighlighterColors.IDENTIFIER)

				val KEYWORD = createTextAttributesKey("DATA_FUSION_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

				val STRING = createTextAttributesKey("DATA_FUSION_STRING", DefaultLanguageHighlighterColors.STRING)
				val NUMBER = createTextAttributesKey("DATA_FUSION_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

				val LINE_COMMENT = createTextAttributesKey("DATA_FUSION_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
				val BLOCK_COMMENT = createTextAttributesKey("DATA_FUSION_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)

				val DOT = createTextAttributesKey("DATA_FUSION_DOT", DefaultLanguageHighlighterColors.DOT)
				val COMMA = createTextAttributesKey("DATA_FUSION_COMMA", DefaultLanguageHighlighterColors.COMMA)
				val COLON = createTextAttributesKey("DATA_FUSION_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN)
				val SEMICOLON = createTextAttributesKey("DATA_FUSION_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)

				val TYPE_NAME = createTextAttributesKey("DATA_FUSION_TYPE_NAME", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
				val TYPE_REFERENCE = createTextAttributesKey("DATA_FUSION_TYPE_REFERENCE", DefaultLanguageHighlighterColors.CLASS_NAME)

				val PARENS = createTextAttributesKey("DATA_FUSION_PARENS", DefaultLanguageHighlighterColors.PARENTHESES)
				val BRACKETS = createTextAttributesKey("DATA_FUSION_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
				val BRACES = createTextAttributesKey("DATA_FUSION_BRACES", DefaultLanguageHighlighterColors.BRACES)
		}

		override fun getHighlightingLexer(): Lexer {
				return DataFusionLexerAdapter()
		}

		override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {

				if (DataFusionTokenSets.KEYWORDS.contains(tokenType))
						return arrayOf(KEYWORD)

				return when (tokenType) {

						DataFusionTypes.ID -> arrayOf(ID)

						DataFusionTypes.DOT -> arrayOf(DOT)
						DataFusionTypes.COMMA -> arrayOf(COMMA)
						DataFusionTypes.COLON -> arrayOf(COLON)
						DataFusionTypes.SEMICOLON -> arrayOf(SEMICOLON)

						DataFusionTypes.LPAREN, DataFusionTypes.RPAREN -> arrayOf(PARENS)
						DataFusionTypes.LBRACK, DataFusionTypes.RBRACK -> arrayOf(BRACKETS)
						DataFusionTypes.LCURLY, DataFusionTypes.RCURLY -> arrayOf(BRACES)

						DataFusionTypes.VALUE_STRING,
						DataFusionTypes.DOUBLE_QUOUTE_STRING,
						DataFusionTypes.SINGLE_QUOUTE_STRING,
						DataFusionTypes.BACKTICK_STRING,
						-> arrayOf(STRING)

						DataFusionTypes.VALUE_INTEGER, DataFusionTypes.VALUE_FLOAT -> arrayOf(NUMBER)


						DataFusionTypes.LINE_COMMENT -> arrayOf(LINE_COMMENT)
						DataFusionTypes.BLOCK_COMMENT -> arrayOf(BLOCK_COMMENT)

						TokenType.BAD_CHARACTER -> arrayOf(BAD_CHARACTER)

						else -> return EMPTY_KEYS
				}
		}

}
