package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.psi.DataFusionFunctionReference
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionMemberAccessExpr
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionMemberCallExpr
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionValueExpr
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.refactoring.suggested.startOffset

open class DataFusionMemberAccessExpressionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node) {

		fun isCallExpression(): Boolean {
				return parent is DataFusionMemberCallExpr
		}

		fun getRHS(): PsiElement? {
				return findChildByType<PsiElement>(DataFusionTypes.ID)
		}

		fun getLHS(): DataFusionValueExpr? {
				return findChildByType<DataFusionValueExpr>(DataFusionTypes.VALUE_EXPR)
		}

		override fun getReference(): PsiReference? {
				val id = getRHS() ?: return null

				val startOffset: Int = id.startOffset - this.textRange.startOffset
				val range = TextRange(startOffset, startOffset + id.textLength)

				return DataFusionFunctionReference(this, range)
		}
}
