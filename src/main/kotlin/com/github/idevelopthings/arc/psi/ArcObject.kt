package com.github.idevelopthings.arc.psi

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement

interface ArcObject : PsiElement {

		fun getFields(): List<ArcObjectFieldDeclaration>

		fun getMember(name: String): ArcObjectFieldDeclaration?

		fun getPresentation(): ItemPresentation?;

}
