package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.impl.ArcExpressionImpl
import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import com.intellij.psi.tree.IElementType


interface ArcIdReferenceExpr : ArcResolvable, ArcExpression {

		fun isStatic(): Boolean

		fun getIdElement(): PsiElement

		override fun getReference(): PsiReference?
		override fun getReferences(): Array<PsiReference>

		fun isMemberAccess(): Boolean

}

open class ArcIdReferenceExprImpl(node: ASTNode) : ArcExpressionImpl(node), ArcIdReferenceExpr {

		override fun isStatic(): Boolean {
				return (this as ArcRefExpr).coloncolon != null
		}

		override fun getIdElement(): PsiElement {
				return this.findChildByType(ArcTypes.ID)!!
		}

		override fun getReference(): PsiReference? {
				return ArcPsiUtilImpl.getReference(this)
		}

		override fun getReferences(): Array<PsiReference> {
				return ReferenceProvidersRegistry.getReferencesFromProviders(this)
		}

		override fun isMemberAccess(): Boolean {
				if (parent is ArcCallExpr) {
						return false
				}
				if (this is ArcRefExpr) {
						return this.expression is ArcIdReferenceExpr
				}

				return false
		}

		override fun getIdentifier(): PsiElement {
				return this.getIdElement()
		}


}
