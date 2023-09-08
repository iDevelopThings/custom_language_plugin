package com.github.idevelopthings.arc.parser

import com.github.idevelopthings.arc.ext.makeBitMask
import com.github.idevelopthings.arc.psi.ArcTokenSets
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Key
import com.intellij.util.BitUtil
import com.intellij.util.containers.Stack


object ArcParserUtil : GeneratedParserUtilBase() {

		private val LOG = logger<ArcParserUtil>()

		enum class IdentMode { ON, OFF }

		private val IDENT_MODE: Int = makeBitMask(1)

		private fun Int.setFlag(flag: Int, mode: Boolean): Int = BitUtil.set(this, flag, mode)

		private val DEFAULT_FLAGS: Int = IDENT_MODE


		private val FLAGS: Key<Int> = Key("ArcParserUtil.FLAGS")
		private var PsiBuilder.flags: Int
				get() = getUserData(FLAGS) ?: DEFAULT_FLAGS
				set(value) = putUserData(FLAGS, value)

		private val FLAG_STACK: Key<Stack<Int>> = Key("ArcParserUtil.FLAG_STACK")
		private var PsiBuilder.flagStack: Stack<Int>
				get() = getUserData(FLAG_STACK) ?: Stack<Int>(0)
				set(value) = putUserData(FLAG_STACK, value)

		private fun PsiBuilder.popFlag() {
				flags = flagStack.pop()
		}

		@JvmStatic
		private fun PsiBuilder.pushFlag(flag: Int, mode: Boolean) {
				val stack = flagStack
				stack.push(flags)
				flagStack = stack
				flags = BitUtil.set(flags, flag, mode)
		}

		@JvmStatic
		fun resetFlags(b: PsiBuilder, level: Int): Boolean {
				b.popFlag()
				return true
		}

		/**
		 * Idk why, but we need this for `prefix::name()` call expressions to work properly
		 */
		@JvmStatic
		fun isBuiltin(builder: PsiBuilder, level: Int): Boolean {
				// val marker = builder.latestDoneMarker ?: return false
				// val parsedText = builder.originalText.subSequence(marker.startOffset, marker.endOffset).toString()
				return false
		}

		@JvmStatic
		fun setIdentMode(b: PsiBuilder, level: Int, mode: IdentMode): Boolean {
				b.pushFlag(IDENT_MODE, mode == IdentMode.ON)
				return true
		}

		@JvmStatic
		fun parseIdentifier(b: PsiBuilder, level: Int): Boolean {
				if (!BitUtil.isSet(b.flags, IDENT_MODE)) return false
				// See if it's one of the keywords that can also be an ident
				if (
						!ArcTokenSets.KEYWORDS_WHICH_ARE_ALSO_IDENTS.types.contains(b.tokenType) &&
						b.tokenType != ArcTypes.ID
				) return false

				b.remapCurrentToken(ArcTypes.ID)
				consumeToken(b, ArcTypes.ID)

				return true
		}

		/* private fun contextualKeywordWithRollback(b: PsiBuilder, keyword: String, elementType: IElementType): Boolean {
				if (b.tokenType == ArcTypes.ID && b.tokenText == keyword) {
						val marker = b.mark()
						b.advanceLexer()
						marker.collapse(elementType)
						return true
				}
				return false
		} */

}

