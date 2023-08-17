package com.github.idevelopthings.customlanguageplugin.completion

import com.github.idevelopthings.customlanguageplugin.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.findParentOfType

class DeclarationProcessor(private val reference: PsiElement) : PsiScopeProcessor {

		var locatedVar: DataFusionVariableDeclaration? = null
		var locatedObj: DataFusionObjectDeclaration? = null

		var resolved : PsiElementResolveResult? = null

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				when (reference) {
						is DataFusionMemberAccessExpr -> {
								return executeMemberAccessExpr(reference, element, state)
						}
						is DataFusionType -> {
								return executeType(reference, element, state)
						}

						is DataFusionValueExpr -> {
								return executeVarId(reference, element, state)
						}
				}


				return true
		}

		private fun executeMemberAccessExpr(reference: DataFusionMemberAccessExpr, element: PsiElement, state: ResolveState): Boolean {
				if(element !is DataFusionFuncDeclaration) {
						return true
				}

				if(reference.isCallExpression) {
						// Ensure our function name matches
						// func (r Type) funcName() Type
						// Then ensure our receiver type matches
						if(element.getName() == reference.getRHS()?.text) {



								resolved = PsiElementResolveResult(element.functionId)
								return false
						}
				}


				return true
		}

		private fun executeVarId(ref: DataFusionValueExpr, element: PsiElement, state: ResolveState): Boolean {
				if (element !is DataFusionStatement) {
						return true
				}

				element.variableDeclaration?.let {
						if (it.varId.text == ref.text) {
								locatedVar = it
								resolved = PsiElementResolveResult(it.varId)
								return false
						}
				}

				return true
		}

		private fun executeType(ref: DataFusionType, element: PsiElement, state: ResolveState): Boolean {
				if (element !is DataFusionObjectDeclaration) {
						return true
				}

				if (element.objectId.text == ref.text) {
						locatedObj = element
						resolved = PsiElementResolveResult(element.objectId)
						return false
				}

				return true
		}

}
