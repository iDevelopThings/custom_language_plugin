package com.github.idevelopthings.arc.settings.runConfiguration

import com.intellij.execution.configurations.RunConfigurationOptions
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.components.StoredProperty
import com.intellij.util.xmlb.annotations.OptionTag


class ArcRunConfigurationOptions : RunConfigurationOptions() {

		// private val myScriptName: StoredProperty<String?> = string("")
		// 		.provideDelegate(this, "scriptName")

		@get:OptionTag(tag = "scriptName", valueAttribute = "value")
		var scriptName: String? by string(ProjectUtil.getActiveProject()?.basePath)

		// var scriptName: String
		// 		get() = myScriptName
		// 		set(scriptName) {
		// 				myScriptName.setValue(this, scriptName)
		// 		}
}
