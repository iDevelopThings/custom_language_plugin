package com.github.idevelopthings.arc.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

interface ArcBaseTopLevelDeclaration : PsiElement {

		fun getNameIdentifier(): ArcNamedElement?

		fun getDeclaration(): PsiElement?

}

open class ArcBaseTopLevelDeclarationImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcBaseTopLevelDeclaration {

		override fun getNameIdentifier(): ArcNamedElement? {
				return when (this.firstChild) {
						is ArcFuncDeclaration -> (this.firstChild as ArcFuncDeclaration).funcId
						is ArcObjectDeclaration -> (this.firstChild as ArcObjectDeclaration).objectId
						is ArcEnumDeclaration -> (this.firstChild as ArcEnumDeclaration).enumId
						else -> null
				}
		}

		override fun getDeclaration(): PsiElement? {
				return when (this.firstChild) {
						is ArcFuncDeclaration -> (this.firstChild as ArcFuncDeclaration)
						is ArcObjectDeclaration -> (this.firstChild as ArcObjectDeclaration)
						is ArcEnumDeclaration -> (this.firstChild as ArcEnumDeclaration)
						else -> null
				}
		}

}
