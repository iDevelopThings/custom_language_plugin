package com.github.idevelopthings.arc

import com.intellij.ide.ApplicationInitializedListener
import kotlinx.coroutines.CoroutineScope
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition


class ArcApplicationInitializedListener : ApplicationInitializedListener {
		override suspend fun execute(asyncScope: CoroutineScope) {
				super.execute(asyncScope)

//				ProcessBuilderServerDefinition()
				IntellijLanguageClient.addServerDefinition(
						RawCommandServerDefinition("sl", arrayOf(
								"/Users/sam/Code/GoStuffs/interpreted_lang/interpreted_lang",
								"-lsp",
								"-lsp-protocol=stdio"
						))
				)

		}
}
