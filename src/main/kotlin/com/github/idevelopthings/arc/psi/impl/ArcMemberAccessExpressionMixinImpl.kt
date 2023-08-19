package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.ArcFunctionReference
import com.github.idevelopthings.arc.psi.ArcMemberAccessExpr
import com.github.idevelopthings.arc.psi.ArcMemberCallExpr
import com.github.idevelopthings.arc.psi.ArcTypes
import com.github.idevelopthings.arc.psi.ArcValueExpr
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.refactoring.suggested.startOffset

open class ArcMemberAccessExpressionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node) {

		fun isCallExpression(): Boolean {
				return parent is ArcMemberCallExpr
		}

		fun getRHS(): PsiElement? {
				return findChildByType<PsiElement>(ArcTypes.ID)
		}

		fun getLHS(): PsiElement? {
				return findChildByType<ArcValueExpr>(ArcTypes.VALUE_EXPR)
							 ?: findChildByType<ArcMemberAccessExpr>(ArcTypes.MEMBER_ACCESS_EXPR)
		}

		override fun getReference(): PsiReference? {
				val id = getRHS() ?: return null

				val startOffset: Int = id.startOffset - this.textRange.startOffset
				val range = TextRange(startOffset, startOffset + id.textLength)

				return ArcFunctionReference(this, range)
		}
}
