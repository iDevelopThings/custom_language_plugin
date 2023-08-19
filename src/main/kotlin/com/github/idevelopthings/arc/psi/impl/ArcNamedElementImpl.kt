package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.ArcFuncId
import com.github.idevelopthings.arc.psi.ArcNamedElement
import com.github.idevelopthings.arc.psi.ArcObjectId
import com.github.idevelopthings.arc.psi.ArcVarId
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import javax.swing.Icon

abstract class ArcNamedElementImpl(node: ASTNode) :
		ASTWrapperPsiElement(node),
		ArcNamedElement, NavigationItem {

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

		override fun getName(): String {
				return nameIdentifier?.text ?: ""
		}

		override fun getNameIdentifier(): PsiElement? {
				return ArcPsiImplUtil.getNameIdentifier(this)
		}

		override fun setName(name: String): PsiElement {
				return ArcPsiImplUtil.setName(this, name)
		}

		override fun getReference(): PsiReference? {
				return ArcPsiImplUtil.getReference(this)
		}
}


//
//abstract class ArcNamedElementImpl<T : ArcNamedStub<*>>
//		: ArcStubbedElementImpl<T>, ArcNamedElement, ArcElementType {
//
//		constructor(node: ASTNode) : super(node)
//		constructor(stub: T, nodeType: IStubElementType<*, *>) : super(stub, nodeType)
//
//		override fun getIdentifyingElement(): PsiElement? {
//				return super.getIdentifyingElement()
//		}
//
//		override fun getNameIdentifier(): PsiElement? {
//				return null
//		}
//
//
//		@Throws(IncorrectOperationException::class)
//		override fun setName(name: @NlsSafe String): PsiElement? {
//				return null
//		}
//
//}
