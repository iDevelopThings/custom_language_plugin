package com.github.idevelopthings.arc.language.analysis

import com.github.idevelopthings.arc.psi.ArcObject
import com.github.idevelopthings.arc.psi.ArcTopLevelDeclaration
import com.github.idevelopthings.arc.psi.ArcVisitor
import com.intellij.codeInspection.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile

class ArcLocalInspection : LocalInspectionTool() {

		override fun getDisplayName(): String {
				return "Arc Local Inspection"
		}

		override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
				return object : ArcVisitor() {

						override fun visitFile(file: PsiFile) {
//								holder.registerProblem(
//										file.firstChild,
//										"idk",
//										ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
//								)
//								super.visitFile(file)
						}

						override fun visitObject(o: ArcObject) {
								thisLogger().warn("visiting object")
								super.visitObject(o)
						}

						override fun visitTopLevelDeclaration(o: ArcTopLevelDeclaration) {
//								holder.registerProblem(
//										o,
//										"Top level declaration",
//										ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
//								)
//								super.visitTopLevelDeclaration(o)
						}

				}
		}
}
