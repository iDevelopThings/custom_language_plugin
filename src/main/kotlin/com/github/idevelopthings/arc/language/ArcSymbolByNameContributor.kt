package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.ArcUtil
import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import java.util.*

class ArcSymbolByNameContributor : ChooseByNameContributorEx {

		override fun processNames(processor: Processor<in String>, scope: GlobalSearchScope, filter: IdFilter?) {
				val project: Project = scope.project!!
				val declarationNameList = ArrayList<String>()

				ArcUtil.findAllDeclarations(project) {
						it.getNameIdentifier()?.let {
								declarationNameList.add(it.text)
						}
				}

				ContainerUtil.process(declarationNameList, processor)
		}

		override fun processElementsWithName(name: String, processor: Processor<in NavigationItem>, parameters: FindSymbolParameters) {
				val items = ArrayList<NavigationItem>()

				ArcUtil.findAllDeclarations(parameters.project) {
						it.getNameIdentifier()?.let {
								if (name == it.text) {
										items.add(it as NavigationItem)
								}
						}
				}

				ContainerUtil.process(items, processor)
		}
}
