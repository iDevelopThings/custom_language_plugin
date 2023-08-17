package com.github.idevelopthings.customlanguageplugin

import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndexKey
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.indexing.FileBasedIndex
import com.github.idevelopthings.customlanguageplugin.psi.*


//val myTypeDefinitionIndex = StubIndexKey.createIndexKey<String, DataFusionTypeDefinition>("com.github.idevelopthings.customlanguageplugin.MyTypeDefinitionIndex")

class DataFusionUtil {

		companion object {

				fun findObjectDeclarations(project: Project): List<DataFusionObjectDeclaration> {
						val result = mutableListOf<DataFusionObjectDeclaration>()
						val virtualFiles = FileTypeIndex.getFiles(DataFusionFileType.INSTANCE, GlobalSearchScope.allScope(project))
						for (virtualFile in virtualFiles) {
								val dataFusionFile = PsiManager.getInstance(project).findFile(virtualFile) as? DataFusionFile
								val objectDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(dataFusionFile, DataFusionObjectDeclaration::class.java)
								result.addAll(objectDeclarations)
						}
						return result
				}

				fun findObjectDeclarations(project: Project, name: String): List<DataFusionObjectDeclaration> {
						val result = mutableListOf<DataFusionObjectDeclaration>()
						val virtualFiles = FileTypeIndex.getFiles(DataFusionFileType.INSTANCE, GlobalSearchScope.allScope(project))
						for (virtualFile in virtualFiles) {
								val dataFusionFile = PsiManager.getInstance(project).findFile(virtualFile) as? DataFusionFile
								PsiTreeUtil.getChildrenOfTypeAsList(dataFusionFile, DataFusionTopLevelDeclaration::class.java)
										.map {
												it.objectDeclaration
										}
										.filter {
												it != null && it.objectId.text == name
										}.forEach {
												result.add(it!!)
										}
						}
						return result
				}

				fun findBlockVarType(project: Project, referenceVar: ASTNode?): DataFusionType? {
						// First lets assume we're in some kind of block, either function or if statment

						// So we'll first look upwards for a block

						var block = referenceVar?.treeParent
						while (block != null && block.elementType != DataFusionTypes.BLOCK_BODY) {
								block = block.treeParent
						}

						if (block == null) {
//								logger<DataFusionUtil>().warn("No block found for referenceVar")
								return null
						}

						when (block.treeParent.psi) {
								is DataFusionFuncDeclaration -> {
										// Now we'll check the receiver

										val funcDecl = block.treeParent.psi as DataFusionFuncDeclaration
										if (funcDecl.funcReceiverDeclaration != null) {
												val recv = funcDecl.funcReceiverDeclaration?.funcReceiverName
												if (recv?.id?.text == referenceVar?.text) {
														return funcDecl.funcReceiverDeclaration?.type
												}
										}

										// Now we'll check the params
										val args = funcDecl.argumentDeclarationList.argumentDeclarationList
										if (args.isNotEmpty()) {
												for (arg in args) {
														if (arg.id.text == referenceVar?.text) {
																return arg.type
														}
												}
										}

										thisLogger().warn("No type found for ${referenceVar?.text}")

										return null

										/*val funcDecl = block.treeParent.psi as DataFusionFuncDeclaration
										val funcName = funcDecl.funcName.text
										val funcDeclList = findFuncDeclarations(project, funcName)
										if (funcDeclList.size == 1) {
												val funcDecl = funcDeclList[0]
												val funcParams = funcDecl.funcParams
												val funcParamList = funcParams?.funcParamList
												if (funcParamList != null) {
														for (funcParam in funcParamList) {
																if (funcParam.paramName.text == referenceVar.text) {
																		// We've found the param, so we'll look for the type
																		val paramType = funcParam.paramType
																		if (paramType != null) {
																				val type = paramType.type.text
																				logger<DataFusionUtil>().warn("Found type $type for param ${funcParam.paramName.text}")
																		}
																}
														}
												}
										}*/
								}

								else -> {
										logger<DataFusionUtil>().warn("Unknown block type ${block.treeParent.psi}")
								}
						}


						return null
				}


		}
}

inline fun <reified K, reified F : FileType> fileBasedIndexId(keyClass: Class<K>, fileType: F): String {
		return "${keyClass.name}.${fileType.name}"
}
