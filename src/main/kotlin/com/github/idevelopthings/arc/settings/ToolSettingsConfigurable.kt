package com.github.idevelopthings.arc.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent


class ToolSettingsConfigurable : Configurable {

		private var mySettingsComponent: ToolSettingsComponent? = null

		fun preferredFocusedComponent(): JComponent = mySettingsComponent?.getPreferredFocusedComponent()!!

		@Nls(capitalization = Nls.Capitalization.Title)
		override fun getDisplayName(): String {
				return "Arc Settings"
		}

		override fun createComponent(): JComponent {
				mySettingsComponent = ToolSettingsComponent()
				return mySettingsComponent!!.getPanel()!!
		}

		override fun isModified(): Boolean {
				val settings: ToolSettingsState = ToolSettingsState.instance
				if (mySettingsComponent == null) {
						return false
				}

				var modified: Boolean = mySettingsComponent!!.getToolInstallationPath() != settings.toolInstallationPath
				modified = modified or (mySettingsComponent!!.getIsDefinedOnPath() != settings.isDefinedOnPath)
				return modified
		}

		override fun apply() {
				val settings: ToolSettingsState = ToolSettingsState.instance
				settings.toolInstallationPath = mySettingsComponent!!.getToolInstallationPath()
				settings.isDefinedOnPath = mySettingsComponent!!.getIsDefinedOnPath()
		}

		override fun reset() {
				val settings: ToolSettingsState = ToolSettingsState.instance
				mySettingsComponent!!.setToolInstallationPath(settings.toolInstallationPath!!)
				mySettingsComponent!!.setIsDefinedOnPath(settings.isDefinedOnPath)
		}

		override fun disposeUIResources() {
				mySettingsComponent = null
		}


}
