package com.github.idevelopthings.arc.language.analysis

import com.github.idevelopthings.arc.language.analysis.quickfixes.DefineFieldQuickfix
import com.github.idevelopthings.arc.language.analysis.quickfixes.DefineFunctionArgQuickfix
import com.github.idevelopthings.arc.language.analysis.quickfixes.DefineFunctionQuickFix
import com.github.idevelopthings.arc.language.languageHost.DiagnosticMessage
import com.github.idevelopthings.arc.language.languageHost.ILanguageHostService
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile


data class LaunchLintingInfo(
		var file: PsiFile
)

class LintingDiagnosticsResult {
		var diagnostics: MutableMap<String, MutableList<DiagnosticMessage>> = mutableMapOf()
}

class ArcErrorCheckingAnnotator : ExternalAnnotator<LaunchLintingInfo, LintingDiagnosticsResult>() {

		object Util {
				val diagnosticQuickFixes = mutableMapOf(
						"0001" to { e: PsiElement?, d: DiagnosticMessage -> DefineFieldQuickfix(e, d) },
						"0002" to { e: PsiElement?, d: DiagnosticMessage -> DefineFunctionQuickFix(e, d) },
						"0003" to { e: PsiElement?, d: DiagnosticMessage -> DefineFunctionArgQuickfix(e, d) },
				)
		}


		override fun collectInformation(file: PsiFile): LaunchLintingInfo {
				return LaunchLintingInfo(file)
		}

		override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean): LaunchLintingInfo {
				return LaunchLintingInfo(file)
		}

		override fun doAnnotate(collectedInfo: LaunchLintingInfo?): LintingDiagnosticsResult? {
				if (collectedInfo == null) return null
				val languageService = collectedInfo.file.project.service<ILanguageHostService>()

				val result = LintingDiagnosticsResult()
				result.diagnostics = languageService.annotate(collectedInfo.file)

				return result
		}

		override fun apply(file: PsiFile, annotationResult: LintingDiagnosticsResult?, holder: AnnotationHolder) {
				val diagnostics = annotationResult?.diagnostics?.get(file.virtualFile.path) ?: return
				for (diagnostic in diagnostics) {
						val start = file.findElementAt(diagnostic.start.abs)
						val end = file.findElementAt(diagnostic.end.abs)

						if (start == null || end == null) continue

						val severity = when (diagnostic.severity) {
								"Error" -> HighlightSeverity.ERROR
								"Warning" -> HighlightSeverity.WARNING
								"Info" -> HighlightSeverity.INFORMATION
								else -> HighlightSeverity.TEXT_ATTRIBUTES
						}

						val range = TextRange(diagnostic.start.abs, diagnostic.end.abs)

						val annot = holder.newAnnotation(severity, diagnostic.message)
						annot.range(range)

						if (diagnostic.code != null && Util.diagnosticQuickFixes.containsKey(diagnostic.code)) {
								val fix = Util.diagnosticQuickFixes[diagnostic.code]!!(
										file.findElementAt(range.startOffset),
										diagnostic
								)

								annot.withFix(fix)
						}

						annot.create()
				}

		}

}
