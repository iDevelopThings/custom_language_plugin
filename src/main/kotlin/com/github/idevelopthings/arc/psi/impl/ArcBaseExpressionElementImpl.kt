package com.github.idevelopthings.arc.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.github.idevelopthings.arc.psi.ArcBaseExpressionElement

abstract class ArcBaseExpressionElementImpl(node: ASTNode) :
		ASTWrapperPsiElement(node),
		ArcBaseExpressionElement {

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
