package com.github.idevelopthings.arc.completion.collector

import com.github.idevelopthings.arc.completion.processor.ArcBaseScopeProcessor
import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.intellij.psi.PsiElement

interface TypeCollector {
		fun preCollect(element: PsiElement)
		fun collect(element: PsiElement)
		fun getResults(): List<ArcResolvable>
}

abstract class BaseTypeCollector : TypeCollector {

		protected lateinit var processor: ArcBaseScopeProcessor

		override fun preCollect(element: PsiElement) {
				processor = object : ArcBaseScopeProcessor(element) {
				}
		}

		override fun getResults(): List<ArcResolvable> {
				return processor.getResults()
		}

}
