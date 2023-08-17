package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.github.idevelopthings.customlanguageplugin.DataFusionUtil
import com.github.idevelopthings.customlanguageplugin.Icons
import com.github.idevelopthings.customlanguageplugin.completion.DeclarationProcessor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.startOffset


class DataFusionReference(element: PsiElement, rangeInElement: TextRange?) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {

//		val key: String = element.text.substring(rangeInElement!!.startOffset, rangeInElement.endOffset)

		override fun resolve(): PsiElement? {
				val results = multiResolve(false)
				return if (results.size == 1) results[0].element else null
		}

		override fun multiResolve(p0: Boolean): Array<ResolveResult> {
//				val project = myElement.project
//				val objects = DataFusionUtil.findObjectDeclarations(project, key)
//				val res = mutableListOf<ResolveResult>()
//
//				objects.forEach {
//						res.add(PsiElementResolveResult(it))
//				}
//				return res.toTypedArray()

				val res = mutableListOf<ResolveResult>()

				val processor = DeclarationProcessor(element)

				PsiTreeUtil.treeWalkUp(
						processor,
						element,
						element.containingFile,
						ResolveState.initial()
				)

				if (processor.resolved != null) {
						res.add(processor.resolved!!)

						return res.toTypedArray()
				}

				if (element is DataFusionType) {
						val project = myElement.project
						DataFusionUtil.findObjectDeclarations(project, element.text).forEach {
								res.add(PsiElementResolveResult(it.objectId))
						}
				}

				return res.toTypedArray()
		}

		override fun getVariants(): Array<Any> {
				val project = myElement.project
				val objects = DataFusionUtil.findObjectDeclarations(project)

				val variants = mutableListOf<LookupElement>()

				objects.forEach {
						it.getFields().forEach {
								variants.add(
										LookupElementBuilder
												.create(it.objectFieldKey.id)
												.withIcon(Icons.LogoLight)
												.withTypeText(it.containingFile.name)
												.withPsiElement(it.objectFieldKey)
								)
//								variants.add(PsiElementResolveResult(it))
						}
				}

				return variants.toTypedArray()
		}

}
