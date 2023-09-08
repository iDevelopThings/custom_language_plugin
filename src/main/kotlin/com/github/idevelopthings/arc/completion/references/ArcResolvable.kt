package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.ResolveState
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

interface ArcResolvable : PsiElement {

		fun getIdentifier(): PsiElement

		override fun getReference(): PsiReference?

		override fun getReferences(): Array<PsiReference>

		fun resolve(): PsiElement? {
				return this.resolve(null as ResolveState?)
		}

		fun resolve(context: ResolveState?): PsiElement? {
				val reference = getReference()
				return if (reference is ArcReferenceBase<*>) {
						(reference as ArcReferenceBase<*>?)?.resolve(context)
				} else {
						reference?.resolve()
				}
		}
}

/**
 * Should only be used as a base for bnf nodes which we don't want to add full wrapper/impl classes for
 */
abstract class ArcBaseResolvable(node: ASTNode) : ASTWrapperPsiElement(node), ArcResolvable {

		override fun getIdentifier(): PsiElement {
				return ArcPsiUtilImpl.getArcResolvableIdentifier(this)!!
		}

		override fun getReference(): PsiReference? {
				return ArcPsiUtilImpl.getReference(this)
		}

		override fun getReferences(): Array<PsiReference> {
				return ReferenceProvidersRegistry.getReferencesFromProviders(this)
		}


}
