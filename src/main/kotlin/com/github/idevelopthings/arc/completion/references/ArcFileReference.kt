package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.psi.ArcImportStatement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import kotlin.io.path.Path

class ArcFileReference(private val node: ArcImportStatement, range: TextRange) : PsiReferenceBase<ArcImportStatement>(node, range) {

		override fun resolve(): PsiElement? {

				val importStr = node.valueString.text.slice(1 until node.valueString.text.length - 1)

				val currentPsiFile = node.containingFile.virtualFile!!
				val currentDir = currentPsiFile.path.split('/').dropLast(1).joinToString("/")
				val filePath = Path(currentDir).resolve(importStr).normalize().toString()

				val fileRef = ArcUtil.getAllFiles(node.project).firstOrNull {
						it?.virtualFile?.path == filePath
				}

				return fileRef
		}

}
