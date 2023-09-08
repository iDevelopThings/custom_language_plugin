package com.github.idevelopthings.arc.settings.runConfiguration

import com.github.idevelopthings.arc.Icons
import com.github.idevelopthings.arc.execution.CustomProcessHandler
import com.github.idevelopthings.arc.settings.ToolSettingsState
import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import javax.swing.Icon

// open class RustConsoleBuilder(
// 		project: Project,
// 		val config: ArcRunConfiguration
// ) : TextConsoleBuilderImpl(project, ExecutionSearchScopes.executionScope(project, config)) {
// 		override fun createConsole(): ConsoleView = TerminalExecutionConsole(project, null)
// }

open class ArcRunConfiguration(
		project: Project?,
		factory: ConfigurationFactory?,
		name: String?
) : RunConfigurationBase<ArcRunConfigurationOptions?>(project!!, factory, name) {

		override fun getOptions(): ArcRunConfigurationOptions {
				return super.getOptions() as ArcRunConfigurationOptions
		}

		var scriptName: String?
				get() = options.scriptName
				set(scriptName) {
						options.scriptName = scriptName
				}

		override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState? {
				return object : CommandLineState(environment) {

						@Throws(ExecutionException::class)
						override fun startProcess(): ProcessHandler {
								val settings = ToolSettingsState.instance

								val args = mutableListOf(
										settings.getCorrectPath(),
										"--log-output", "stdout",
										"--dir", project.basePath!!,
										scriptName!!
								)

								val commandLine = GeneralCommandLine(args)
										.withEnvironment("CLICOLOR_FORCE", "1")
										.withEnvironment("TERM", "ansi")
										.withCharset(Charsets.UTF_8)
										.withRedirectErrorStream(true)


								// val cmdLine = PtyCommandLine(commandLine)
								// 		.withInitialColumns(PtyCommandLine.MAX_COLUMNS)
								// 		.withConsoleMode(false)

								// val processHandler = ProcessHandlerFactory.getInstance()
								// 		.createColoredProcessHandler(cmdLine)

								val processHandler = CustomProcessHandler(commandLine)
								processHandler.setHasPty(true)

								ProcessTerminatedListener.attach(processHandler)

								return processHandler
						}
				}
		}

		override fun getIcon(): Icon? {
				return Icons.LogoLight
		}

		override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> {
				return ArcRunConfigurationSettingsEditor()
		}

}
