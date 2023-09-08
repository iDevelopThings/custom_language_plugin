package com.github.idevelopthings.arc.settings.runConfiguration

import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel


class ArcRunConfigurationSettingsEditor : SettingsEditor<ArcRunConfiguration?>() {

		private val myPanel: JPanel
		private val scriptPathField: TextFieldWithBrowseButton = TextFieldWithBrowseButton()

		init {
				scriptPathField.addBrowseFolderListener(
						"Select Script File", null, null,
						FileChooserDescriptorFactory.createSingleFileDescriptor()
				)
				val form = FormBuilder.createFormBuilder()
				form.addLabeledComponent("Script file", scriptPathField)

				myPanel = form.panel
		}

		override fun resetEditorFrom(runConfig: ArcRunConfiguration) {
				scriptPathField.text = runConfig.scriptName.toString()

		}

		override fun applyEditorTo(runConfig: ArcRunConfiguration) {
				if (scriptPathField.text.isEmpty()) {
						runConfig.scriptName = runConfig.project.basePath
				} else {
						runConfig.scriptName = scriptPathField.getText()
				}
		}


		override fun createEditor(): JComponent {
				return myPanel
		}
}
