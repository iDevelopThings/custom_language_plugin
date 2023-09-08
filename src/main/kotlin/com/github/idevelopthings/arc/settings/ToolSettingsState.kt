package com.github.idevelopthings.arc.settings

import com.intellij.execution.process.ScriptRunnerUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.SettingsCategory
import com.intellij.openapi.components.State
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.openapi.components.Storage
import com.intellij.openapi.diagnostic.thisLogger


@State(name = "ToolSettingsState", storages = [Storage("arc_settings.xml")], category = SettingsCategory.TOOLS)
class ToolSettingsState : PersistentStateComponent<ToolSettingsState> {

		var toolInstallationPath: String? = "arc"
		var isDefinedOnPath = true

		override fun getState(): ToolSettingsState {
				return this
		}

		override fun loadState(state: ToolSettingsState) {
				XmlSerializerUtil.copyBean(state, this)
		}

		fun checkValidToolPath(): Boolean {
				thisLogger().warn("Checking tool path -> ${this.toolInstallationPath}")
				if (this.isDefinedOnPath) {
						return ScriptRunnerUtil.isExecutableInPath("arc")
				}

				if (!this.toolInstallationPath.isNullOrEmpty()) {
						thisLogger().warn("Checking tool path #2 -> ${this.toolInstallationPath}")
						if (ScriptRunnerUtil.isExecutableInPath(this.toolInstallationPath!!)) {
								return true
						}

						thisLogger().warn("Checking actual tool path exists -> ${this.toolInstallationPath}")
						val file = java.io.File(this.toolInstallationPath!!)
						if (file.exists()) {
								return true
						}
				}

				return false
		}

		fun getCorrectPath():String {
				thisLogger().warn("Checking correct tool path -> ${this.toolInstallationPath}")
				if (this.isDefinedOnPath) {
						return "arc"
				}

				if (!this.toolInstallationPath.isNullOrEmpty()) {
						if (ScriptRunnerUtil.isExecutableInPath(this.toolInstallationPath!!)) {
								return "arc"
						}
						val file = java.io.File(this.toolInstallationPath!!)
						if (file.exists()) {
								return file.absolutePath
						}
				}

				return "arc"
		}

		companion object {
				val instance: ToolSettingsState
						get() = ApplicationManager.getApplication().getService(ToolSettingsState::class.java)
		}
}
