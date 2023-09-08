package com.github.idevelopthings.arc.completion.collector

import com.github.idevelopthings.arc.ArcUtil
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState


class TopLevelDeclarationCollector : BaseTypeCollector() {

		override fun collect(element: PsiElement) {
				val resolveState = ResolveState.initial()

				ArcUtil.forAllFiles(element.project) { file ->
						file.getDeclarations()!!.forEach { declaration ->
								processor.execute(declaration, resolveState)
						}
				}
		}

}
