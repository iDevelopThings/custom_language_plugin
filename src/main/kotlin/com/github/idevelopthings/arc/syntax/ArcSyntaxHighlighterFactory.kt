package com.github.idevelopthings.arc.syntax

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class ArcSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
		override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
				return ArcSyntaxHighlighter()
		}
}

