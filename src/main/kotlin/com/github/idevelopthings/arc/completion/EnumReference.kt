package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.Icons
import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.github.idevelopthings.arc.psi.ArcType
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

class EnumReference(
		element: PsiElement,
		rangeInElement: TextRange?,
) : PsiReferenceBase<PsiElement>(element, rangeInElement), PsiPolyVariantReference {

		override fun resolve(): PsiElement? {
				val results = multiResolve(false)
				return if (results.size == 1) results[0].element else null
		}

		override fun multiResolve(p0: Boolean): Array<ResolveResult> {
				val res = mutableListOf<ResolveResult>()
				val project = myElement.project

				val files = ArcUtil.getAllFiles(project)
				for (file in files) {
						file?.getEnumDeclarations()?.forEach {
								it.enumId.let {
										val el = (element as ArcSimpleRefExpr)
										if (it.id.text == el.id.text)
												res.add(PsiElementResolveResult(it))
								}
						}
				}

				return res.toTypedArray()
		}

		override fun getVariants(): Array<Any> {
				val project = myElement.project
//				val objects = ArcUtil.findObjectDeclarations(project)

				val variants = mutableListOf<LookupElement>()

				val files = ArcUtil.getAllFiles(project)
				for (file in files) {
						file?.getEnumDeclarations()?.forEach {
								it.enumId.let {
										variants.add(
												LookupElementBuilder.create(it.id.text!!)
														.withIcon(AllIcons.Nodes.Enum)
														.withTypeText(file.name)
										)
								}
						}
				}




				return variants.toTypedArray()
		}

		override fun isReferenceTo(element: PsiElement): Boolean {
				return super.isReferenceTo(element)
		}

}
