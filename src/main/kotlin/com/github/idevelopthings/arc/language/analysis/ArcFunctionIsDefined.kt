package com.github.idevelopthings.arc.language.analysis

import com.github.idevelopthings.arc.psi.ArcCallExpr
import com.github.idevelopthings.arc.psi.ArcVisitor
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.ResolveState

class ArcFunctionIsDefined : LocalInspectionTool() {

		override fun getDisplayName(): String {
				return "Check if function is defined"
		}

		override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
				return ArcFunctionIsDefinedVisitor(holder)
		}
}

class ArcFunctionIsDefinedVisitor(
		private var holder: ProblemsHolder,
		private var resolveState: ResolveState = ResolveState.initial(),
) : ArcVisitor() {

		companion object {
				val LOG = thisLogger()
		}

		override fun visitCallExpr(o: ArcCallExpr) {
				super.visitCallExpr(o)
				if (o.isBuiltin) return

				val ref = o.reference?.resolve()
				if (ref == null) {
						holder.registerProblem(
								o.refExpr.id,
								"Function \"${o.refExpr.id.text}\" is not defined",
								ProblemHighlightType.ERROR,
						)
				}


		}

//		override fun visitCallExpr(o: ArcCallExpr) {
//				val fnName = o.text
//				val access = o.expressionList[0] as ArcMemberAccessExpr
//
//				if (!ArcPsiUtil.lookupFunction(access)) {
//						holder.registerProblem(
//								o,
//								"2. Function $fnName is not defined",
//								ProblemHighlightType.ERROR,
//						)
//				}
//
//				super.visitCallExpr(o)
//		}
}
