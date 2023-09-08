package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CustomCodeStyleSettings


class ArcCodeStyleSettingsProvider : CodeStyleSettingsProvider() {


		override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings {
				return ArcCodeStyleSettings(settings)
		}

		override fun getConfigurableDisplayName(): String {
				return ArcLanguage.displayName
		}


		override fun createConfigurable(settings: CodeStyleSettings, modelSettings: CodeStyleSettings): CodeStyleConfigurable {
				return object : CodeStyleAbstractConfigurable(settings, modelSettings, this.configurableDisplayName) {
						override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
								return ArcCodeStyleMainPanel(currentSettings, settings)
						}
				}
		}



		private class ArcCodeStyleMainPanel(currentSettings: CodeStyleSettings?, settings: CodeStyleSettings?) :
				TabbedLanguageCodeStylePanel(ArcLanguage, currentSettings, settings!!) {

				override fun initTabs(settings: CodeStyleSettings) {
						addIndentOptionsTab(settings)
						addSpacesTab(settings)
						addWrappingAndBracesTab(settings)
				}

		}


}
