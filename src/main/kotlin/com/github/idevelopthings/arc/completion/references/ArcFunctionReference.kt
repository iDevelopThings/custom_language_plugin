package com.github.idevelopthings.arc.completion.references

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.collector.TopLevelDeclarationCollector
import com.github.idevelopthings.arc.completion.data.*
import com.github.idevelopthings.arc.completion.processor.ResolverChain
import com.github.idevelopthings.arc.completion.resolution.GlobalDeclarationResolver
import com.github.idevelopthings.arc.psi.*
import com.intellij.model.psi.PsiCompletableReference
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class ArcFunctionReference(
		element: ArcCallExpr,
		rangeInElement: TextRange?,
) : PsiReferenceBase<ArcCallExpr>(element, rangeInElement) {

		var typeInfo: TypeInfo? = null

		override fun resolve(): PsiElement? {
				val results = getReferences()

				// if (typeInfo != null) {
				// 		return typeInfo?.getDeclarationElement()
				// }

				return if (results.size == 1) results[0].element else null
		}

		private fun getReferences(): Array<PsiElementResolveResult> {
				val lhsRef = element.lhs?.reference as ArcMemberReference
				lhsRef.resolve()
				val memberType = lhsRef.memberType
				if (memberType == null) {
						thisLogger().warn("lhsRef.memberType is null")
						return arrayOf()
				}

				val fn = memberType.functions.firstOrNull {
						it.name == element.refExpr.id.text
				} ?: return arrayOf()

				typeInfo = fn

				val decl = fn.getDeclarationElement() as ArcFuncDeclaration



				return arrayOf(
						PsiElementResolveResult(decl.funcId!!)
				)
		}


}
