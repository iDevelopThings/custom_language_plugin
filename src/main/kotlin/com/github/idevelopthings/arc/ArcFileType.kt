package com.github.idevelopthings.arc

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ArcFileType : LanguageFileType(ArcLanguage.INSTANCE) {
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
				val INSTANCE = ArcFileType()
		}
}
