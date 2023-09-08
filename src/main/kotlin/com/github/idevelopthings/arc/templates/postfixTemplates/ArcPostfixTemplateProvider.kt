package com.github.idevelopthings.arc.templates.postfixTemplates

import com.intellij.codeInsight.template.postfix.templates.PostfixTemplate
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class ArcPostfixTemplateProvider : PostfixTemplateProvider {


		override fun getTemplates(): MutableSet<PostfixTemplate> {
				return mutableSetOf(
						CreateObjectFunctionPostfixTemplate("func", "func", "createObjectFunction", this),
						CreateLoopPostfixTemplate("for", "for", "createLoop", this)
				)
		}

		override fun isTerminalSymbol(currentChar: Char): Boolean {
				return currentChar == '.'
		}

		override fun preExpand(file: PsiFile, editor: Editor) {

		}

		override fun afterExpand(file: PsiFile, editor: Editor) {

		}

		override fun preCheck(copyFile: PsiFile, realEditor: Editor, currentOffset: Int): PsiFile {
				return copyFile
		}
}
