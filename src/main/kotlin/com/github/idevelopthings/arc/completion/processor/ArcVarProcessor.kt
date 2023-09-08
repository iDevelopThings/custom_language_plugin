package com.github.idevelopthings.arc.completion.processor

import com.github.idevelopthings.arc.psi.ArcArgumentDeclaration
import com.github.idevelopthings.arc.psi.ArcBaseDeclaration
import com.github.idevelopthings.arc.psi.ArcFuncReceiverName
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.util.childrenOfType

class ArcVarProcessor(origin: PsiElement) : ArcBaseScopeProcessor(origin) {

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				super.execute(element, state)
				// element.childrenOfType<ArcBaseDeclaration>()
				// 		.forEach { super.execute(it, state) }

				return true
		}

		// override fun canAdd(element: PsiElement): Boolean {
		// 		return super.canAdd(element) && element is ArcBaseDeclaration
		// }

		override fun canAdd(element: PsiElement): Boolean {
				return when (element) {
						is ArcBaseDeclaration -> true
						is ArcFuncReceiverName -> true
						is ArcArgumentDeclaration -> true
						else -> false
				}
		}

}
