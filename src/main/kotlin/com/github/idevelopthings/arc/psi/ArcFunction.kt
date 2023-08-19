package com.github.idevelopthings.arc.psi

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement

interface ArcFunction : PsiElement {

		fun getNameIdentifier(): ArcFuncId

		fun getName(): String

		fun getPresentation(): ItemPresentation?

		fun belongsToType(objDeclaration: ArcObjectDeclaration): Boolean
}
