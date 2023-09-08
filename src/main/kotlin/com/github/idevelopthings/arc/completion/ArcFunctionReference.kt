package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.Icons
import com.github.idevelopthings.arc.psi.ArcType
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

/* class ArcFunctionReference(element: PsiElement, rangeInElement: TextRange?) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {

		override fun resolve(): PsiElement? {
				val results = multiResolve(false)
				return if (results.size == 1) results[0].element else null
		}

		override fun multiResolve(p0: Boolean): Array<ResolveResult> {
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

				if (element is ArcType) {
						val project = myElement.project
						ArcUtil.findObjectDeclarations(project, element.text).forEach {
								res.add(PsiElementResolveResult(it.objectId))
						}
				}

				return res.toTypedArray()
		}

		 *//*override fun getVariants(): Array<Any> {
				val project = myElement.project
				val objects = ArcUtil.findObjectDeclarations(project)

				val variants = mutableListOf<LookupElement>()

				objects.forEach {
						it.getFields().forEach {
								variants.add(
										LookupElementBuilder.create(it.objectFieldKey.id)
												.withIcon(Icons.LogoLight)
												.withTypeText(it.containingFile.name)
												.withPsiElement(it.objectFieldKey)
								)
//								variants.add(PsiElementResolveResult(it))
						}
				}

				return variants.toTypedArray()
		}*//*

} */
