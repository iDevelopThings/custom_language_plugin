package com.github.idevelopthings.arc.completion

import com.github.idevelopthings.arc.ArcLanguage
import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.Icons
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.findParentOfType
import com.intellij.util.ProcessingContext
import com.github.idevelopthings.arc.psi.ArcType
import com.github.idevelopthings.arc.psi.ArcTypes
import com.github.idevelopthings.arc.psi.ArcValueExpr
import com.intellij.psi.ResolveState
import com.intellij.psi.util.PsiTreeUtil

class ArcCompletionContributor : CompletionContributor() {
		init {

				extend(
						CompletionType.BASIC,
						PlatformPatterns.psiElement()

								.andOr(
										PlatformPatterns.psiElement().withParent(PlatformPatterns.psiElement(ArcTypes.MEMBER_ACCESS_EXPR)),
										PlatformPatterns.psiElement().afterLeaf(".")
								)

								.withLanguage(ArcLanguage.INSTANCE),
						ArcCompletionProvider()
				)
				extend(
						CompletionType.BASIC,
						PlatformPatterns.psiElement(ArcTypes.ID)
								.withLanguage(ArcLanguage.INSTANCE),
						ArcLocalVarCompletionProvider()
				)
		}

		override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
				super.fillCompletionVariants(parameters, result)
		}
}

class ArcLocalVarCompletionProvider : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {

				val element = parameters.originalFile.findElementAt(parameters.position.textOffset)
				if (element == null) {
						thisLogger().warn("Element is null")
						return
				}

				val parent = element.findParentOfType<ArcValueExpr>()
				if (parent == null) {
						thisLogger().warn("Parent is null")
						return
				}

				val processor = DeclarationLookupPartialProcessor(parent)

				PsiTreeUtil.treeWalkUp(
						processor,
						parent,
						parent.containingFile,
						ResolveState.initial()
				)

				if (processor.resolved.isNotEmpty()) {
						thisLogger().warn("Resolved ${processor.resolved.size} possible references/declarations")
						processor.resolved.forEach {
								result.addElement(
										LookupElementBuilder.create(it.element.text)
												.withIcon(Icons.LogoLight)
												.withPsiElement(it.element)
								)
						}
				}

		}
}

class ArcCompletionProvider : CompletionProvider<CompletionParameters?>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
				val position = parameters.position
				val project: Project = position.project

//				logger<ArcCompletionContributor>().warn("gib lookups pls")

				// We need to find the previous identifiers name
				// We can either be at `var.` or `var.x`<- x being some user input
				// We need to find the identifier before the dot

				var ident = position.node
				var referenceVar = position.node

				var identType: ArcType? = null

				if (ident.treePrev != ArcTypes.DOT) {
						// If we're not at a DOT, then we're already past `var.`, we're now at `var.x`
						// Now we just look up `var`
						referenceVar = ident.treePrev
						while (
								referenceVar.elementType != ArcTypes.ID &&
								referenceVar.elementType != ArcTypes.VALUE_EXPR
						) {
								referenceVar = referenceVar.treePrev
						}
				}

				if (referenceVar.psi is ArcValueExpr) {
						val objId = ArcPsiUtil.resolveMemberAccessDeclaration(
								referenceVar.psi,
								ArcPsiUtil.ResolutionKind.TYPE_DECLARATION
						)
						if (objId != null) {
								(objId.parent as ArcObjectDeclaration).getFields().forEach {
										result.addElement(
												LookupElementBuilder.create(it.objectFieldKey.id.text)
														.withIcon(Icons.LogoLight)
														.withPsiElement(it.objectFieldKey)

										)
								}

								return
						}
				}

				identType = ArcUtil.findBlockVarType(project, referenceVar)

				if (identType == null) {
						// thisLogger().warn("Type is null")
						return
				}

				// thisLogger().warn("Found ident: ${ident.text}")

				val decl = ArcUtil.findObjectDeclarations(project, identType.id.text).firstOrNull()
				if (decl == null) {
						// thisLogger().warn("Decl is null")
						return
				}

				decl.getFields().forEach {
						result.addElement(
								LookupElementBuilder.create(it.objectFieldKey.id.text)
										.withIcon(Icons.LogoLight)
										.withPsiElement(it.objectFieldKey)

						)
				}

//				result.addElement(
//						LookupElementBuilder.create("Hello").withIcon(Icons.LogoLight)
//				)

//				val lookups = ArcUtil.findTypeDefinitions(project)
//
//				logger<ArcCompletionContributor>().warn("Lookups found: ${lookups.size}")
//
//				lookups.forEach {
//						logger<ArcCompletionContributor>().warn("Lookup: ${it.nameIdentifier!!.text}")
//						result.addElement(
//								LookupElementBuilder.create(it.nameIdentifier!!.text).withIcon(Icons.LogoLight).withPsiElement(it)
//						)
//				}

		}


}

