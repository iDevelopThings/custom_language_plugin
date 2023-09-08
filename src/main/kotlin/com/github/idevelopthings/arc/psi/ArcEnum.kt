package com.github.idevelopthings.arc.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor

interface ArcEnum : ArcBaseDeclaration {
		override fun getPresentation(): ItemPresentation?
}

abstract class ArcEnumImpl(node: ASTNode) : ArcBaseDeclarationImpl(node),
		ArcEnum,
		NavigationItem,
		PsiElementWithLookup {

		override fun getName(): String? {
				val idEl = this.findChildByType<PsiElement?>(ArcTypes.ENUM_ID)
				return idEl?.text
		}

		override fun getPresentation(): ItemPresentation? {
				return object : ItemPresentation {
						override fun getLocationString(): String {
								return containingFile.name
						}

						override fun getIcon(unused: Boolean): javax.swing.Icon {
								return AllIcons.Nodes.Enum
						}

						override fun getPresentableText(): String? {
								return name
						}
				}
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				if (this is ArcEnumDeclaration) {
						for (valueDeclaration in this.enumFieldDeclarationList) {
								if (!processor.execute(valueDeclaration, state)) {
										return false
								}
						}
				}
				return true
		}


		override fun getLookupElement(): LookupElement {
				return ArcEnumLookupElement(this as ArcEnumDeclaration)
		}

}

class ArcEnumLookupElement(private val el: ArcEnumDeclaration) : LookupElement() {
		override fun getLookupString(): String {
				return el.name!!
		}

		override fun renderElement(presentation: LookupElementPresentation) {
				presentation.icon = AllIcons.Nodes.Enum
				presentation.itemText = el.getName()
				presentation.typeText = el.containingFile.name
				presentation.isTypeGrayed = true
		}
}

