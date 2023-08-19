package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.ArcMemberCallExpr
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

open class ArcMemberCallExpressionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node) {

		fun getAccessor(): ArcMemberAccessExprImpl? {
				return this.findChildByType(ArcTypes.MEMBER_ACCESS_EXPR)
		}

		override fun getReference(): PsiReference? {
				if(this is ArcMemberCallExpr) {
						val accessor = getAccessor()
						if(accessor != null) {
								return accessor.getReference()
						}
				}


				return ArcPsiImplUtil.getReference(this)
		}

}
