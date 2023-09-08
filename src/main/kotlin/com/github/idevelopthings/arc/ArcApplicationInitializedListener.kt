package com.github.idevelopthings.arc
//
//import com.intellij.ide.ApplicationInitializedListener
//import com.intellij.openapi.diagnostic.LogLevel
//import kotlinx.coroutines.CoroutineScope
//import org.wso2.lsp4intellij.IntellijLanguageClient
//import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
//import com.intellij.openapi.diagnostic.Logger
//
//
//class ArcApplicationInitializedListener : ApplicationInitializedListener {
//		override suspend fun execute(asyncScope: CoroutineScope) {
//
//				Logger.getInstance(IntellijLanguageClient::class.java).setLevel(LogLevel.ALL)
//
////				ProcessBuilderServerDefinition()
//				IntellijLanguageClient.addServerDefinition(
//						RawCommandServerDefinition(
//								"arc", arrayOf(
//										"/Users/sam/Code/Projects/ArcLang/ArcInterpreter/arc",
//										"-lsp",
//										"-lsp-protocol=stdio"
//								)
//						)
//				)
//
//				super.execute(asyncScope)
//
//		}
//}
