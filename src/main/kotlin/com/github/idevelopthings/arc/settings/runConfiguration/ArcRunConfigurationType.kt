package com.github.idevelopthings.arc.settings.runConfiguration

import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.NotNullLazyValue


class ArcRunConfigurationType : ConfigurationTypeBase(
		ID,
		"Arc",
		"Arc run configuration",
		NotNullLazyValue.createValue { AllIcons.Nodes.Console }
) {

		init {
				addFactory(ArcConfigurationFactory(this))
		}

		companion object {
				const val ID = "ArcRunConfiguration"
		}
}
