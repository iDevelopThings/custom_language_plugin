package com.github.idevelopthings.arc.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType
import com.github.idevelopthings.arc.ArcFileType
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil


object ArcElementFactory {


		fun createIdentifier(project: Project?, name: String?): ArcNamedElement {
				val file = createFile(project, "object $name {}")
				return file.firstChild.childrenOfType<ArcNamedElement>().first()
		}

		fun createObjectFieldDeclaration(project: Project?, name: String, type: String): ArcObjectFieldDeclaration {
				val file = createFile(project, "object temp { $name $type }")
				return PsiTreeUtil.findChildOfType(file.firstChild, ArcObjectFieldDeclaration::class.java)!!
		}

		fun createFuncDeclaration(project: Project, name: String, objectDeclaration: ArcObjectDeclaration?): ArcFuncDeclaration {
				val receiverText = if (objectDeclaration != null) {
						val typeName = objectDeclaration.objectId.text
						"(${typeName[0]} ${typeName})"
				} else {
						null
				}
				return createFuncDeclaration(project, name, receiverText)
		}

		fun createFuncDeclaration(project: Project, name: @NlsSafe String?, receiver: ArcFuncReceiverDeclaration?): ArcFuncDeclaration {
				return createFuncDeclaration(project, name, receiver?.text)
		}

		fun createFuncDeclaration(project: Project, name: @NlsSafe String?, receiverText: String?): ArcFuncDeclaration {
				var text = "func "
				if (receiverText != null) {
						text += receiverText
						if (!receiverText.endsWith(" ")) {
								text += " "
						}
				}
				text += "$name() {\n}\n"
				val file = createFile(project, text)
				return PsiTreeUtil.findChildOfType(file.firstChild, ArcFuncDeclaration::class.java)!!
		}

		fun createFile(project: Project?, text: String?): ArcFile {
				val name = "dummy.simple"
				return text?.let {
						PsiFileFactory.getInstance(project).createFileFromText(name, ArcFileType.INSTANCE, it)
				} as ArcFile
		}

		fun createWhitespace(project: Project, wsType: String): PsiElement {
				val file = createFile(project, wsType)
				return file.firstChild
		}

		fun createArgumentDeclaration(project: Project, typeName: String): ArcArgumentDeclaration {
				val file = createFile(project, "func temp($typeName $typeName) {}")
				return PsiTreeUtil.findChildOfType(file.firstChild, ArcArgumentDeclaration::class.java)!!
		}

		fun createArgumentList(project: Project, currentArgs: ArcArgumentDeclarationList?, newArgType: String): ArcArgumentDeclarationList {
				var args = currentArgs?.text ?: ""
				args = args.substring(1, args.length - 1)

				val file = createFile(project, "func temp(${args}, $newArgType $newArgType) {}")
				return PsiTreeUtil.findChildOfType(file.firstChild, ArcArgumentDeclarationList::class.java)!!

		}


}
