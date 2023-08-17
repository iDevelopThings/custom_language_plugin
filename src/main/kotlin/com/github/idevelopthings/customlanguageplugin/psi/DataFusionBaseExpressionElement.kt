package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface DataFusionBaseExpressionElement : PsiNameIdentifierOwner {

		override fun getNameIdentifier(): PsiElement?

}
