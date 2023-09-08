package com.github.idevelopthings.arc.settings.runConfiguration

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project


open class ArcConfigurationFactory(type: ConfigurationType?) :
		ConfigurationFactory(type!!) {

		override fun getId(): String {
				return ArcRunConfigurationType.ID
		}

		override fun createTemplateConfiguration(project: Project): RunConfiguration {
				return ArcRunConfiguration(project, this, "Arc")
		}

		override fun getOptionsClass(): Class<out BaseState>? {
				return ArcRunConfigurationOptions::class.java
		}
}
