package com.github.idevelopthings.arc.lsp

//import com.intellij.execution.configurations.GeneralCommandLine
//import com.intellij.openapi.project.Project
//import com.intellij.openapi.vfs.VirtualFile
//import com.intellij.platform.lsp.api.LspServerSupportProvider
//import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor
//
//class ArcLspServiceDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Arc") {
//		override fun isSupportedFile(file: VirtualFile) = file.extension == "sl"
//
//		override fun createCommandLine(): GeneralCommandLine {
//				return GeneralCommandLine().apply {
//						withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
//						withCharset(Charsets.UTF_8)
//						withExePath("/Users/sam/Code/GoStuffs/interpreted_lang/interpreted_lang")
//						addParameter("-lsp")
//						// addParameter("--stdio")
//				}
//		}
//}
