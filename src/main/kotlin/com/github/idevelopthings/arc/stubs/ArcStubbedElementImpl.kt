package com.github.idevelopthings.arc.stubs

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubBase
import com.github.idevelopthings.arc.psi.ArcFile
import org.jetbrains.annotations.NotNull

open class ArcStubbedElementImpl<T : StubBase<*>> :
		StubBasedPsiElementBase<T> {

		constructor(node: ASTNode) : super(node)
		constructor(stub: T, nodeType: IStubElementType<*, *>) : super(stub, nodeType)


		override fun toString(): String {
				return getElementType().toString();
		}


		override fun getText(): String? {
//				val stub = getStub()
//				if (stub is TextHolder) {
//						val text: String = (stub as TextHolder?).getText()
//						if (text != null) return text
//				}
				return super.getText()
		}

		override fun getParent(): PsiElement {
				return getParentByStub();
		}
//
//override 		fun processDeclarations(@NotNull PsiScopeProcessor processo booleanr,
//		@NotNull ResolveState state,
//		PsiElement lastParent,
//		@NotNull PsiElement place) {
//				return GoCompositeElementImpl.processDeclarationsDefault(this, processor, state, lastParent, place);
//		}

		@NotNull
		override fun getContainingFile(): ArcFile {
				return super.getContainingFile() as ArcFile
		}

		@Override
		fun shouldGoDeeper(): Boolean {
				return true
		}

}
