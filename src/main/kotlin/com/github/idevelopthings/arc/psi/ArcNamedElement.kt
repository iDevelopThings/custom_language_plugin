package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.model.Pointer
import com.intellij.model.Symbol
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference
import javax.swing.Icon


interface ArcNamedElement : PsiNameIdentifierOwner,
		NavigationItem,
		/* PsiSymbolDeclaration, */
		ArcResolvable {
		override fun getPresentation(): ItemPresentation?

		override fun getNameIdentifier(): PsiElement?

		// override fun getTextOffset(): Int

		override fun getName(): String?

		override fun getReference(): PsiReference?

		//		override fun getReferences(): Array<PsiReference>
		// fun findChildDeclaration(refName: String): ArcNamedElement?

		// override fun getDeclaringElement(): PsiElement
		// override fun getRangeInDeclaringElement(): TextRange

		// override fun getSymbol(): Symbol
}

abstract class ArcNamedElementImpl(node: ASTNode) :
		ASTWrapperPsiElement(node),
		ArcNamedElement,
		NavigationItem,
		/* PsiSymbolDeclaration, */
		ArcResolvable
/*PsiSymbolDeclaration, Symbol*/ {

		override fun getIdentifier(): PsiElement {
				return nameIdentifier ?: this
		}

		override fun getPresentation(): ItemPresentation? {
				return object : ItemPresentation {
						override fun getPresentableText() = name
						override fun getLocationString() = containingFile.name
						override fun getIcon(unused: Boolean): Icon {
								return when (this@ArcNamedElementImpl) {
										is ArcObjectId -> AllIcons.Nodes.Class
										is ArcFuncId -> AllIcons.Nodes.Function
										is ArcVarId -> AllIcons.Nodes.Variable
										else -> AllIcons.Nodes.Type
								}
						}
				}
		}

		/* override fun findChildDeclaration(refName: String): ArcNamedElement? {
				when (this) {
						is ArcObjectId -> {
								val objDecl = getDeclaringElement() as ArcObjectDeclaration
								val member = objDecl.getMember(refName) ?: return null

								return member.type.reference?.resolve() as ArcNamedElement
						}
				}
				return null
		} */

		override fun getName(): String {
				return nameIdentifier?.text ?: ""
		}

		override fun getNameIdentifier(): PsiElement? {
				return ArcPsiUtilImpl.getNameIdentifier(this)
		}

		override fun setName(name: String): PsiElement {
				return ArcPsiUtilImpl.setName(this, name)
		}

		override fun getReference(): PsiReference? {
				return ArcPsiUtilImpl.getReference(this)
		}

//		override fun getReferences(): Array<PsiReference> {
//				return ReferenceProvidersRegistry.getReferencesFromProviders(this)
//		}

		// override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()


		/* override fun getDeclaringElement(): PsiElement {
				return when (this) {
						is ArcObjectId -> (this as ArcObjectId).parent
						is ArcFuncId -> (this as ArcFuncId).parent
						is ArcVarId -> (this as ArcVarId).parent
						is ArcFuncReceiverName -> (this as ArcFuncReceiverName).parent.parent
						else -> this
				}
		} */

		// Implementors should also override PsiElement.getTextOffset() to
		// return the relative offset of the identifier token.

/* 		override fun getTextOffset(): Int {
				val id = this.findChildByType<PsiElement>(ArcTypes.ID) ?: return 0

				if (this is ArcVarId) {
						return super.getTextOffset()
				}

				return id.startOffset - this.textRange.startOffset
		} */

		/* override fun getRangeInDeclaringElement(): TextRange {
				if (this.nameIdentifier == null) return TextRange.EMPTY_RANGE

				val id = this.nameIdentifier!!

				// when(this) {
				// 		is ArcFuncReceiverName -> {
				// 				val startOffset: Int = id.startOffset - declaringElement.textRange.startOffset
				// 				val range = TextRange(startOffset, startOffset + id.textLength)
				// 				return range
				// 		}
				// }

				val declRange = declaringElement.textRange
				val startOffset: Int = id.startOffset - declRange.startOffset
				val range = TextRange(startOffset, declRange.endOffset)

				return range
		}

		override fun getSymbol(): Symbol {
				return NamedElementSymbol(this)
		} */


}

class NamedElementSymbol(private val el: ArcNamedElement) : Symbol {
		override fun createPointer(): Pointer<out Symbol> {
				return Pointer.hardPointer(this)
		}

}

