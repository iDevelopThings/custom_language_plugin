package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType
import com.github.idevelopthings.customlanguageplugin.DataFusionFileType


class DataFusionElementFactory {

		companion object {

				fun createIdentifier(project: Project?, name: String?): DataFusionNamedElement {
						val file = createFile(project, "object $name {}")
						return file.firstChild.childrenOfType<DataFusionNamedElement>().first()
				}

				fun createFile(project: Project?, text: String?): DataFusionFile {
						val name = "dummy.simple"
						return text?.let {
								PsiFileFactory.getInstance(project).createFileFromText(name, DataFusionFileType.INSTANCE, it)
						} as DataFusionFile
				}
		}

}
