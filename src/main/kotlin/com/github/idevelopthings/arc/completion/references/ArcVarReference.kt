package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.data.DeclarationTypeInfo
import com.github.idevelopthings.arc.completion.data.ResolvableVarTypeInfo
import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.github.idevelopthings.arc.completion.data.VarTypeInfo
import com.github.idevelopthings.arc.completion.processor.ResolverChain
import com.github.idevelopthings.arc.completion.resolution.FuncReceiverTypeResolver
import com.github.idevelopthings.arc.completion.resolution.VarTypeResolver
import com.github.idevelopthings.arc.psi.ArcFuncReceiverName
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.github.idevelopthings.arc.psi.ArcNamedElementImpl
import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.model.Symbol
import com.intellij.model.psi.PsiCompletableReference
import com.intellij.model.psi.PsiSymbolReferenceService
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class PlsReference(
		element: PsiElement,
		rangeInElement: TextRange?,
) : PsiReferenceBase<PsiElement>(element, rangeInElement) {
		override fun resolve(): PsiElement? {
				return null
		}

}

class ArcVarReference(
		element: ArcResolvable,
		rangeInElement: TextRange?,
) : ArcReferenceBase<ArcResolvable>(element, rangeInElement)
/* , PsiCompletableReference */ {

		override fun resolve(): PsiElement? {
				return resolveOrGet(null).firstOrNull()?.element?.let {
						it
				}
		}

		override fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				val resolver = ResolverChain(element).apply {
						resolver(VarTypeResolver())
						resolver(FuncReceiverTypeResolver())
						collector(ScopeCollector())
						filterFirst {
								(it is VarTypeInfo || it is ResolvableVarTypeInfo) && it.name == (element as ArcSimpleRefExpr).id.text
						}
						withDeepLoading(true)
				}.resolve()

				if (resolver.results.isEmpty()) {
						thisLogger().warn("resolver.results is empty")
						return ResolveResult.EMPTY_ARRAY
				}

				val decl = resolver.first()
				if (decl != null) {
						if (decl is DeclarationTypeInfo) {
								return arrayOf(PsiElementResolveResult(decl.baseDeclaration!!))
						}

						if (decl is ResolvableVarTypeInfo) {
								return arrayOf(PsiElementResolveResult(decl.originalDeclaration))
						}

						return arrayOf(PsiElementResolveResult(decl.getDeclarationElement()!!))
				}

				return ResolveResult.EMPTY_ARRAY
		}

		override fun getVariants(): Array<Any> {
				val project = myElement.project

				val variants = mutableListOf<LookupElement>()

				// val files = ArcUtil.getAllFiles(project)
				// for (file in files) {
				// 		file?.getEnumDeclarations()?.forEach {
				// 				it.enumId.let {
				// 						variants.add(
				// 								LookupElementBuilder.create(it.id.text!!)
				// 										.withIcon(AllIcons.Nodes.Enum)
				// 										.withTypeText(file.name)
				// 						)
				// 				}
				// 		}
				// }

				return variants.toTypedArray()
		}

		/* override fun getAbsoluteRange(): TextRange {
				TODO("Not yet implemented")
		}

		override fun resolveReference(): MutableCollection<out Symbol> {
				val service = service<PsiSymbolReferenceService>()
				val references = service.getReferences(element).asSequence()
						.filterIsInstance<ArcVarReference>()

				val refs = mutableListOf<Symbol>()

				for (reference in references) {
						val resolve = reference.resolve()
						if (resolve is ArcNamedElement) {
								refs.add(resolve.symbol)
						}
				}

				return refs
		}

		override fun getCompletionVariants(): MutableCollection<LookupElement> {
				TODO("Not yet implemented")
		} */

}
