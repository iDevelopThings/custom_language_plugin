package com.github.idevelopthings.arc.structure

import com.github.idevelopthings.arc.psi.ArcFuncDeclaration
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class ArcStructureViewModel(editor: Editor?, psiFile: PsiFile) :
		StructureViewModelBase(psiFile, editor, ArcStructureViewElement(psiFile)), StructureViewModel.ElementInfoProvider {

		override fun getSorters(): Array<out Sorter> {
				return arrayOf(Sorter.ALPHA_SORTER)
		}

		override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
				return false
		}

		override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean {
				//return when (element?.value) {
				//		is ArcFuncDeclaration -> true
				//		is ArcObjectDeclaration -> true
				//		else -> false
				//}
				return false
		}


		override fun getSuitableClasses(): Array<out Class<*>> {
				return arrayOf(
						ArcFuncDeclaration::class.java,
						ArcObjectDeclaration::class.java
				)
		}
}
