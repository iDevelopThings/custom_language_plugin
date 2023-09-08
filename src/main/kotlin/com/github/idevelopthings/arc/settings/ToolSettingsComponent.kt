package com.github.idevelopthings.arc.settings

import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.progress.ModalTaskOwner.project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import javax.swing.JComponent
import javax.swing.JPanel


class ToolSettingsComponent {

		private var myMainPanel: JPanel? = null
		private var installationPathPanel: JPanel? = null

		private val toolInstallationPath = TextFieldWithBrowseButton()
		private val isDefinedOnPath = JBCheckBox("Use tool from PATH")


		init {
				val panel = FormBuilder.createFormBuilder()
				val project = ProjectUtil.getActiveProject()


				val childPanel = FormBuilder.createFormBuilder()
				childPanel.addComponentFillVertically(JPanel(), 0)
				childPanel.addLabeledComponent(JBLabel("Tool installation path:"), toolInstallationPath, 1, true)
				installationPathPanel = childPanel.panel
				panel.addComponent(installationPathPanel!!, 1)

				panel.addComponent(isDefinedOnPath, 1)
				panel.addComponentFillVertically(JPanel(), 0)

				isDefinedOnPath.addItemListener { e ->
						installationPathPanel!!.isVisible = e.stateChange != ItemEvent.SELECTED
				}

				toolInstallationPath.addBrowseFolderListener(
						"Select File",
						"Select the tool installation path",
						project,
						FileChooserDescriptorFactory.createSingleFileDescriptor()
				)

				toolInstallationPath.addActionListener {
						val selectedFilePath: String = toolInstallationPath.getText()
						if (selectedFilePath.isNotEmpty()) {
								val file = java.io.File(selectedFilePath)
								if (file.exists()) {
										toolInstallationPath.setText(file.absolutePath)
										isDefinedOnPath.setSelected(false)
								}
						}
				}

				myMainPanel = panel.panel
		}

		fun getPanel(): JPanel? {
				return myMainPanel
		}

		fun getPreferredFocusedComponent(): JComponent {
				return toolInstallationPath
		}

		fun getToolInstallationPath(): String {
				return toolInstallationPath.getText()
		}

		fun setToolInstallationPath(newPath: String) {
				toolInstallationPath.setText(newPath)
		}

		fun getIsDefinedOnPath(): Boolean {
				return isDefinedOnPath.isSelected
		}

		fun setIsDefinedOnPath(newStatus: Boolean) {
				isDefinedOnPath.setSelected(newStatus)
		}
}
