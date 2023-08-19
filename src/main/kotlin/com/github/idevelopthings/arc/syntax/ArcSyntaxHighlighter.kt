package com.github.idevelopthings.arc.syntax

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
				return ArcLexerAdapter()
		}

		override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {

				if (ArcTokenSets.KEYWORDS.contains(tokenType))
						return arrayOf(KEYWORD)

				return when (tokenType) {

						ArcTypes.ID -> arrayOf(ID)

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
