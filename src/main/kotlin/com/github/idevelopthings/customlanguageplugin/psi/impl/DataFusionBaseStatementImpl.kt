package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.childrenOfType

abstract class DataFusionBaseStatementImpl(node: ASTNode) : ASTWrapperPsiElement(node), DataFusionBaseStatement {

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {


				if (this is DataFusionStatementImpl) {
						val valExpr = PsiTreeUtil.findChildOfType(this, DataFusionVariableDeclaration::class.java)
						valExpr?.let {
								if (!processor.execute(it, state)) {
										return false
								}
						}
				}

				return true
		}

}
