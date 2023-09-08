package com.github.idevelopthings.arc

import com.intellij.lang.Language

object ArcLanguage : Language(
		Constants.NAME,
		// "text/" + Constants.NAME.lowercase(),
		// "text/x-" + Constants.NAME.lowercase(),
		// "application/x-" + Constants.NAME.lowercase()
) {
		private fun readResolve(): Any = ArcLanguage
		override fun isCaseSensitive() = true
		override fun getDisplayName() = Constants.NAME
}
