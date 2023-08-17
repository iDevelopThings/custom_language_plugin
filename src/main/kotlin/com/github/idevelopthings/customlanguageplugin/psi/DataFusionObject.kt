package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.psi.PsiElement

interface DataFusionObject : PsiElement {

		fun getFields(): List<DataFusionObjectFieldDeclaration>

}
