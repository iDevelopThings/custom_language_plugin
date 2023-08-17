package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.psi.DataFusionBlockBody
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionFuncId
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionFunction
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil


open class DataFusionFunctionImpl(node: ASTNode) : ASTWrapperPsiElement(node), DataFusionFunction {

		override fun getNameIdentifier(): DataFusionFuncId {
				return findChildByType(DataFusionTypes.FUNC_ID)!!
		}

		override fun getName(): String {
				return getNameIdentifier().text
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				val block = PsiTreeUtil.findChildOfType(this, DataFusionBlockBody::class.java)
				block?.statementList?.forEach {
						if (!processor.execute(it, state)) {
								return false
						}
				}

				return true
		}

}
