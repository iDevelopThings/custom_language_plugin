package com.github.idevelopthings.arc.structure

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement

class ArcStructureViewElement(val element: NavigatablePsiElement) : StructureViewTreeElement, SortableTreeElement {

		override fun getValue(): Any {
				return element
		}

		override fun navigate(requestFocus: Boolean) {
				element.navigate(requestFocus)
		}

		override fun canNavigate(): Boolean {
				return element.canNavigate()
		}

		override fun canNavigateToSource(): Boolean {
				return element.canNavigateToSource()
		}

		override fun getAlphaSortKey(): String {
				return element.name ?: ""
		}

		override fun getPresentation(): ItemPresentation {
				return element.presentation ?: PresentationData()
		}

		override fun getChildren(): Array<out TreeElement> {
				val treeElements = mutableListOf<TreeElement>()

				when (element) {

						is ArcFile -> {
								element.getObjectDeclarations().forEach {
										treeElements.add(ArcStructureViewElement(it as NavigatablePsiElement))
								}
						}

						is ArcObjectDeclaration -> {
								ArcUtil.findMethodDeclarations(element).forEach {
										treeElements.add(ArcStructureViewElement(it as NavigatablePsiElement))
								}
						}

				}

//				if (element is ArcFile) {
//						val decls = element.getDeclarations()
//
//						for (decl in decls) {
//								treeElements.add(ArcStructureViewElement(decl.getDeclaration() as NavigatablePsiElement))
//						}
//
//						return treeElements.toTypedArray<TreeElement>()
//				}

				return treeElements.toTypedArray<TreeElement>()
		}


}
