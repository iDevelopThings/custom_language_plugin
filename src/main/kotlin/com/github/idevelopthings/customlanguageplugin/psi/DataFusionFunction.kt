package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.psi.PsiElement

interface DataFusionFunction : PsiElement {

		fun getNameIdentifier(): DataFusionFuncId

		fun getName(): String

}
