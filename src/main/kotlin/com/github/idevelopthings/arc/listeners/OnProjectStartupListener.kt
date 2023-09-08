package com.github.idevelopthings.arc.listeners

import com.github.idevelopthings.arc.language.ArcBuiltinsProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import kotlin.coroutines.Continuation

class OnProjectStartupListener : ProjectActivity {

		override suspend fun execute(project: Project) {
//				ArcBuiltinsProvider.loadBuiltinStubs(project)
		}
}
