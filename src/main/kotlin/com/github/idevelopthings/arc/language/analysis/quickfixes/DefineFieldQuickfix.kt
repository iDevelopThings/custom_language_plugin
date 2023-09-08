package com.github.idevelopthings.arc.language.analysis.quickfixes

import com.github.idevelopthings.arc.completion.references.ArcMemberReference
import com.github.idevelopthings.arc.language.languageHost.DiagnosticMessage
import com.github.idevelopthings.arc.psi.ArcElementFactory
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.intellij.codeInsight.intention.preview.IntentionPreviewUtils
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

class DefineFieldQuickfix(diagnosticElement: PsiElement?, d: DiagnosticMessage) : ArcBaseLinterQuickfix(diagnosticElement, d) {

		override fun getText() = "Create field: ${diagnosticElement?.text}"
		override fun getFamilyName() = "Create field"

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

		private fun addField(obj: ArcObjectDeclaration, element: PsiElement) {
				val newFieldElement = ArcElementFactory.createObjectFieldDeclaration(
						element.project,
						element.text,
						"string"
				)
				obj.node.addChild(newFieldElement.node, obj.node.lastChildNode)
				obj.node.addChild(ArcElementFactory.createWhitespace(element.project, "\n").node, obj.node.lastChildNode)
		}

		override fun invoke(project: Project, editor: Editor?, element: PsiElement) {
				if (IntentionPreviewUtils.isPreviewElement(element)) return

				if (element.parent !is ArcRefExpr) {
						thisLogger().warn("element.parent is not ArcRefExpr")
						return
				}
				val expr = element.parent as ArcRefExpr
				val declaration = locateDeclaration(expr)
				if (declaration == null) {
						thisLogger().warn("declaration is null")
						return
				}
				CommandProcessor.getInstance().runUndoTransparentAction {
						addField(declaration, element)
				}
		}

}
