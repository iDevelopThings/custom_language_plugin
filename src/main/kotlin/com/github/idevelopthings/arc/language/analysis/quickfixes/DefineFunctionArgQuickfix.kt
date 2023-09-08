package com.github.idevelopthings.arc.language.analysis.quickfixes

import com.github.idevelopthings.arc.completion.data.FunctionTypeInfo
import com.github.idevelopthings.arc.completion.data.ObjectTypeInfo
import com.github.idevelopthings.arc.completion.references.ArcMemberReference
import com.github.idevelopthings.arc.language.languageHost.DiagnosticMessage
import com.github.idevelopthings.arc.psi.ArcCallExpr
import com.github.idevelopthings.arc.psi.ArcElementFactory
import com.github.idevelopthings.arc.psi.ArcFuncDeclaration
import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.intellij.codeInsight.intention.preview.IntentionPreviewUtils
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.util.findParentOfType

class DefineFunctionArgQuickfix(diagnosticElement: PsiElement?, d: DiagnosticMessage) : ArcBaseLinterQuickfix(diagnosticElement, d) {

		val diagnosticCallExpr: ArcCallExpr?
				get() = diagnosticElement?.findParentOfType<ArcCallExpr>()

		val firstUndeclared
				get() = diagnostic.meta.argumentInfo.firstOrNull { !it.isDeclared }

		override fun getText() = "Add '${firstUndeclared?.name}' as an argument to '${diagnosticCallExpr?.refExpr?.id?.text}'"
		override fun getFamilyName() = "Add function argument to declaration"

		private fun locateTypeInfo(expr: ArcRefExpr): FunctionTypeInfo? {
				val lhs = expr.expression
				if (lhs == null) {
						thisLogger().warn("lhs is null")
						return null
				}

				val ref = lhs.reference
				ref?.resolve()

				if (ref !is ArcMemberReference) {
						thisLogger().warn("ref is not ArcMemberReference")
						return null
				}
				if (ref.memberType == null) {
						thisLogger().warn("ref.memberType is null")
						return null
				}

				val objType = ref.memberType as ObjectTypeInfo
				val fnTypeInfo = objType.functions.firstOrNull { it.name == expr.id.text } ?: return null

				return fnTypeInfo as FunctionTypeInfo?
		}

		private fun addChange(fn: ArcFuncDeclaration, element: PsiElement) {
				val argList = fn.argumentDeclarationList
				val args = argList?.argumentDeclarationList

				diagnostic.meta.argumentInfo.forEachIndexed { index, argInfo ->
						if (argInfo.isDeclared) return@forEachIndexed

						// var prevDeclaration = argList?.argumentDeclarationList?.getOrNull(index - 1) as PsiElement?
						// val newElement = ArcElementFactory.createArgumentDeclaration(
						// 		element.project,
						// 		argInfo.type ?: argInfo.name,
						// )

						var newArgList = ArcElementFactory.createArgumentList(element.project, argList, argInfo.type ?: argInfo.name)

						fn.argumentDeclarationList?.replace(newArgList)

						// val elements = mutableListOf<PsiElement>()

						/* if (index > 0) {
								// elements.add(ArcElementFactory.createWhitespace(element.project, ","))
								// elements.add(ArcElementFactory.createWhitespace(element.project, " "))
								// prevDeclaration = argList?.addAfter(ArcElementFactory.createWhitespace(element.project, ","), prevDeclaration)
								// prevDeclaration = argList?.addAfter(ArcElementFactory.createWhitespace(element.project, " "), prevDeclaration)
								// fn.node.addChild(ArcElementFactory.createWhitespace(element.project, ",").node, argList?.rparen?.node)
								// fn.node.addChild(ArcElementFactory.createWhitespace(element.project, " ").node, argList?.rparen?.node)
						} */

						// argList?.addRangeAfter(elements.first(), elements.last(), prevDeclaration)
						// addRangeAfter
						// argList?.addAfter(newElement, prevDeclaration)
						// fn.node.addChild(newElement.node, argList?.rparen?.node)
				}

				/* suggestions.forEachIndexed { index, s ->
						if (args?.getOrNull(index) != null) return@forEachIndexed
						val newElement = ArcElementFactory.createArgumentDeclaration(element.project, s)
						if (index > 0)
								fn.node.addChild(ArcElementFactory.createWhitespace(element.project, ", ").node, argList?.argumentDeclarationList?.get(index - 1)?.node)
						// else
						// fn.node.addChild(ArcElementFactory.createWhitespace(element.project, " ").node, argList?.lparen?.node)

						fn.node.addChild(newElement.node, argList?.rparen?.node)
				} */


				// fNode.addChild(ArcElementFactory.createWhitespace(element.project, "\n").node, offset)
				// fNode.addChild(newElement.node, offset)

		}

		override fun invoke(project: Project, editor: Editor?, element: PsiElement) {
				if (IntentionPreviewUtils.isPreviewElement(element)) return
				val callExpr = element.findParentOfType<ArcCallExpr>()
				if (callExpr == null) {
						thisLogger().warn("callExpr is null")
						return
				}

				val typeInfo = locateTypeInfo(callExpr.refExpr)
				if (typeInfo == null) {
						thisLogger().warn("declaration is null")
						return
				}

				val declaration = typeInfo.getDeclaration() as ArcFuncDeclaration

				CommandProcessor.getInstance().runUndoTransparentAction {
						addChange(declaration, element)
				}
		}

}
