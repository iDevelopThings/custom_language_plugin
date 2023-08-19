package com.github.idevelopthings.arc.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.github.idevelopthings.arc.ArcFileType
import com.github.idevelopthings.arc.ArcLanguage
import com.github.idevelopthings.arc.ArcUtil
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil


class ArcFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ArcLanguage.INSTANCE) {

		override fun getFileType(): FileType {
				return ArcFileType.INSTANCE
		}

		override fun toString(): String {
				return "Data Fusion File"
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				val childrenDecls = PsiTreeUtil.findChildrenOfType(this, ArcTopLevelDeclaration::class.java)


				childrenDecls.forEach {
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

		fun getDeclarations(): MutableList<ArcDeclaration> {
				val decls = mutableListOf<ArcDeclaration>()
				ArcUtil.findAllDeclarations(this){
						decls.add(it)
				}
				return decls
		}

		fun getObjectDeclarations(): MutableList<ArcObjectDeclaration> {
				val decls = mutableListOf<ArcObjectDeclaration>()
				ArcUtil.findAllDeclarations(this){
						val decl = it.getDeclaration()
						if (decl is ArcObjectDeclaration) {
								decls.add(decl)
						}
				}
				return decls
		}
}

