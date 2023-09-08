package com.github.idevelopthings.arc.language.gutter

import com.github.idevelopthings.arc.psi.ArcFuncId
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement


class ArcRunLineMarkerProvider : RunLineMarkerContributor() {

		override fun getInfo(element: PsiElement): Info? {
				if (element.parent !is ArcFuncId) return null
				if (element.text != "main") return null

				return Info(
						AllIcons.RunConfigurations.TestState.Run,
						{ "Run script" },
						object : AnAction("Run ${element.containingFile.name}") {
								override fun actionPerformed(e: AnActionEvent) {
										thisLogger().warn("Run...")
								}
						}
				)
		}


}
