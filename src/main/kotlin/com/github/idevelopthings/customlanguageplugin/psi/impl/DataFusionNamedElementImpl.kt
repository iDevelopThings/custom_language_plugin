package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionNamedElement

abstract class DataFusionNamedElementImpl(node: ASTNode) :
		ASTWrapperPsiElement(node),
		DataFusionNamedElement {

		override fun getNameIdentifier(): PsiElement? {
				return DataFusionPsiImplUtil.getNameIdentifier(this)
		}

		override fun setName(name: String): PsiElement {
				return DataFusionPsiImplUtil.setName(this, name)
		}

		override fun getReference(): PsiReference? {
				return DataFusionPsiImplUtil.getReference(this)
		}
}


//
//abstract class DataFusionNamedElementImpl<T : DataFusionNamedStub<*>>
//		: DataFusionStubbedElementImpl<T>, DataFusionNamedElement, DataFusionElementType {
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
