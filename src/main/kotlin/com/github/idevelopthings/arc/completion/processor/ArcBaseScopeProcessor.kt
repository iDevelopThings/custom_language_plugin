package com.github.idevelopthings.arc.completion.processor

import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.util.containers.OrderedSet

abstract class ArcScopeProcessor : PsiScopeProcessor {
}

abstract class ArcBaseScopeProcessor(
		protected var origin: PsiElement
) : ArcScopeProcessor() {

		protected var requestedName: String? = null
		protected var isCompletion = false
		protected var results = OrderedSet<ArcResolvable>()

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				if (element !is ArcResolvable) return true
				if (element == origin) return true
				// if (requestedName != null && element.name != requestedName) return true
				if (!canAdd(element)) return true

				return !results.add(element)
		}

		protected open fun canAdd(element: PsiElement): Boolean {
				return true
		}

		fun getResult(): ArcResolvable? {
				return results.firstOrNull()
		}

		fun getResults(): List<ArcResolvable> {
				return results.toList()
		}

}
