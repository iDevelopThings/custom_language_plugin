package com.github.idevelopthings.arc.lsp
//
//import com.intellij.execution.configurations.GeneralCommandLine
//import com.intellij.openapi.project.Project
//import com.intellij.openapi.vfs.VirtualFile
//import com.intellij.platform.lsp.api.LspServerSupportProvider
//import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
//import com.intellij.platform.lsp.api.customization.LspCodeActionsSupport
//import com.intellij.platform.lsp.api.customization.LspCommandsSupport
//import com.intellij.platform.lsp.api.customization.LspCompletionSupport
//
//class ArcLspServiceDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Arc") {
//		override fun isSupportedFile(file: VirtualFile) = file.extension == "arc"
//
//		override val lspGoToDefinitionSupport: Boolean
//				get() = false
//
//		override val lspCommandsSupport: LspCommandsSupport?
//				get() = null
//
//		override val lspCompletionSupport: LspCompletionSupport?
//				get() = null
//
//		override val lspCodeActionsSupport: LspCodeActionsSupport?
//				get() = null
//
//
//		override fun createCommandLine(): GeneralCommandLine {
//				return GeneralCommandLine().apply {
//						withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
//						withCharset(Charsets.UTF_8)
//						withExePath("/Users/sam/Code/Projects/ArcLang/ArcInterpreter/arc")
//						addParameter("-lsp")
//						addParameter("-lsp-protocol=stdio")
//				}
//		}
//}
