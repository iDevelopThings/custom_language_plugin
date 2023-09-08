package com.github.idevelopthings.arc.language.analysis.quickfixes

import com.github.idevelopthings.arc.completion.references.ArcMemberReference
import com.github.idevelopthings.arc.language.languageHost.DiagnosticMessage
import com.github.idevelopthings.arc.psi.ArcCallExpr
import com.github.idevelopthings.arc.psi.ArcElementFactory
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.intellij.codeInsight.intention.preview.IntentionPreviewUtils
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.util.findParentOfType
import com.intellij.refactoring.suggested.startOffset

class DefineFunctionQuickFix(diagnosticElement: PsiElement?, d: DiagnosticMessage) : ArcBaseLinterQuickfix(diagnosticElement, d) {

		override fun getText() = "Create function: ${diagnosticElement?.text}"
		override fun getFamilyName() = "Create function"

		private fun locateDeclaration(expr: ArcRefExpr): ArcObjectDeclaration? {
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
				val lhsDeclaration = ref.memberType?.getDeclaration()
				if (lhsDeclaration == null) {
						thisLogger().warn("ref.memberType.getDeclaration() is null")
						return null
				}

				if (lhsDeclaration !is ArcObjectDeclaration) {
						thisLogger().warn("lhsDeclaration is not ArcObjectDeclaration")
						return null
				}

				return lhsDeclaration
		}

		private fun addChange(obj: ArcObjectDeclaration, element: PsiElement) {


				val existingMethods = obj.getMethods()
				// If we have an existing method, then we'll copy its receiver structure...
				// If we don't, we'll just come up with something like `(<lower first char of type> <type>)`

				val fNode = obj.containingFile.node

				if (existingMethods.isEmpty()) {
						val newElement = ArcElementFactory.createFuncDeclaration(
								element.project,
								element.text,
								obj,
						)
						fNode.addChild(newElement.node, fNode.lastChildNode)
						return
				}

				val lastMethod = existingMethods.maxByOrNull { it.startOffset }
				if (lastMethod == null) {
						thisLogger().warn("lastMethod is null")
						return
				}

				// We need to get the parent(top level decl) and then next tree element
				// this should be whitespace after that function declaration(in theory ofc)

				val newElement = ArcElementFactory.createFuncDeclaration(
						element.project,
						element.text,
						lastMethod.funcReceiverDeclaration,
				)

				val offset = lastMethod.parent.node.treeNext

				fNode.addChild(ArcElementFactory.createWhitespace(element.project, "\n").node, offset)
				fNode.addChild(newElement.node, offset)

				// obj.node.addChild(ArcElementFactory.createWhitespace(element.project, "\n").node, obj.node.lastChildNode)
		}

		override fun invoke(project: Project, editor: Editor?, element: PsiElement) {
				if (IntentionPreviewUtils.isPreviewElement(element)) return
				val callExpr = element.findParentOfType<ArcCallExpr>()
				if (callExpr == null) {
						thisLogger().warn("callExpr is null")
						return
				}

				val declaration = locateDeclaration(callExpr.refExpr)
				if (declaration == null) {
						thisLogger().warn("declaration is null")
						return
				}
				CommandProcessor.getInstance().runUndoTransparentAction {
						addChange(declaration, element)
				}
		}

}
