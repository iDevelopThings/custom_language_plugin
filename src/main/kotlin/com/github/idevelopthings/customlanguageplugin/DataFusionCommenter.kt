package com.github.idevelopthings.customlanguageplugin

import com.intellij.lang.Commenter

class DataFusionCommenter : Commenter {

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
