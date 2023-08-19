package com.github.idevelopthings.arc.language.presentation

import com.github.idevelopthings.arc.psi.ArcArgumentDeclarationList
import com.intellij.navigation.ItemPresentation
import javax.swing.Icon

class ArgumentDeclarationListPresentation(private val argumentList: ArcArgumentDeclarationList) : ItemPresentation {
		override fun getPresentableText(): String? {
				val args = argumentList.argumentDeclarationList
				var str = "("

				args.forEachIndexed { index, arg ->
						str += arg.id.text
						str += " "
						str += arg.type.text
						if (index < args.size - 1) {
								str += ", "
						}
				}

				str += ")"

				return str
		}

		override fun getIcon(unused: Boolean): Icon? {
				return null
		}
}
