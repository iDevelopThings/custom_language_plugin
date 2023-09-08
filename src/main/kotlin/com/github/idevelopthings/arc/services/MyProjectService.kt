package com.github.idevelopthings.arc.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.github.idevelopthings.arc.MyBundle

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

		init {
				thisLogger().info(MyBundle.message("projectService", project.name))

		}


}
