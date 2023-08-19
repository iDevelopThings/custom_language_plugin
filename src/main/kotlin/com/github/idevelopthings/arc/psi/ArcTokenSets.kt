package com.github.idevelopthings.arc.psi

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet


class ArcTokenSets {
		companion object {

				val NLS: IElementType = ArcTokenType("Arc_WS_NEW_LINES")

				val COMMENTS = TokenSet.create(ArcTypes.LINE_COMMENT, ArcTypes.BLOCK_COMMENT)
				val WHITESPACES = TokenSet.create(com.intellij.psi.TokenType.WHITE_SPACE, NLS)

				val ID = TokenSet.create(ArcTypes.ID)

				val IDENTIFIERS = TokenSet.create(
						ArcTypes.ID,
						ArcTypes.OBJECT_ID,
						ArcTypes.VAR_ID,
						ArcTypes.FUNC_ID,
				)

				val OBJECT_DECLARATION = TokenSet.create(ArcTypes.OBJECT_DECLARATION)
				val OBJECT_FIELD_DECLARATION = TokenSet.create(ArcTypes.OBJECT_FIELD_DECLARATION)

				val FUNC_DECLARATION = TokenSet.create(ArcTypes.FUNC_DECLARATION)

				val STRING = TokenSet.create(ArcTypes.VALUE_STRING)

				val PARENS = TokenSet.create(ArcTypes.LPAREN, ArcTypes.RPAREN)
				val CURLY_BRACKETS = TokenSet.create(ArcTypes.LCURLY, ArcTypes.RCURLY)
				val SQ_BRACKETS = TokenSet.create(ArcTypes.LBRACK, ArcTypes.RBRACK)

				val KEYWORDS = TokenSet.create(
						ArcTypes.VAR_KW,
						ArcTypes.RETURN_KW,
						ArcTypes.OBJECT_KW,
						ArcTypes.FUNC_KW,
						ArcTypes.IF_KW,
						ArcTypes.ELSE_KW,
						ArcTypes.FOR_KW,
						ArcTypes.AS_KW,
				)
		}
}
