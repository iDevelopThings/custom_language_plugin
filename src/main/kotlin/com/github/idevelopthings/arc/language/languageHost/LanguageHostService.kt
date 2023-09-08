package com.github.idevelopthings.arc.language.languageHost

import com.github.idevelopthings.arc.settings.ToolSettingsConfigurable
import com.github.idevelopthings.arc.settings.ToolSettingsState
import com.google.gson.Gson
import com.intellij.execution.process.ScriptRunnerUtil
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.psi.PsiFile

interface ILanguageHostService {
		fun annotate(file: PsiFile): MutableMap<String, MutableList<DiagnosticMessage>>
}


class LanguageHostService(private val project: Project) : ILanguageHostService {

		companion object {
				val LOG = logger<LanguageHostService>()
		}

		override fun annotate(file: PsiFile): MutableMap<String, MutableList<DiagnosticMessage>> {
				val settings = ToolSettingsState.instance
				if (!validateToolPath(settings)) return mutableMapOf()

				val path = FileUtil.toSystemDependentName(file.virtualFile.path)

				val procPath = settings.getCorrectPath()

				val proc = ScriptRunnerUtil.execute(
						// "/Users/sam/Code/Projects/ArcLang/ArcInterpreter/arc",
						procPath,
						project.basePath,
						file.virtualFile,
						arrayOf(
								"--dir", project.basePath,
								"--lint",
								"--format", "json",
								"--log-output", "stderr",
								"--stdin",
								path
						)
				)
				thisLogger().warn("Host cli args -> ${proc.commandLine}")
				proc.processInput.use {
						it.write(file.viewProvider.document.text.toByteArray())
				}
				proc.process.errorReader()?.use {
						var line: String?
						while (it.readLine().also { line = it } != null) {
								LOG.warn("Linter(stderr) -> $line")
						}
				}

				val gson = Gson()

				val diagnostics = mutableListOf<LintingDiagnosticsOutput>()

				val output = ScriptRunnerUtil.getProcessOutput(proc, ScriptRunnerUtil.STDOUT_OUTPUT_KEY_FILTER, 5000)

				output.split("\n").forEach {
						if (it.isBlank()) return@forEach

						val obj = gson.fromJson(it, LintingDiagnosticsOutput::class.java)
						LOG.warn("Linter(stdout) -> $it")
						diagnostics.add(obj)
				}

				return LintingDiagnosticsOutput.mapByPath(diagnostics)
		}

		private fun validateToolPath(settings: ToolSettingsState): Boolean {
				if (settings.checkValidToolPath())
						return true

				val notification = Notification(
						"Arc",
						"Invalid Arc tool path",
						"Arc path is not configured correctly, double check the arc tool settings/your PATH.\nIt's not possible to lint your code for errors without valid configuration.",
						NotificationType.ERROR
				)
				notification.addAction(object : NotificationAction("Open settings") {
						override fun actionPerformed(e: AnActionEvent, notification: Notification) {
								notification.expire()
								ShowSettingsUtil.getInstance().editConfigurable(project, ToolSettingsConfigurable())
						}
				})
				notification.notify(project)

				return false
		}

}
