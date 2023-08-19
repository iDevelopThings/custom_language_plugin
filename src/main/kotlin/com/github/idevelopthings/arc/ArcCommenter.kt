package com.github.idevelopthings.arc

import com.intellij.lang.Commenter

class ArcCommenter : Commenter {

		override fun getLineCommentPrefix(): String? {
				return "//"
		}

		override fun getBlockCommentPrefix(): String? {
				return "/*"
		}

		override fun getBlockCommentSuffix(): String? {
				return "*/"
		}

		override fun getCommentedBlockCommentPrefix(): String? {
				return "/**"
		}

		override fun getCommentedBlockCommentSuffix(): String? {
				return "*/"
		}
}
