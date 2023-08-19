package com.github.idevelopthings.arc.psi

import com.intellij.psi.tree.IElementType
import com.github.idevelopthings.arc.ArcLanguage

class ArcTokenType(debugName: String) : IElementType(debugName, ArcLanguage.INSTANCE) {
		override fun toString(): String {
				return "ArcTokenTypes." + super.toString()
		}
}
