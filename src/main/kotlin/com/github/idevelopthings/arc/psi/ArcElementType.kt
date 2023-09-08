package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.psi.tree.IElementType

class ArcElementType(debugName: String) : IElementType(debugName, ArcLanguage) {
		// override fun createCompositeNode(): ASTNode {
		// 		return ArcTypes.Factory.createElement(this)
		// }
}
