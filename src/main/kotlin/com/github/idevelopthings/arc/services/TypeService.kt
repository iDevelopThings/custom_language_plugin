package com.github.idevelopthings.arc.services

/*

import com.github.idevelopthings.arc.ArcFileType
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.github.idevelopthings.arc.MyBundle
import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.components.service
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.*

@Service(Service.Level.PROJECT)
class TypeService(private var project: Project) {

		init {
				thisLogger().info(MyBundle.message("projectService", project.name))
		}

		companion object {
				@JvmStatic
				fun getInstance(project: Project): TypeService = project.service()

				private var declarationsCache: CachedValue<MutableList<ArcDeclaration>>? = null

				private var objectDeclarationsCache: CachedValue<MutableList<ArcObjectDeclaration>>? = null
				private var objectFuncDeclarationsCache: CachedValue<MutableMap<String, MutableList<ArcFuncDeclaration>>>? = null

				private var funcDeclarationsCache: CachedValue<MutableList<ArcFuncDeclaration>>? = null
				private var enumDeclarationsCache: CachedValue<MutableList<ArcEnumDeclaration>>? = null

				private var isCacheInitializing = false
		}


		private fun buildDependencies() {
				thisLogger().warn("1. Rebuilding dependencies/cache")
				if (isCacheInitializing) return

				thisLogger().warn("2. Rebuilding dependencies/cache")
				isCacheInitializing = true

				val manager = CachedValuesManager.getManager(project)
				val virtualFiles = FileTypeIndex.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
				for (virtualFile in virtualFiles) {
						processFileDeclarations(
								(PsiManager.getInstance(project).findFile(virtualFile) as? ArcFile)!!,
								manager
						)
				}

				isCacheInitializing = false

		}

		private fun processFileDeclarations(file: ArcFile, manager: CachedValuesManager) {
				CachedValuesManager.getProjectPsiDependentCache(file) {
						val objectDeclarations = mutableListOf<ArcObjectDeclaration>()
						val funcDeclarations = mutableListOf<ArcFuncDeclaration>()
						val objFuncDeclarations = mutableMapOf<String, MutableList<ArcFuncDeclaration>>()
						val enumDeclarations = mutableListOf<ArcEnumDeclaration>()
						val declarations = mutableListOf<ArcDeclaration>()

						val childs = PsiTreeUtil.getChildrenOfTypeAsList(file, ArcTopLevelDeclaration::class.java);

						childs.forEach {
								if (it !is ArcDeclaration) return@forEach
								val decl = it.getDeclaration()

								declarations.add(it)

								when (decl) {

										is ArcObjectDeclaration -> {
												objectDeclarations.add(decl)
												thisLogger().warn("Adding object declaration: ${decl.name}")
												if (!objFuncDeclarations.containsKey(decl.name!!)) {
														objFuncDeclarations[decl.name!!] = mutableListOf<ArcFuncDeclaration>()
												}
										}

										is ArcFuncDeclaration -> funcDeclarations.add(decl)
										is ArcEnumDeclaration -> enumDeclarations.add(decl)
								}
						}


						for (fn in funcDeclarations) {
								if (fn.funcReceiverDeclaration == null) continue
								for (objDecls in objFuncDeclarations) {
										if (objDecls.key == fn.funcReceiverDeclaration?.type?.name!!) {
												thisLogger().warn("Adding obj <-> function declaration: ${objDecls.key} <-> ${fn.funcId?.id?.text}")
												objDecls.value.add(fn)
										}
								}
						}


						declarationsCache = manager.createCachedValue {
								CachedValueProvider.Result.create(declarations, PsiModificationTracker.MODIFICATION_COUNT)
						}

						objectDeclarationsCache = manager.createCachedValue {
								CachedValueProvider.Result.create(objectDeclarations, PsiModificationTracker.MODIFICATION_COUNT)
						}

						objectFuncDeclarationsCache = manager.createCachedValue {
								CachedValueProvider.Result.create(objFuncDeclarations, PsiModificationTracker.MODIFICATION_COUNT)
						}

						funcDeclarationsCache = manager.createCachedValue {
								CachedValueProvider.Result.create(funcDeclarations, PsiModificationTracker.MODIFICATION_COUNT)
						}

						enumDeclarationsCache = manager.createCachedValue {
								CachedValueProvider.Result.create(enumDeclarations, PsiModificationTracker.MODIFICATION_COUNT)
						}

				}
		}

		fun getDeclarations(): MutableList<ArcDeclaration> {
				if (declarationsCache == null) {
						buildDependencies()
						return declarationsCache?.value!!
				}
				return declarationsCache?.value!!
		}

		fun getObjectDeclarations(): MutableList<ArcObjectDeclaration> {
				if (objectDeclarationsCache == null) {
						buildDependencies()
						return objectDeclarationsCache?.value!!
				}
				return objectDeclarationsCache?.value!!
		}

		fun getObjectFuncDeclarations(): MutableMap<String, MutableList<ArcFuncDeclaration>> {
				if (objectFuncDeclarationsCache == null) {
						buildDependencies()
//						if (objectFuncDeclarationsCache?.value == null) {
//								buildDependencies()
//						}
				}

				return objectFuncDeclarationsCache?.value!!
		}

		fun getFuncDeclarations(): MutableList<ArcFuncDeclaration> {
				if (funcDeclarationsCache == null) {
						buildDependencies()
						return funcDeclarationsCache?.value!!
				}

				return funcDeclarationsCache?.value!!
		}

		fun getEnumDeclarations(): MutableList<ArcEnumDeclaration> {
				if (enumDeclarationsCache == null) {
						buildDependencies()
						return enumDeclarationsCache?.value!!
				}

				return enumDeclarationsCache?.value!!
		}


		fun getObjectFunctions(obj: ArcObjectDeclaration): MutableList<ArcFuncDeclaration> {
				val result = getObjectFuncDeclarations()[obj.name!!]
				if (result == null) {
						thisLogger().warn("No functions for object ${obj.name}")
						return mutableListOf()
				}
				return result
		}

}

*/


