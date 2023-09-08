package com.github.idevelopthings.arc.psi

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.github.idevelopthings.arc.psi.ArcTypes.*

class ArcTokenSets {
		companion object {

				/**
				 * Custom keyword token types
				 */
				// val HTTP_KW: IElementType = ArcTokenType("http")
				// val HTTP_FROM_KW: IElementType = ArcTokenType("from")

				val NLS: IElementType = ArcTokenType("Arc_WS_NEW_LINES")

				val COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT)
				val WHITESPACES = TokenSet.create(com.intellij.psi.TokenType.WHITE_SPACE, NLS)

				val ID = TokenSet.create(ArcTypes.ID)

				val IDENTIFIERS = TokenSet.create(
						ArcTypes.ID,
						OBJECT_ID,
						VAR_ID,
						FUNC_ID,
						ENUM_ID,
						TYPE,
						TYPE_REF,
						SIMPLE_REF_EXPR,
//						OBJECT_FIELD_KEY
				)

				val OBJECT_DECLARATION = TokenSet.create(ArcTypes.OBJECT_DECLARATION)
				val OBJECT_FIELD_DECLARATION = TokenSet.create(ArcTypes.OBJECT_FIELD_DECLARATION)

				val FUNC_DECLARATION = TokenSet.create(ArcTypes.FUNC_DECLARATION)

				val STRING = TokenSet.create(VALUE_STRING, BACKTICK_STRING, SINGLE_QUOUTE_STRING, DOUBLE_QUOUTE_STRING)

				val PARENS = TokenSet.create(LPAREN, RPAREN)
				val CURLY_BRACKETS = TokenSet.create(LCURLY, RCURLY)
				val SQ_BRACKETS = TokenSet.create(LBRACK, RBRACK)

				val LITERAL_VALUES = TokenSet.create(
						VALUE_INTEGER,
						VALUE_FLOAT,
						DOUBLE_QUOUTE_STRING,
						SINGLE_QUOUTE_STRING,
						BACKTICK_STRING,
						VALUE_NULL,
						VALUE_BOOL,
				)
				val KEYWORDS = TokenSet.create(
						VAR_KW,
						RETURN_KW,
						OBJECT_KW,
						FUNC_KW,
						EXTERN_KW,
						IF_KW,
						ELSE_KW,
						FOR_KW,
						AS_KW,
						DELETE_KW,
						IMPORT_KW,
						STEP_KW,
						ENUM_KW,
						OR_KW,
						DEFER_KW,
						HTTP_KW,
						HTTP_ROUTE_KW,
						HTTP_FROM_KW,
				)

				// This will allow us to use keywords as identifiers
				// for ex, with `object <name>`, or a field, we can use any of these keywords
				val KEYWORDS_WHICH_ARE_ALSO_IDENTS = TokenSet.create(
						IF_KW,
						ELSE_KW,
						FOR_KW,
						EXTERN_KW,
						AS_KW,
						STEP_KW,
						OR_KW,
						HTTP_KW,
						HTTP_ROUTE_KW,
						HTTP_FROM_KW,
				)
		}
}
