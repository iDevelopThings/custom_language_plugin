package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil


open class ArcFunctionImpl(node: ASTNode) : ASTWrapperPsiElement(node), ArcFunction {

		override fun getNameIdentifier(): ArcFuncId {
				return findChildByType(ArcTypes.FUNC_ID)!!
		}

		override fun getName(): String {
				return getNameIdentifier().text
		}

		override fun belongsToType(objDeclaration: ArcObjectDeclaration): Boolean {
				val fn = this as ArcFuncDeclaration
				val receiver = fn.funcReceiverDeclaration ?: return false

				return receiver.type.text == objDeclaration.name
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				val block = PsiTreeUtil.findChildOfType(this, ArcBlockBody::class.java)
				block?.statementList?.forEach {
						if (!processor.execute(it, state)) {
								return false
						}
				}

				if (this is ArcFuncDeclaration) {
						this.argumentDeclarationList?.argumentDeclarationList?.forEach {
								if (!processor.execute(it, state)) {
										return false
								}
						}

						if (this.funcReceiverDeclaration != null) {
								if (!processor.execute(this.funcReceiverDeclaration!!, state)) {
										return false
								}
						}
				}


				return true
		}

		override fun getPresentation(): ItemPresentation? {
				val func = this as ArcFuncDeclaration

				return object : ItemPresentation {
						override fun getLocationString(): String {
								return func.containingFile.name
						}

						override fun getIcon(unused: Boolean): javax.swing.Icon? {
								return AllIcons.Nodes.Method
//								return getIcon(Iconable.ICON_FLAG_VISIBILITY)
						}

						override fun getPresentableText(): String? {
								var str = name +
													(func.argumentDeclarationList?.presentation?.presentableText ?: "()")

								val rt = func.type?.text
								if (rt != null) {
										str += " -> $rt"
								}

								return str
						}
				}
		}

}
