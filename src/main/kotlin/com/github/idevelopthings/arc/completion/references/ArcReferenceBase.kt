package com.github.idevelopthings.arc.completion.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache


abstract class ArcReferenceBase<T : ArcResolvable>(
		element: PsiElement,
		rangeInElement: TextRange?,
) : PsiReferenceBase<T>(element as T, rangeInElement) {

		constructor(element: T) : this(element, null)

		protected var context: ResolveState? = null

		fun resolve(context: ResolveState?): PsiElement? {
				this.context = context
				return resolve()
		}

		override fun resolve(): PsiElement? {
				val resolveResults = multiResolve()
				if (resolveResults.size == 1)
						return resolveResults[0].element

				return resolveResults.firstOrNull()?.element
		}

		fun multiResolve(): Array<ResolveResult> {
				return resolveOrGet(null as ResolveState?)
		}

		open fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				return ResolveResult.EMPTY_ARRAY
		}

}


/* abstract class ArcReferenceBase<T : ArcResolvable>(
		element: PsiElement,
		rangeInElement: TextRange?,
) : PsiPolyVariantReferenceBase<T>(element as T, rangeInElement), PsiPolyVariantReference {

		constructor(element: T) : this(element, null)


		fun resolve(state: ResolveState?): PsiElement? {
				return if (state == null) {
						this.resolve()
				} else {
						val resolveResults = this.resolveOrGet(state)
						if (resolveResults.size == 1) resolveResults[0].element else null
				}
		}

		override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
				if (!(myElement as ArcResolvable).isValid) {
						return ResolveResult.EMPTY_ARRAY
				}

				val project = (myElement as ArcResolvable).project

				return ResolveCache
						.getInstance(project)
						.resolveWithCaching(this, getCachingResolver(), false, false)
		}

		open fun resolveOrGet(state: ResolveState?): Array<ResolveResult> {
				return ResolveResult.EMPTY_ARRAY
		}

		fun getCachingResolver(): ResolveCache.PolyVariantResolver<PsiPolyVariantReferenceBase<*>> {
				return ResolveCache.PolyVariantResolver { psiPolyVariantReferenceBase: PsiPolyVariantReferenceBase<*>, _: Boolean ->
						(psiPolyVariantReferenceBase as ArcReferenceBase).resolveOrGet(null as ResolveState?)
				}
		}
} */
