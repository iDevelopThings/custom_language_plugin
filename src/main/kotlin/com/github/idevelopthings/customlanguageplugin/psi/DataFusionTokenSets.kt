package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet


class DataFusionTokenSets {
		companion object {

				val NLS: IElementType = DataFusionTokenType("DATAFUSION_WS_NEW_LINES")

				val COMMENTS = TokenSet.create(DataFusionTypes.LINE_COMMENT, DataFusionTypes.BLOCK_COMMENT)
				val WHITESPACES = TokenSet.create(com.intellij.psi.TokenType.WHITE_SPACE, NLS)

				val ID = TokenSet.create(DataFusionTypes.ID)

				val OBJECT_DECLARATION = TokenSet.create(DataFusionTypes.OBJECT_DECLARATION)
				val OBJECT_FIELD_DECLARATION = TokenSet.create(DataFusionTypes.OBJECT_FIELD_DECLARATION)

				val FUNC_DECLARATION = TokenSet.create(DataFusionTypes.FUNC_DECLARATION)

				val STRING = TokenSet.create(DataFusionTypes.VALUE_STRING)

				val PARENS = TokenSet.create(DataFusionTypes.LPAREN, DataFusionTypes.RPAREN)
				val CURLY_BRACKETS = TokenSet.create(DataFusionTypes.LCURLY, DataFusionTypes.RCURLY)
				val SQ_BRACKETS = TokenSet.create(DataFusionTypes.LBRACK, DataFusionTypes.RBRACK)

				val KEYWORDS = TokenSet.create(
						DataFusionTypes.VAR_KW,
						DataFusionTypes.RETURN_KW,
						DataFusionTypes.OBJECT_KW,
						DataFusionTypes.FUNC_KW,
						DataFusionTypes.IF_KW,
						DataFusionTypes.ELSE_KW,
						DataFusionTypes.FOR_KW,
						DataFusionTypes.AS_KW,
				)
		}
}
