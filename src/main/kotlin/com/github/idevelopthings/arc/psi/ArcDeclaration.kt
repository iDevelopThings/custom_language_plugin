package com.github.idevelopthings.arc.psi

import com.intellij.psi.PsiElement

interface ArcDeclaration : PsiElement {

		fun getNameIdentifier(): ArcNamedElement?

		fun getDeclaration(): PsiElement?

}
