package com.github.idevelopthings.customlanguageplugin

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class DataFusionFileType : LanguageFileType(DataFusionLanguage.INSTANCE) {
		override fun getName(): String {
				return "Data Fusion"
		}

		override fun getDescription(): String {
				return "Data Fusion language file"
		}

		override fun getDefaultExtension(): String {
				return "sl"
		}

		override fun getIcon(): Icon {
				return Icons.LogoLight
		}

		companion object {
				val INSTANCE = DataFusionFileType()
		}
}
