package com.github.idevelopthings.arc.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType
import com.github.idevelopthings.arc.ArcFileType


class ArcElementFactory {

		companion object {

				fun createIdentifier(project: Project?, name: String?): ArcNamedElement {
						val file = createFile(project, "object $name {}")
						return file.firstChild.childrenOfType<ArcNamedElement>().first()
				}

				fun createFile(project: Project?, text: String?): ArcFile {
						val name = "dummy.simple"
						return text?.let {
								PsiFileFactory.getInstance(project).createFileFromText(name, ArcFileType.INSTANCE, it)
						} as ArcFile
				}
		}

}
