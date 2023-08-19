package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.childrenOfType

abstract class ArcObjectImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcObject, NavigationItem {

		override fun getFields(): List<ArcObjectFieldDeclaration> {
				val body = this.childrenOfType<ArcObjectBody>().firstOrNull() ?: return listOf()

				return body.objectFieldDeclarationList
		}

		override fun getMember(name: String): ArcObjectFieldDeclaration? {
				return getFields().firstOrNull { it.objectFieldKey.id.text == name }
		}

		override fun getName(): @NlsSafe String? {
				val idEl = this.findChildByType<PsiElement?>(ArcTypes.OBJECT_ID)
				return idEl?.text
		}

		override fun getPresentation(): ItemPresentation? {
				return object : ItemPresentation {
						override fun getLocationString(): String {
								return containingFile.name
						}

						override fun getIcon(unused: Boolean): javax.swing.Icon? {
								return AllIcons.Nodes.Class
						}

						override fun getPresentableText(): String? {
								return name
						}
				}
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				val fields = getFields()
				for (field in fields) {
						if (!processor.execute(field, state)) {
								return false
						}
				}
				return true
		}
}
