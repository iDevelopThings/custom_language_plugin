package com.github.idevelopthings.arc

import com.github.idevelopthings.arc.parser.ArcParser
import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcTokenSets
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class ArcParserDefinition : ParserDefinition {
		companion object {
				val FILE: IFileElementType = IFileElementType(ArcLanguage)
		}

		override fun createLexer(project: Project?) = ArcLexerAdapter()
		override fun createParser(project: Project?) = ArcParser()
		override fun getFileNodeType() = FILE
		override fun getCommentTokens() = ArcTokenSets.COMMENTS
		override fun getStringLiteralElements() = ArcTokenSets.STRING
		override fun getWhitespaceTokens() = ArcTokenSets.WHITESPACES
		override fun createElement(node: ASTNode?): PsiElement = ArcTypes.Factory.createElement(node)
		override fun createFile(viewProvider: FileViewProvider): PsiFile = ArcFile(viewProvider)
}
