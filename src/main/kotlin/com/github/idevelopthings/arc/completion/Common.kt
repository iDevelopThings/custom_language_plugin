package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

object Common {

		fun isAfterBadIdentifier(text: CharSequence, offset: Int): Boolean {
				// We need to check if the text is an identifier
				// The interpreter uses go's `unicode.IsLetter(ch)` to check if the char is a letter
				// So we have to do the same

				if (offset == 0 || offset >= text.length) return false

				var currentOffset = offset - 1
				var currentChar = text[currentOffset]

				if (!Character.isLetter(currentChar)) return false

				while (currentOffset > 0) {
						currentOffset--
						currentChar = text[currentOffset]

						if (!Character.isLetterOrDigit(currentChar) && currentChar != '_') {
								return false
						}
				}

				return true
		}

		fun prevIsDot(text: CharSequence, offset: Int): Boolean {
				if (offset == 0 || offset >= text.length) return false
				return text[offset - 1] == '.'
		}

		fun prevIsColonColon(text: CharSequence, offset: Int): Boolean {
				if (offset == 0 || offset >= text.length) return false
				return text[offset - 1] == ':' && text[offset - 2] == ':'
		}

		fun isValidCompletionIdentElement(element: PsiElement?): Boolean {
				return element is ArcSimpleRefExpr || element is ArcRefExpr || element?.elementType == ArcTypes.ID
		}

		fun findCompletionIdentWhenUsingDot(element: PsiElement): PsiElement? {
				var ident: PsiElement? = element
				while ((ident !is ArcRefExpr)) {
						if (ident == null) {
								return null
						}

						ident = ident.prevSibling
				}

				if (isValidCompletionIdentElement(ident)) {
						return ident
				}

				return null
		}

}
