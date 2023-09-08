package com.github.idevelopthings.arc.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.github.idevelopthings.arc.ArcFileType
import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.RecursionManager
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.PsiTreeUtil


class ArcFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ArcLanguage) {

		override fun getFileType(): FileType {
				return ArcFileType.INSTANCE
		}

		fun getTopLevelDeclarations(): MutableList<ArcTopLevelDeclaration>? {
				return RecursionManager.doPreventingRecursion(this, true) {
						CachedValuesManager.getCachedValue(this) {
								val decls = PsiTreeUtil.getChildrenOfTypeAsList(this, ArcTopLevelDeclaration::class.java)

								CachedValueProvider.Result.create(decls, PsiModificationTracker.MODIFICATION_COUNT)
						}
				}
		}

		fun getDeclarations(): List<ArcBaseDeclaration>? {
				val result = RecursionManager.doPreventingRecursion(this, true) {
						CachedValuesManager.getCachedValue(this) {
								val decls = PsiTreeUtil.getChildrenOfTypeAsList(this, ArcTopLevelDeclaration::class.java)
										.map { it.getDeclaration()										}
										.filterIsInstance<ArcBaseDeclaration>()

								CachedValueProvider.Result.create(decls, PsiModificationTracker.MODIFICATION_COUNT)
						}
				}

				return result
		}

		fun getObjectDeclarations(): MutableList<ArcObjectDeclaration>? {
				return RecursionManager.doPreventingRecursion(this, true, Computable {
						CachedValuesManager.getCachedValue(this) {
								CachedValueProvider.Result.create(
										getTopLevelDeclarations()
												?.mapNotNull { it.objectDeclaration }
												?.toMutableList(),
										PsiModificationTracker.MODIFICATION_COUNT
								)
						}
				})
		}

		fun getFunctionDeclarations(): MutableList<ArcFuncDeclaration>? {
				return RecursionManager.doPreventingRecursion(this, true, Computable {
						CachedValuesManager.getCachedValue(this) {
								CachedValueProvider.Result.create(
										getTopLevelDeclarations()
												?.mapNotNull { it.funcDeclaration }
												?.toMutableList(),
										PsiModificationTracker.MODIFICATION_COUNT
								)
						}
				})
		}

		fun getEnumDeclarations(): MutableList<ArcEnumDeclaration>? {
				return RecursionManager.doPreventingRecursion(this, true, Computable {
						CachedValuesManager.getCachedValue(this) {
								CachedValueProvider.Result.create(
										getTopLevelDeclarations()
												?.mapNotNull { it.enumDeclaration }
												?.toMutableList(),
										PsiModificationTracker.MODIFICATION_COUNT
								)
						}
				})
		}


		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				for (topLevelDeclaration in getTopLevelDeclarations()!!) {
						val decl = topLevelDeclaration.getDeclaration()
						if (decl != null) {
								if (!processor.execute(decl, state)) {
										return false
								}
						}
				}

				/*val childrenDecls = PsiTreeUtil.findChildrenOfType(this, ArcTopLevelDeclaration::class.java)
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
						if (it.enumDeclaration != null) {
								if (!processor.execute(it.enumDeclaration!!, state)) {
										return false
								}
						}
				}*/
				return true
		}


		/*	fun getObjectDeclarations(): List<ArcObjectDeclaration>? {
					val items = CachedValuesManager.getProjectPsiDependentCache(this) {
							val decls = project.service<TypeService>().getObjectDeclarations().filter {
									it.containingFile == this
							}
							CachedValueProvider.Result.create(decls, PsiModificationTracker.MODIFICATION_COUNT)
					}

					return items.value

					*//*val decls = mutableListOf<ArcObjectDeclaration>()
				ArcUtil.findAllDeclarations(this) {
						val decl = it.getDeclaration()
						if (decl is ArcObjectDeclaration) {
								decls.add(decl)
						}
				}
				return decls*//*
		}*/
}

