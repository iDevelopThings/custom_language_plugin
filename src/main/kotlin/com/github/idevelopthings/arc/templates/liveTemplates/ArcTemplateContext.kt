package com.github.idevelopthings.arc.templates.liveTemplates

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.psi.ArcFile
import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil

abstract class BaseArcTemplateContext(name: String = "Arc") : TemplateContextType(name) {

		override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
				val file = templateActionContext.file
				val element = file.findElementAt(templateActionContext.startOffset)

				return (file is ArcFile || file.name.endsWith(".arc")) && isInContext(templateActionContext, file, element)
		}

		abstract fun isInContext(
				templateActionContext: TemplateActionContext,
				file: PsiFile,
				element: PsiElement?
		): Boolean

}

class ArcTemplateContext : BaseArcTemplateContext("Arc") {
		override fun isInContext(templateActionContext: TemplateActionContext, file: PsiFile, element: PsiElement?): Boolean {
				return true
		}
}

class ArcFileTemplateContext : BaseArcTemplateContext("File") {
		override fun isInContext(templateActionContext: TemplateActionContext, file: PsiFile, element: PsiElement?): Boolean {
				return true
		}
}


class ArcBlockTemplateContext : BaseArcTemplateContext("Block") {

		override fun isInContext(templateActionContext: TemplateActionContext, file: PsiFile, element: PsiElement?): Boolean {
				if (element == null) return false

				return ArcPsiUtil.getOuterScopeBlock(element) != null
		}

}

