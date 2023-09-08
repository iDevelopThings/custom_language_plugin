package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.data.ResolvableVarTypeInfo
import com.github.idevelopthings.arc.completion.processor.ResolverChain
import com.github.idevelopthings.arc.completion.resolution.ArgumentTypeResolver
import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveResult
import com.intellij.psi.ResolveState


class ArcArgumentReference(
		element: ArcResolvable,
		rangeInElement: TextRange?,
) : ArcReferenceBase<ArcResolvable>(element, rangeInElement) {

		override fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				val resolver = ResolverChain(element).apply {
						resolver(ArgumentTypeResolver())
						collector(ScopeCollector())
						filterFirst {
								it is ResolvableVarTypeInfo && it.name == (element as ArcSimpleRefExpr).id.text
						}
				}.resolve()

				if (resolver.results.isEmpty()) {
						thisLogger().warn("resolver.results is empty")
						return ResolveResult.EMPTY_ARRAY
				}

				val decl = resolver.first()
				if (decl != null) {
						return arrayOf(PsiElementResolveResult(decl.getDeclarationElement()!!))
				}

				return ResolveResult.EMPTY_ARRAY
		}

		override fun getVariants(): Array<Any> {
				val variants = mutableListOf<LookupElement>()
				/*
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
								} */

				return variants.toTypedArray()
		}

}
