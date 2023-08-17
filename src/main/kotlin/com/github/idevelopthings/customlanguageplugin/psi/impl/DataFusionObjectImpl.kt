package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.util.Iconable
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType

abstract class DataFusionObjectImpl(node: ASTNode) : ASTWrapperPsiElement(node), DataFusionObject, NavigationItem {

		override fun getFields(): List<DataFusionObjectFieldDeclaration> {
				val body = this.childrenOfType<DataFusionObjectBody>().firstOrNull() ?: return listOf()

				return body.objectFieldDeclarationList
		}


		override fun getName(): @NlsSafe String? {
				val idEl = this.findChildByType<PsiElement?>(DataFusionTypes.OBJECT_ID)
				return idEl?.text
		}

		override fun getPresentation(): ItemPresentation? {
				return object : ItemPresentation {
						override fun getLocationString(): String {
								return containingFile.name
						}

						override fun getIcon(unused: Boolean): javax.swing.Icon? {
								return getIcon(Iconable.ICON_FLAG_VISIBILITY)
						}

						override fun getPresentableText(): String? {
								return name
						}
				}
		}
}
