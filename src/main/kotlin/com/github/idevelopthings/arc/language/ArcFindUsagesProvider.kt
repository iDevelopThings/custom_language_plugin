package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.ArcLexerAdapter
import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.ArcObjectFieldDeclaration
import com.github.idevelopthings.arc.psi.ArcObjectFieldKey
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
//						ArcTokenSets.LITERAL_VALUES
				)
		}

		override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
				return when (psiElement) {
						is PsiNamedElement -> true
						is ArcResolvable -> true
						is ArcObjectFieldKey -> true
						else -> false
				}
		}

		override fun getHelpId(psiElement: PsiElement): String? {
				return null
		}

		override fun getType(element: PsiElement): String {
				when (element) {
						is ArcObjectFieldKey -> return (element.parent as ArcObjectFieldDeclaration).type.text
				}
				if (element is PsiNamedElement) {
						return element.name ?: "Unknown"
				}

				return "Unknown"
		}

		override fun getDescriptiveName(element: PsiElement): String {
				when (element) {
						is ArcObjectFieldKey -> return element.id.text
				}
				if (element is NavigationItem) {
						return element.presentation?.locationString + " " + element.presentation?.presentableText
				}

				return "Unknown"
		}

		override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
				when (element) {
						is ArcObjectFieldKey -> return element.text
				}

				if (element is NavigationItem) {
						return element.presentation?.presentableText ?: "Unknown"
				}

				return "Unknown"
		}
}
