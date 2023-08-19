package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class ArcBraceMatcher : PairedBraceMatcher {
		object Util {
				val BRACE_PAIRS = arrayOf(
						BracePair(ArcTypes.LPAREN, ArcTypes.RPAREN, true),
						BracePair(ArcTypes.LCURLY, ArcTypes.RCURLY, true),
						BracePair(ArcTypes.LBRACK, ArcTypes.RBRACK, false),
				)
		}

		override fun getPairs(): Array<BracePair> {
				return Util.BRACE_PAIRS
		}

		override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
				return true
		}

		override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int {
				return openingBraceOffset
		}
}
