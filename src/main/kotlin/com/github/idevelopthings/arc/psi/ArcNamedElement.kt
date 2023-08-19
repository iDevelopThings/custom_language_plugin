package com.github.idevelopthings.arc.psi

import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference

interface ArcNamedElement : PsiNameIdentifierOwner, NavigationItem {
		override fun getPresentation(): ItemPresentation?

		override fun getNameIdentifier(): PsiElement?

		override fun getName(): String?

		override fun getReference(): PsiReference?

}
