package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil

abstract class ArcBaseStatementImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcBaseStatement {

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {


				if (this is ArcStatementImpl) {
						val valExpr = PsiTreeUtil.findChildOfType(this, ArcVariableDeclaration::class.java)
						valExpr?.let {
								if (!processor.execute(it, state)) {
										return false
								}
						}
				}

				return true
		}

}
