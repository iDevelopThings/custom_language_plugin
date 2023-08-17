package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.github.idevelopthings.customlanguageplugin.DataFusionUtil
import com.github.idevelopthings.customlanguageplugin.Icons

//
//class DataFusionPsiTypeReference(element: PsiElement, textRange: TextRange) :
//		PsiReferenceBase<PsiElement?>(element, textRange), PsiPolyVariantReference {
//
//		private val key: String
//
//		init {
//				key = element.text.substring(textRange.startOffset, textRange.endOffset)
//		}
//
//		override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
//				return DataFusionUtil.findTypeDefinitions(myElement!!.project, key)
//						.map { PsiElementResolveResult(it) }
//						.toTypedArray()
//		}
//
//		override fun resolve(): PsiElement? {
//				val resolveResults: Array<ResolveResult> = multiResolve(false)
//				return if (resolveResults.size == 1) resolveResults[0].element else null
//		}
//
//
////		override fun getVariants(): Array<out Any> {
////				return DataFusionUtil.findTypeDefinitions(myElement!!.project)
////						.map { LookupElementBuilder.create(it).withIcon(Icons.LogoLight).withTypeText(it.containingFile.name) }
////						.toTypedArray()
////		}
//}
