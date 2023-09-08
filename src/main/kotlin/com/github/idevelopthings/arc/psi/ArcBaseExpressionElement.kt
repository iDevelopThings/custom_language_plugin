package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference

interface ArcBaseExpressionElement : PsiNameIdentifierOwner {

		override fun getNameIdentifier(): PsiElement?

}
abstract class ArcBaseExpressionElementImpl(node: ASTNode) :
		ASTWrapperPsiElement(node),
		ArcBaseExpressionElement {

		override fun getNameIdentifier(): PsiElement? {
				return ArcPsiUtilImpl.getNameIdentifier(this)
		}

		override fun setName(name: String): PsiElement {
				return ArcPsiUtilImpl.setName(this, name)
		}

		override fun getReference(): PsiReference? {
				return ArcPsiUtilImpl.getReference(this)
		}
}
