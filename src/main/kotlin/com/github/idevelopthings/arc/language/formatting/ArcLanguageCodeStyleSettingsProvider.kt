package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.application.options.IndentOptionsEditor
import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.lang.Language
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider


class ArcLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {

		override fun getLanguage(): Language {
				return ArcLanguage.INSTANCE
		}

		override fun customizeDefaults(commonSettings: CommonCodeStyleSettings, indentOptions: CommonCodeStyleSettings.IndentOptions) {
				super.customizeDefaults(commonSettings, indentOptions)

				indentOptions.INDENT_SIZE = 2;
				indentOptions.SMART_TABS = true;
				indentOptions.CONTINUATION_INDENT_SIZE = 2;
				indentOptions.USE_TAB_CHARACTER = true;
		}

		override fun getIndentOptionsEditor(): IndentOptionsEditor {
				return SmartIndentOptionsEditor()
		}

		override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {

				if (settingsType == SettingsType.SPACING_SETTINGS) {

						consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS")
						consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator")


				}
				if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
						consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE")
				}
				if (settingsType == SettingsType.INDENT_SETTINGS) {
						consumer.showStandardOptions("INDENT_SIZE")
						consumer.showStandardOptions("USE_TAB_CHARACTER")
						consumer.showStandardOptions("TAB_SIZE")
						consumer.showStandardOptions("SMART_TABS")
						consumer.showStandardOptions("CONTINUATION_INDENT_SIZE")
				}
				consumer.showAllStandardOptions()
		}

		override fun getCodeSample(settingsType: SettingsType): String? {
				return """

// Line comment

/* block comment */

object User {
   name string
	 age int
}

func (u User) String() string {
    return u.name;
}

func main() {
	var user = User{name: "John"};
	john.String()
	// fmt::printf("Name: %s\n", john.String());
}
"""
		}
}
