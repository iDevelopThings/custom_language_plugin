package com.github.idevelopthings.arc.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.UserDataHolderEx
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType

interface ArcElement : PsiElement, UserDataHolderEx

abstract class ArcElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcElement {

		override fun getNavigationElement(): PsiElement {
				return super.getNavigationElement()
		}

		override fun toString(): String = "${javaClass.simpleName}($elementType)"
}
