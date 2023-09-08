package com.github.idevelopthings.arc.completion.collector

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.processor.ArcVarProcessor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.util.PsiTreeUtil


class ScopeCollector : BaseTypeCollector() {

		override fun preCollect(element: PsiElement) {
				processor = ArcVarProcessor(element)
		}

		override fun collect(element: PsiElement) {
				try {

						val outerScope = ArcPsiUtil.getOuterScopeBlock(element)

						PsiTreeUtil.treeWalkUp(
								processor,
								element,
								outerScope?.parent,
								ResolveState.initial()
						)

				} catch (e: Exception) {
						if (e is ProcessCanceledException) throw e

						thisLogger().error("Error collecting scope", e)
				}
		}

}
