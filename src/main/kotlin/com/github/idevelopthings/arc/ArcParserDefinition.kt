package com.github.idevelopthings.arc

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.github.idevelopthings.arc.parser.ArcParser
import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcTokenSets
import com.github.idevelopthings.arc.psi.ArcTypes

class ArcParserDefinition : ParserDefinition {
		companion object {
				val FILE: IFileElementType = IFileElementType(ArcLanguage.INSTANCE)
		}

		override fun createLexer(project: Project?): Lexer {
				return ArcLexerAdapter()
		}

		override fun createParser(project: Project?): PsiParser {
				return ArcParser()
		}

		override fun getFileNodeType(): IFileElementType {
				return FILE
		}

		override fun getCommentTokens(): TokenSet {
				return ArcTokenSets.COMMENTS
		}

		override fun getStringLiteralElements(): TokenSet {
				return ArcTokenSets.STRING
		}

		override fun createElement(node: ASTNode?): PsiElement {
				return ArcTypes.Factory.createElement(node)
		}

		override fun createFile(viewProvider: FileViewProvider): PsiFile {
				return ArcFile(viewProvider)
		}
}
