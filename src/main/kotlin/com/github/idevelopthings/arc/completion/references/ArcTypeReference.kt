package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.psi.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveResult
import com.intellij.psi.ResolveState

class ArcTypeReference(
		element: ArcNamedElement,
		rangeInElement: TextRange?,
) : ArcReferenceBase<ArcNamedElement>(element, rangeInElement) {

		companion object {

				val RESOLVE_TYPE_BASE_DECLARATION_ONLY_KEY = Key.create<Boolean>("RESOLVE_TYPE_BASE_DECLARATION_ONLY")

		}
		// override fun resolve(): PsiElement? {
		// 		return super.resolve()
		// }
		//
		// override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		// 		return super.multiResolve(incompleteCode)
		// }

		override fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				val results = mutableListOf<ResolveResult>()

				val isSingleLookup = state?.get(RESOLVE_TYPE_BASE_DECLARATION_ONLY_KEY) == true
				val files = ArcUtil.getAllFiles(myElement.project)

				/* ArcUtil.findAllDeclarations(myElement.project)
						.filter { it.name == myElement.name }
						.forEach { results.add(PsiElementResolveResult(it.getIdentifier())) }

				if (isSingleLookup) {
						return results.firstOrNull()?.let {
								arrayOf(it)
						} ?: ResolveResult.EMPTY_ARRAY
				} */


				for (file in files) {
						val decls = file!!.getTopLevelDeclarations()!!
						for (topLevelDecl in decls) {
								val decl = topLevelDecl.getDeclaration() ?: continue

								if (decl !is ArcBaseDeclaration) {
										continue
								}

								if (decl.name == myElement.name)
										results.add(PsiElementResolveResult(decl.getIdentifier()))
						}

						if (isSingleLookup && results.size == 1) {
								break
						}
				}

				return results.toTypedArray()
		}


		override fun getVariants(): Array<Any> {
				val project = myElement.project
				val variants = mutableListOf<LookupElement>()
				val declVariants = mutableListOf<PsiElement>()

				val resolvedType = resolveOrGet(null as ResolveState?)
				if (resolvedType.isEmpty()) {
						return emptyArray()
				}

				for (type in resolvedType) {
						val element = type.element?.parent
						if (element is ArcEnumDeclaration) {
								element.enumFieldDeclarationList.forEach {
										declVariants.add(it)
										variants.add(ArcEnumValueLookupElement(it))
								}
						}
						if (element is ArcObjectDeclaration) {
								element.objectFieldDeclarationList.forEach {
										declVariants.add(it)
										variants.add(ArcObjectFieldLookupElement(it))
								}
								element.getMethods().forEach {
										declVariants.add(it)
										variants.add(it.getLookupElement())
								}
						}
				}

				return listOf(*variants.toTypedArray(), *declVariants.toTypedArray()).toTypedArray()
		}
}
