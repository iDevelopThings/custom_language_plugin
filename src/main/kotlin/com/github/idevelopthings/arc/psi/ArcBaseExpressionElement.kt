package com.github.idevelopthings.arc.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface ArcBaseExpressionElement : PsiNameIdentifierOwner {

		override fun getNameIdentifier(): PsiElement?

}
