package com.github.idevelopthings.arc

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ArcFileType : LanguageFileType(ArcLanguage) {
		override fun getName(): String {
				return "Arc"
		}

		override fun getDescription(): String {
				return "Arc language file"
		}

		override fun getDefaultExtension(): String {
				return "arc"
		}

		override fun getIcon(): Icon {
				return Icons.LogoLight
		}

		companion object {
				val INSTANCE = ArcFileType()
		}
}
