package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor

class DeclarationLookupPartialProcessor(private val reference: PsiElement) : PsiScopeProcessor {
		var resolved = mutableListOf<PsiElementResolveResult>()

		override fun execute(element: PsiElement, state: ResolveState): Boolean {
				val refText = reference.text.lowercase()

				when (element) {
						is ArcVarId -> {
								thisLogger().warn("[DeclarationLookupPartialProcessor] ArcVarId: ${element.text}")
						}

						is ArcStatement -> {
								element.variableDeclaration?.let {
										it.varId.text.lowercase().contains(refText).let { contains ->
												if (contains) {
														resolved.add(PsiElementResolveResult(it.varId))
												}
										}
								}
						}

						is ArcFuncReceiverName -> {
								// If the receiver name is the same as our reference
								// then we're trying to resolve the declaration of the receiver
								// For example, completion of fields/methods on the receiver type
								if (element.id.text == reference.text) {
										val receiver = element.parent as ArcFuncReceiverDeclaration
										resolved.add(PsiElementResolveResult(receiver.type))
								}

								element.id.text.lowercase().contains(refText).let { contains ->
										if (contains) {
												resolved.add(PsiElementResolveResult(element.id))
										}
								}
						}

						is ArcFuncReceiverDeclaration -> {
								element.funcReceiverName?.id?.text?.lowercase()?.contains(refText).let { contains ->
										if (contains!!) {
												resolved.add(PsiElementResolveResult(element.funcReceiverName?.id!!))
										}
								}
						}

						is ArcArgumentDeclaration -> {
								element.argumentId.text.lowercase().contains(refText).let { contains ->
										if (contains) {
												resolved.add(PsiElementResolveResult(element.argumentId))
										}
								}
						}

//						else -> {
//								thisLogger().warn("[DeclarationLookupPartialProcessor] Unknown element: ${element.javaClass.name} ${element.text}")
//						}

				}

				return true
		}


}
