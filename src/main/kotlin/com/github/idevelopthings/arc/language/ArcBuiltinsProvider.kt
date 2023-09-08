package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcFuncDeclaration
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.util.PsiTreeUtil
import java.io.File

class ArcBuiltinsProvider {

		companion object {
				val stubPsiFiles = mutableListOf<PsiFile>()
				val stubs = mutableListOf<VirtualFile>()

				val builtinFunctionStubs = mutableMapOf<ArcObjectDeclaration, MutableList<ArcFuncDeclaration>>()

				/*fun loadBuiltinStubs(project: Project) {
						val fileStream = javaClass.classLoader.getResourceAsStream("stdStubs/fmt.arc")
						val fileContent = fileStream?.bufferedReader().use {
								it?.readText()
						}

						val tempFile = File.createTempFile("builtins", ".arc")
						tempFile.writeText(fileContent!!)

						val vFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(tempFile)!!
						stubs.add(vFile)

						val psiFile = PsiManager.getInstance(project).findFile(vFile)
						stubPsiFiles.add(psiFile!!)

						val arcFile = psiFile as ArcFile
						var currObj: ArcObjectDeclaration? = null
						arcFile.getDeclarations().forEach {
								var tld = it.getDeclaration()
								var pls = PsiTreeUtil.findChildOfType(it, ArcFuncDeclaration::class.java)

								if (tld is ArcObjectDeclaration) {
										builtinFunctionStubs[tld] = mutableListOf()
										currObj = tld
								}

								if (tld is ArcFuncDeclaration)
										builtinFunctionStubs[currObj!!]?.add(tld)
						}

				}*/

				/*fun findFunction(typeName: String, functionName: String): ArcFuncDeclaration? {
						var fnDecl: ArcFuncDeclaration? = null

						builtinFunctionStubs.forEach { (obj, fn) ->
								if (obj.name == typeName) {
										fn.find { it.funcId?.id?.text == functionName }?.let {
												fnDecl = it
												return@forEach
										}
								}
						}

						return fnDecl
				}*/

		}

}
