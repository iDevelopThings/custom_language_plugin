package com.github.idevelopthings.arc.language.analysis.quickfixes

import com.github.idevelopthings.arc.language.languageHost.DiagnosticMessage
import com.intellij.codeInsight.intention.BaseElementAtCaretIntentionAction
import com.intellij.codeInsight.intention.preview.IntentionPreviewInfo
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

open class ArcBaseLinterQuickfix(
		val diagnosticElement: PsiElement?,
		val diagnostic: DiagnosticMessage,
) : BaseElementAtCaretIntentionAction() {


		override fun getText(): String {
				return "Base linter quickfix"
		}

		override fun getFamilyName(): String {
				return "Base linter quickfix"
		}

		override fun generatePreview(project: Project, editor: Editor, file: PsiFile): IntentionPreviewInfo {
				return IntentionPreviewInfo.EMPTY
		}

		override fun isAvailable(project: Project, editor: Editor?, element: PsiElement): Boolean {
				return true
		}

		override fun invoke(project: Project, editor: Editor?, element: PsiElement) {
				return
		}

}
