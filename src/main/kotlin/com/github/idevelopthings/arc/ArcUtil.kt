package com.github.idevelopthings.arc

import com.intellij.lang.ASTNode
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.github.idevelopthings.arc.psi.*


//val myTypeDefinitionIndex = StubIndexKey.createIndexKey<String, ArcTypeDefinition>("com.github.idevelopthings.customlanguageplugin.MyTypeDefinitionIndex")

class ArcUtil {

		companion object {

				fun findObjectDeclarations(project: Project): List<ArcObjectDeclaration> {
						val result = mutableListOf<ArcObjectDeclaration>()
						val virtualFiles = FileTypeIndex.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
						for (virtualFile in virtualFiles) {
								val ArcFile = PsiManager.getInstance(project).findFile(virtualFile) as? ArcFile
								val objectDeclarations = PsiTreeUtil.getChildrenOfTypeAsList(ArcFile, ArcObjectDeclaration::class.java)
								result.addAll(objectDeclarations)
						}
						return result
				}

				fun findObjectDeclarations(project: Project, name: String): List<ArcObjectDeclaration> {
						val result = mutableListOf<ArcObjectDeclaration>()
						val virtualFiles = FileTypeIndex.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
						for (virtualFile in virtualFiles) {
								val ArcFile = PsiManager.getInstance(project).findFile(virtualFile) as? ArcFile
								PsiTreeUtil.getChildrenOfTypeAsList(ArcFile, ArcTopLevelDeclaration::class.java)
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

				fun findBlockVarType(project: Project, referenceVar: ASTNode?): ArcType? {
						// First lets assume we're in some kind of block, either function or if statment

						// So we'll first look upwards for a block

						var block = referenceVar?.treeParent
						while (block != null && block.elementType != ArcTypes.BLOCK_BODY) {
								block = block.treeParent
						}

						if (block == null) {
//								logger<ArcUtil>().warn("No block found for referenceVar")
								return null
						}

						when (block.treeParent.psi) {
								is ArcFuncDeclaration -> {
										// Now we'll check the receiver

										val funcDecl = block.treeParent.psi as ArcFuncDeclaration
										if (funcDecl.funcReceiverDeclaration != null) {
												val recv = funcDecl.funcReceiverDeclaration?.funcReceiverName
												if (recv?.id?.text == referenceVar?.text) {
														return funcDecl.funcReceiverDeclaration?.type
												}
										}

										// Now we'll check the params
										val args = funcDecl.argumentDeclarationList?.argumentDeclarationList
										if (args?.isNotEmpty() == true) {
												for (arg in args) {
														if (arg.id.text == referenceVar?.text) {
																return arg.type
														}
												}
										}

										thisLogger().warn("No type found for ${referenceVar?.text}")

										return null

										/*val funcDecl = block.treeParent.psi as ArcFuncDeclaration
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
																				logger<ArcUtil>().warn("Found type $type for param ${funcParam.paramName.text}")
																		}
																}
														}
												}
										}*/
								}

								else -> {
										logger<ArcUtil>().warn("Unknown block type ${block.treeParent.psi}")
								}
						}


						return null
				}

				fun findAllDeclarations(file: ArcFile, unit: (decl: ArcDeclaration) -> Unit) {
						PsiTreeUtil.getChildrenOfTypeAsList(file, ArcTopLevelDeclaration::class.java).forEach {
								unit(it)
						}
				}

				fun findAllDeclarations(project: Project, unit: (decl: ArcDeclaration) -> Unit) {
						val virtualFiles = FileTypeIndex.getFiles(ArcFileType.INSTANCE, GlobalSearchScope.allScope(project))
						for (virtualFile in virtualFiles) {
								val ArcFile = PsiManager.getInstance(project).findFile(virtualFile) as? ArcFile
								findAllDeclarations(ArcFile!!, unit)
						}
				}

				fun findMethodDeclarations(decl: ArcObjectDeclaration): List<ArcFuncDeclaration> {
						val result = mutableListOf<ArcFuncDeclaration>()

						findAllDeclarations(decl.containingFile as ArcFile) {
								val tlDecl = it.getDeclaration()
								if (tlDecl is ArcFuncDeclaration) {
										if (tlDecl.belongsToType(decl))
												result.add(tlDecl)
								}

						}

						return result
				}


		}
}

inline fun <reified K, reified F : FileType> fileBasedIndexId(keyClass: Class<K>, fileType: F): String {
		return "${keyClass.name}.${fileType.name}"
}
