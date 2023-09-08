package com.github.idevelopthings.arc.language.analysis

import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.github.idevelopthings.arc.psi.ArcVisitor
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.ResolveState

class ArcCheckVariableIsDefined : LocalInspectionTool() {

		override fun getDisplayName(): String {
				return "Checks if variable is defined in the scope"
		}

		override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
				return ArcCheckVariableIsDefinedVisitor(holder)
		}
}

class ArcCheckVariableIsDefinedVisitor(
		private var holder: ProblemsHolder,
		private var resolveState: ResolveState = ResolveState.initial(),
) : ArcVisitor() {

		companion object {
				val LOG = logger<ArcCheckVariableIsDefinedVisitor>()
		}

		override fun visitSimpleRefExpr(o: ArcSimpleRefExpr) {
				if (o.isCallExpression) {
						super.visitSimpleRefExpr(o)
						return
				}


				val ref = o.reference?.resolve()
				if (ref == null) {
						holder.registerProblem(
								o,
								"'${o.text}' is not defined",
								ProblemHighlightType.ERROR,
						)
				}

				super.visitSimpleRefExpr(o)
		}

}
