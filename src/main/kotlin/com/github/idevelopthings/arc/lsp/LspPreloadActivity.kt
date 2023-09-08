package com.github.idevelopthings.arc.lsp

//import com.intellij.openapi.application.PreloadingActivity
//import com.intellij.openapi.diagnostic.logger
//import com.intellij.openapi.progress.ProgressIndicator
//import org.wso2.lsp4intellij.IntellijLanguageClient
//import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
//import org.wso2.lsp4intellij.requests.Timeouts


//class LspPreloadActivity : PreloadingActivity() {
//		override fun preload(indicator: ProgressIndicator?) {
//				IntellijLanguageClient.addServerDefinition(
//						RawCommandServerDefinition(
//								"arc", arrayOf(
//										"/Users/sam/Code/Projects/ArcLang/ArcInterpreter/arc",
//										"-lsp",
//										"-lsp-protocol=stdio"
//								)
//						)
//				)
////				IntellijLanguageClient.setTimeout(Timeouts.INIT, initTimeout)
//		}
//
//		companion object {
//				private val log = logger<LspPreloadActivity>();
//		}
//}
