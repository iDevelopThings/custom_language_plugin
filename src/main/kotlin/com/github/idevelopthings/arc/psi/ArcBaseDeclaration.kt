package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.model.psi.PsiSymbolReference
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry


interface ArcBaseDeclaration : PsiNameIdentifierOwner, NavigationItem, ArcResolvable, PsiElementWithLookup {
		override fun getName(): String?
		override fun setName(name: String): PsiElement?
		override fun getNameIdentifier(): PsiElement?
		override fun getIdentifier(): PsiElement
		override fun getReferences(): Array<PsiReference>
		override fun getOwnReferences(): MutableCollection<out PsiSymbolReference>
}

abstract class ArcBaseDeclarationImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcBaseDeclaration, PsiNameIdentifierOwner, ArcResolvable {

		override fun getName(): String? {
				return nameIdentifier?.text
		}

		override fun setName(name: String): PsiElement? {
				return ArcPsiUtilImpl.setName(this, name)
		}

		override fun getNameIdentifier(): PsiElement? {
				return getIdentifier()
		}

		override fun getLookupElement(): LookupElement {
				return ArcPsiUtilImpl.getLookupElement(this)!!
		}

		override fun getIdentifier(): PsiElement {
				return when (this) {
						is ArcEnumDeclaration -> this.enumId
						is ArcFuncDeclaration -> this.funcId!!
						is ArcObjectDeclaration -> this.objectId
						is ArcObjectFieldDeclaration -> this.objectFieldKey!!
						is ArcVariableDeclaration -> this.varId
						is ArcEnumFieldDeclaration -> this.id
						else -> this
				}
		}

		override fun getReferences(): Array<PsiReference> {
				return when (this) {
						is ArcVariableDeclaration -> ReferenceProvidersRegistry.getReferencesFromProviders(this.varId)
						else -> return ReferenceProvidersRegistry.getReferencesFromProviders(this)
				}
		}

		override fun getOwnReferences(): MutableCollection<out PsiSymbolReference> {
				// if (this is ArcVariableDeclaration) {
				// 		val refs = PsiReferenceService.getService().getReferences(this, PsiReferenceService.Hints.NO_HINTS)
				// 		val res = refs.filterIsInstance<ArcVarReference>().toMutableList()
				//
				// 		return res
				// }

				return mutableListOf()
		}
}
