package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.github.idevelopthings.customlanguageplugin.DataFusionFileType
import com.github.idevelopthings.customlanguageplugin.DataFusionLanguage
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil


class DataFusionFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, DataFusionLanguage.INSTANCE) {
		override fun getFileType(): FileType {
				return DataFusionFileType.INSTANCE
		}

		override fun toString(): String {
				return "Data Fusion File"
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				PsiTreeUtil.findChildrenOfType(this, DataFusionTopLevelDeclaration::class.java).forEach {
						if (it.funcDeclaration != null) {
								if (!processor.execute(it.funcDeclaration!!, state)) {
										return false
								}
						}
						if (it.objectDeclaration != null) {
								if (!processor.execute(it.objectDeclaration!!, state)) {
										return false
								}
						}
				}
				return true
		}
}

