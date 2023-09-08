package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension
import com.intellij.lang.Language
import com.intellij.navigation.NavigationItem
import javax.swing.Icon


class ArcStructureAwareNavbar() : StructureAwareNavBarModelExtension() {

		override val language: Language
				get() = ArcLanguage

		override fun getPresentableText(obj: Any): String? {
				return when (obj) {
						is NavigationItem -> obj.presentation?.presentableText
						else -> null
				}
		}

		override fun getIcon(obj: Any?): Icon? {
				return when (obj) {
						is NavigationItem -> obj.presentation?.getIcon(false)
						else -> null
				}
		}
}
