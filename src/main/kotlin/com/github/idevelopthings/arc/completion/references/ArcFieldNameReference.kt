package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveResult

class ArcFieldNameReference(
		element: ArcNamedElement,
		rangeInElement: TextRange?,
) : ArcReferenceBase<ArcNamedElement>(element, rangeInElement) {

		override fun resolve(): PsiElement? {
				return super.resolve()
		}

		// override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
		// 		return super.multiResolve(incompleteCode)
		// }

}
