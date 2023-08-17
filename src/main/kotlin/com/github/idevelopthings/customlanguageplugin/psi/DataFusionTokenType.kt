package com.github.idevelopthings.customlanguageplugin.psi

import com.intellij.psi.tree.IElementType
import com.github.idevelopthings.customlanguageplugin.DataFusionLanguage

class DataFusionTokenType(debugName: String) : IElementType(debugName, DataFusionLanguage.INSTANCE) {
		override fun toString(): String {
				return "DataFusionTokenTypes." + super.toString()
		}
}
