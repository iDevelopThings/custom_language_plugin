package com.github.idevelopthings.arc.templates.postfixTemplates

import com.github.idevelopthings.arc.psi.ArcFile
import com.github.idevelopthings.arc.psi.ArcTopLevelDeclaration
import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.TemplateManager
import com.intellij.codeInsight.template.impl.TextExpression
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplate
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateProvider
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import org.jetbrains.annotations.NonNls


class CreateObjectFunctionPostfixTemplate(
		id: @NonNls String?,
		name: @NlsSafe String,
		example: @NlsSafe String,
		provider: PostfixTemplateProvider?
) : PostfixTemplate(id, name, example, provider) {

		override fun isApplicable(context: PsiElement, copyDocument: Document, newOffset: Int): Boolean {
//				return (context.parent is ArcFile || context.parent is ArcTopLevelDeclaration)
//							 &&
				return context.elementType == ArcTypes.ID
		}

		override fun expand(context: PsiElement, editor: Editor) {
				val typeName = context.text

				val originalSegment = context.textRange

				val templateManager = TemplateManager.getInstance(editor.project)
				val template: Template = templateManager.createTemplate("", "")
				template.isToReformat = true
				template.addTextSegment("func (")
				template.addVariable("receiverVarName", TextExpression(typeName[0].lowercase()), TextExpression(""), true)
				template.addTextSegment(" ")
				template.addTextSegment(typeName)
				template.addTextSegment(") ")
				template.addVariable("funcName", TextExpression(""), TextExpression("name"), true)
				template.addTextSegment("() ")
				template.addVariable("returnType", TextExpression(""), TextExpression(""), true)
				template.addTextSegment(" {\n")
				template.addEndVariable()
				template.addTextSegment("\n}")


				templateManager.startTemplate(editor, template)

				editor.document.deleteString(originalSegment.startOffset, originalSegment.endOffset)

		}

}
