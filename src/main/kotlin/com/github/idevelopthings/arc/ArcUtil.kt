package com.github.idevelopthings.arc

import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.RecursionManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker


// val myTypeDefinitionIndex = StubIndexKey.createIndexKey<String, ArcTypeDefinition>("com.github.idevelopthings.customlanguageplugin.MyTypeDefinitionIndex")

object ArcUtil {


		fun forAllFiles(project: Project, unit: (file: ArcFile) -> Unit) {
				val virtualFiles = FileTypeIndex.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
				for (virtualFile in virtualFiles) {
						val file = PsiManager.getInstance(project).findFile(virtualFile) as? ArcFile
						unit(file!!)
				}
		}

		fun getAllFiles(project: Project): List<ArcFile?> {
				val result = CachedValuesManager.getManager(project).getCachedValue(project) {
						val r = FileTypeIndex
								.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
								.map {
										PsiManager.getInstance(project).findFile(it) as? ArcFile
								}

						CachedValueProvider.Result.create(
								r,
								PsiModificationTracker.MODIFICATION_COUNT
						)
				}

				return result
		}

		fun findAllDeclarations(project: Project): List<ArcBaseDeclaration> {
				val result = CachedValuesManager.getManager(project).getCachedValue(project) {
						val result = mutableListOf<ArcBaseDeclaration>()

						forAllFiles(project) { file ->
								result.addAll(file.getDeclarations() ?: listOf())
						}

						CachedValueProvider.Result.create(
								result,
								PsiModificationTracker.MODIFICATION_COUNT
						)
				}

				return result
		}

		fun findObjectDeclarations(project: Project): List<ArcObjectDeclaration> {
				val result = mutableListOf<ArcObjectDeclaration>()
				forAllFiles(project) { file ->
						file.getObjectDeclarations()?.let {
								result.addAll(it)
						}
				}
				return result
		}

		fun findObjectDeclarations(project: Project, name: String): List<ArcObjectDeclaration> {
				val result = mutableListOf<ArcObjectDeclaration>()
				forAllFiles(project) { file ->
						val decls = file.getObjectDeclarations()
						decls?.filter {
								it.objectId.text == name
						}?.let {
								result.addAll(it)
						}
				}
				return result
		}

		fun findEnumDeclarations(project: Project): List<ArcEnumDeclaration> {
				val result = CachedValuesManager.getManager(project).getCachedValue(project) {
						val result = mutableListOf<ArcEnumDeclaration>()

						forAllFiles(project) { file ->
								file.getEnumDeclarations()?.let {
										result.addAll(it)
								}
						}

						thisLogger().warn("findEnumDeclarations resolved")

						CachedValueProvider.Result.create(
								result,
								PsiModificationTracker.MODIFICATION_COUNT
						)
				}

				return result
		}

		fun findEnumDeclarations(project: Project, name: String): List<ArcEnumDeclaration> {
				val result = mutableListOf<ArcEnumDeclaration>()

				forAllFiles(project) { file ->
						val decls = file.getEnumDeclarations()
						decls?.filter {
								it.enumId.text == name
						}?.let {
								result.addAll(it)
						}
				}

				return result
		}

		fun findMethodDeclarations(decl: ArcObjectDeclaration): List<ArcFuncDeclaration> {
				val result = mutableListOf<ArcFuncDeclaration>()
				forAllFiles(decl.project) { file ->
						file.getFunctionDeclarations()?.filter {
								it.belongsToType(decl)
						}?.let {
								result.addAll(it)
						}
				}
				return result
		}

		fun findGlobalFunctions(project: Project): List<ArcFuncDeclaration> {
				val result = CachedValuesManager.getManager(project).getCachedValue(project) {
						val result = mutableListOf<ArcFuncDeclaration>()

						forAllFiles(project) { file ->
								file.getFunctionDeclarations()
										?.filter { it.isGlobal() }
										?.let { result.addAll(it) }
						}

						thisLogger().warn("findGlobalFunctions resolved")

						CachedValueProvider.Result.create(
								result,
								PsiModificationTracker.MODIFICATION_COUNT
						)
				}

				return result
		}

		/* fun provideVarRefCompletions(ident: PsiElement): MutableList<PsiElementWithLookup> {
				val lookups = mutableListOf<PsiElementWithLookup>()

				val objId = ArcPsiUtil.resolveMemberAccessDeclaration(
						ident, ArcPsiUtil.ResolutionKind.TYPE_DECLARATION
				) ?: return lookups

				val decl = TypeLookup.getTypeFromDeclaration<ArcObjectDeclaration>(objId) ?: return lookups
				lookups.addAll(decl.getFields())
				lookups.addAll(decl.getMethods())

				return lookups
		} */

		fun provideLookupsForType(type: ArcType): MutableList<PsiElementWithLookup> {
				val lookups = mutableListOf<PsiElementWithLookup>()

				val decl = TypeLookup.getTypeFromDeclaration<ArcObjectDeclaration>(type.reference?.resolve()!!) ?: return lookups
				lookups.addAll(decl.getFields())
				lookups.addAll(decl.getMethods())

				return lookups
		}


}

