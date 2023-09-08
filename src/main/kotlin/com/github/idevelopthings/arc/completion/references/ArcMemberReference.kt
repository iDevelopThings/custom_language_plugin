package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.collector.TopLevelDeclarationCollector
import com.github.idevelopthings.arc.completion.data.*
import com.github.idevelopthings.arc.completion.processor.ResolverChain
import com.github.idevelopthings.arc.completion.resolution.ArgumentTypeResolver
import com.github.idevelopthings.arc.completion.resolution.FuncReceiverTypeResolver
import com.github.idevelopthings.arc.completion.resolution.GlobalDeclarationResolver
import com.github.idevelopthings.arc.completion.resolution.VarTypeResolver
import com.github.idevelopthings.arc.psi.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.model.Symbol
import com.intellij.model.psi.PsiCompletableReference
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.PsiTreeUtil


class ArcMemberReference(
		element: ArcRefExpr,
		rangeInElement: TextRange?,
) : ArcReferenceBase<ArcRefExpr>(element, rangeInElement), PsiCompletableReference {

		var memberType: TypeInfo? = null
		var memberDeclaration: TypeInfo? = null

		// override fun resolve(): PsiElement? {
		// val results = multiResolve(false)
		// if (memberDeclaration != null) {
		// 		val e = memberDeclaration!!.getDeclarationElement()
		// 		if (e is ArcFuncReceiverName) {
		// 				return e.id
		// 		}
		// }
		//
		// return if (results.size == 1) results[0].element else null
		// }
		override fun resolve(): PsiElement? {
				val resolveResults = multiResolve()
				val el = resolveResults.firstOrNull()?.element ?: return null

				return el
		}

		override fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				val segments = ArcPsiUtil.getMemberAccessSegments(element)
				var decl: TypeInfo? = null

				val resolver = ResolverChain(element).apply {
						resolver(GlobalDeclarationResolver())
						resolver(VarTypeResolver())
						resolver(FuncReceiverTypeResolver())
						resolver(ArgumentTypeResolver())
						collector(TopLevelDeclarationCollector())
						collector(ScopeCollector())
						withDeepLoading()
						onEntry { decl = it }
				}

				if (segments.size == 1) {
						resolver.filterFirst {
								if (it.name != (element as ArcSimpleRefExpr).id.text)
										return@filterFirst false

								true
						}

						resolver.resolve()

						if (decl == null) {
								thisLogger().warn("decl is null")
								return ResolveResult.EMPTY_ARRAY
						}

						memberType = (decl as TypeInfo).type
						memberDeclaration = decl

						val baseDecl = (decl as TypeInfo).getDeclarationElement()
						if (baseDecl == null) {
								thisLogger().warn("baseDecl is null")
								return ResolveResult.EMPTY_ARRAY
						}

						return arrayOf(PsiElementResolveResult(baseDecl))
				}

				val current = segments.removeFirst()
				var ref = current.reference?.resolve()
				if (ref == null) {
						thisLogger().warn("Reference is null")
						return ResolveResult.EMPTY_ARRAY
				}

				if (ref !is ArcVarId) {
						if (ref !is ArcVariableDeclaration) {
								return ResolveResult.EMPTY_ARRAY
						}
						ref = ref.varId
				}

				resolver.setElement(ref)
				resolver.onTypeCreated { type ->
						// So...
						// If we don't have a next segment, our reference is the field on the type
						// If we do... then we're accessing a field on another type, so our reference is the type

						if (type !is MemberTypeInfo) return@onTypeCreated
						if (segments.isEmpty()) return@onTypeCreated

						val currentName = segments[0].id.text
						if (type.name != currentName) return@onTypeCreated

						decl = type
						memberDeclaration = type
						memberType = type.type

						if (segments.size > 1) {
								segments.removeFirst()
								decl = type.type
						}

				}

				resolver.resolve()

				return arrayOf(
						PsiElementResolveResult(
								(decl as DeclarationTypeInfo).baseDeclaration!!
						)
				)
		}

		override fun getAbsoluteRange(): TextRange {
				TODO("Not yet implemented")
		}

		override fun resolveReference(): MutableCollection<out Symbol> {
				TODO("Not yet implemented")
		}

		override fun getCompletionVariants(): MutableCollection<LookupElement> {
				TODO("Not yet implemented")
		}

}

class SimpleTestReference(element: PsiElement) : PsiReferenceBase<PsiElement>(element) {

		override fun resolve(): PsiElement? {

				val results = mutableListOf<PsiElement>()

				val outerScope = ArcPsiUtil.getOuterScopeBlock(element)

				PsiTreeUtil.treeWalkUp(
						{ el, state ->
								if (el is ArcVariableDeclaration) {
										if (el.varId.text == element.text) {
												results.add(el.varId)
												return@treeWalkUp false
										}
								}

								true
						},
						element,
						outerScope?.parent,
						ResolveState.initial()
				)

				return results.firstOrNull()
		}

}
