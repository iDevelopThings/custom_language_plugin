package com.github.idevelopthings.customlanguageplugin

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.elementType
import com.intellij.psi.util.findParentOfType
import com.intellij.util.ProcessingContext
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionType
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes

class DataFusionCompletionContributor : CompletionContributor() {
		init {

				extend(
						CompletionType.BASIC,
						PlatformPatterns.psiElement()

								.andOr(
										PlatformPatterns.psiElement().withParent(PlatformPatterns.psiElement(DataFusionTypes.MEMBER_ACCESS_EXPR)),
										PlatformPatterns.psiElement().afterLeaf(".")
								)

								.withLanguage(DataFusionLanguage.INSTANCE),
						DataFusionCompletionProvider()
				)
		}

		override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
				super.fillCompletionVariants(parameters, result)
		}
}

class DataFusionCompletionProvider : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
				val position = parameters.position
				val project: Project = position.project

//				logger<DataFusionCompletionContributor>().warn("gib lookups pls")

				// We need to find the previous identifiers name
				// We can either be at `var.` or `var.x`<- x being some user input
				// We need to find the identifier before the dot

				var ident = position.node
				var referenceVar = position.node

				var identType: DataFusionType? = null

				if (ident.treePrev != DataFusionTypes.DOT) {
						// If we're not at a DOT, then we're already past `var.`, we're now at `var.x`
						// Now we just look up `var`
						referenceVar = ident.treePrev
						while (referenceVar.elementType != DataFusionTypes.ID) {
								referenceVar = referenceVar.treePrev
						}
						// thisLogger().warn("Found ident: ${referenceVar.text}")
				}

				identType = DataFusionUtil.findBlockVarType(project, referenceVar)

				if (identType == null) {
						// thisLogger().warn("Type is null")
						return
				}

				// thisLogger().warn("Found ident: ${ident.text}")

				val decl = DataFusionUtil.findObjectDeclarations(project, identType.id.text).firstOrNull()
				if (decl == null) {
						// thisLogger().warn("Decl is null")
						return
				}

				decl.getFields().forEach{
						result.addElement(
								LookupElementBuilder.create(it.objectFieldKey.id.text)
										.withIcon(Icons.LogoLight)
										.withPsiElement(it.objectFieldKey)

						)
				}

//				result.addElement(
//						LookupElementBuilder.create("Hello").withIcon(Icons.LogoLight)
//				)

//				val lookups = DataFusionUtil.findTypeDefinitions(project)
//
//				logger<DataFusionCompletionContributor>().warn("Lookups found: ${lookups.size}")
//
//				lookups.forEach {
//						logger<DataFusionCompletionContributor>().warn("Lookup: ${it.nameIdentifier!!.text}")
//						result.addElement(
//								LookupElementBuilder.create(it.nameIdentifier!!.text).withIcon(Icons.LogoLight).withPsiElement(it)
//						)
//				}

		}


}

