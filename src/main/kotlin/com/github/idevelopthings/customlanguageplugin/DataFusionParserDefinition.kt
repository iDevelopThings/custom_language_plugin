package com.github.idevelopthings.customlanguageplugin

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
import com.github.idevelopthings.customlanguageplugin.parser.DataFusionParser
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionFile
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTokenSets
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes

class DataFusionParserDefinition : ParserDefinition {
		companion object {
				val FILE: IFileElementType = IFileElementType(DataFusionLanguage.INSTANCE)
		}

		override fun createLexer(project: Project?): Lexer {
				return DataFusionLexerAdapter()
		}

		override fun createParser(project: Project?): PsiParser {
				return DataFusionParser()
		}

		override fun getFileNodeType(): IFileElementType {
				return FILE
		}

		override fun getCommentTokens(): TokenSet {
				return DataFusionTokenSets.COMMENTS
		}

		override fun getStringLiteralElements(): TokenSet {
				return DataFusionTokenSets.STRING
		}

		override fun createElement(node: ASTNode?): PsiElement {
				return DataFusionTypes.Factory.createElement(node)
		}

		override fun createFile(viewProvider: FileViewProvider): PsiFile {
				return DataFusionFile(viewProvider)
		}
}
