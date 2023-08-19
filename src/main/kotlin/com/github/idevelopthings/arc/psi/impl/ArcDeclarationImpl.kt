package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement


open class ArcDeclarationImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcDeclaration {

		override fun getNameIdentifier(): ArcNamedElement? {
				return when (this.firstChild) {
						is ArcFuncDeclaration -> (this.firstChild as ArcFuncDeclaration).funcId
						is ArcObjectDeclaration -> (this.firstChild as ArcObjectDeclaration).objectId
						else -> null
				}
		}

		override fun getDeclaration(): PsiElement? {
				return when (this.firstChild) {
						is ArcFuncDeclaration -> (this.firstChild as ArcFuncDeclaration)
						is ArcObjectDeclaration -> (this.firstChild as ArcObjectDeclaration)
						else -> null
				}
		}

}
