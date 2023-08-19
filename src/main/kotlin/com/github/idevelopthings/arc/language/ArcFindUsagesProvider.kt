package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.ArcLexerAdapter
import com.github.idevelopthings.arc.psi.ArcTokenSets
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet


class ArcFindUsagesProvider : FindUsagesProvider {

		override fun getWordsScanner(): WordsScanner? {
				return DefaultWordsScanner(
						ArcLexerAdapter(),
						ArcTokenSets.IDENTIFIERS,
						ArcTokenSets.COMMENTS,
						TokenSet.EMPTY
				)
		}

		override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
				return psiElement is PsiNamedElement
		}

		override fun getHelpId(psiElement: PsiElement): String? {
				return null
		}

		override fun getType(element: PsiElement): String {
				if (element is PsiNamedElement) {
						return element.name ?: "Unknown"
				}

				return "Unknown"
		}

		override fun getDescriptiveName(element: PsiElement): String {
				if (element is NavigationItem) {
						return element.presentation?.locationString + " " + element.presentation?.presentableText
				}

				return "Unknown"
		}

		override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
				if (element is NavigationItem) {
						return element.presentation?.presentableText ?: "Unknown"
				}

				return "Unknown"
		}
}
