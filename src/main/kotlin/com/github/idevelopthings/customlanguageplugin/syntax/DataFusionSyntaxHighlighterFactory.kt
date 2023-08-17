package com.github.idevelopthings.customlanguageplugin.syntax

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class DataFusionSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
		override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
				return DataFusionSyntaxHighlighter()
		}
}

