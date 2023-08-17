package com.github.idevelopthings.customlanguageplugin.psi.impl

import com.github.idevelopthings.customlanguageplugin.psi.DataFusionMemberAccessExpr
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionMemberCallExpr
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import org.jetbrains.annotations.NotNull

open class DataFusionMemberCallExpressionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node) {

		fun getAccessor(): DataFusionMemberAccessExprImpl? {
				return this.findChildByType(DataFusionTypes.MEMBER_ACCESS_EXPR)
		}

		override fun getReference(): PsiReference? {
				if(this is DataFusionMemberCallExpr) {
						/*is DataFusionMemberAccessExpr -> {
								val accessExpr = element.expression
								val pls = accessExpr.getReference()

								if(pls != null) {
										val idk = pls.resolve()
										val idkk = pls.resolve()

								}

								return null
						}*/

						val accessor = getAccessor()
						if(accessor != null) {
								return accessor.getReference()
						}
				}


				return DataFusionPsiImplUtil.getReference(this)
		}

}
