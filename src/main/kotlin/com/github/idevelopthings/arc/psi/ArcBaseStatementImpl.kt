package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.psi.impl.ArcStatementImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor

abstract class ArcBaseStatementImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcBaseStatement {

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				if (this is ArcStatementImpl) {
						val varDecl = variableDeclaration
						if (varDecl != null) {
								if (!processor.execute(varDecl, state)) {
										return false
								}
						}
				}

				return true
		}

}
