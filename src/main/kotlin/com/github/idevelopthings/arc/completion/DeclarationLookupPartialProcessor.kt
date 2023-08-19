package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.psi.*
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor

class DeclarationLookupPartialProcessor(private val reference: PsiElement) : PsiScopeProcessor {
		var resolved = mutableListOf<PsiElementResolveResult>()

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				val refText = reference.text.lowercase()

				when (element) {
						is ArcStatement -> {
								element.variableDeclaration?.let {
										it.varId.text.lowercase().contains(refText).let { contains ->
												if (contains) {
														resolved.add(PsiElementResolveResult(it.varId))
												}
										}
								}
						}

						is ArcFuncReceiverDeclaration -> {
								element.funcReceiverName.id.text.lowercase().contains(refText).let { contains ->
										if (contains) {
												resolved.add(PsiElementResolveResult(element.funcReceiverName.id))
										}
								}
						}

						is ArcArgumentDeclaration -> {
								element.id.text.lowercase().contains(refText).let { contains ->
										if (contains) {
												resolved.add(PsiElementResolveResult(element.id))
										}
								}
						}

				}

				return true
		}


}
