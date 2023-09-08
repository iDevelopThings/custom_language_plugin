package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.language.LanguageInfo
import com.github.idevelopthings.arc.psi.impl.ArcPsiUtilImpl
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker

interface ArcFunction : ArcBaseDeclaration, PsiElementWithLookup {

		override fun getNameIdentifier(): ArcFuncId?

		override fun getName(): String

		override fun getPresentation(): ItemPresentation?

		fun belongsToType(objDeclaration: ArcObjectDeclaration): Boolean

		fun receiverIsNamed(name: String): Boolean

		fun hasArgumentWithName(name: String): Boolean

		override fun getLookupElement(): LookupElement

		fun getObject(): ArcObjectDeclaration?

		fun hasArguments(): Boolean

		fun isGlobal(): Boolean
}


open class ArcFunctionImpl(node: ASTNode) :
		ArcBaseDeclarationImpl(node),
		ArcFunction,
		PsiElementWithLookup {

		override fun receiverIsNamed(name: String): Boolean {
				if (this is ArcFuncDeclaration) {
						val receiver = this.funcReceiverDeclaration ?: return false
						return receiver.funcReceiverName?.text == name
				}
				return false
		}

		override fun hasArgumentWithName(name: String): Boolean {
				if (this is ArcFuncDeclaration) {
						return this.argumentDeclarationList?.argumentDeclarationList?.any { it.argumentId.text == name } ?: false
				}
				return false
		}

		override fun getNameIdentifier(): ArcFuncId? {
				return findChildByType(ArcTypes.FUNC_ID)
		}

		override fun getName(): String {
				return nameIdentifier?.text ?: "Unknown name?"
		}

		override fun getObject(): ArcObjectDeclaration? {
				return CachedValuesManager.getCachedValue(this) {
						var objDecl: ArcObjectDeclaration? = null
						val recv = (this as ArcFuncDeclaration).funcReceiverDeclaration
						if (recv != null) {
								objDecl = ArcUtil.findObjectDeclarations((this as ArcFuncDeclaration).project, recv.type.name!!).firstOrNull()
						}
						CachedValueProvider.Result.create(objDecl, PsiModificationTracker.MODIFICATION_COUNT)
				}
		}

		override fun belongsToType(objDeclaration: ArcObjectDeclaration): Boolean {
				val fn = this as ArcFuncDeclaration
				val receiver = fn.funcReceiverDeclaration ?: return false

				return receiver.type.text == objDeclaration.name
		}

		/* override fun getOwnDeclarations(): MutableCollection<out PsiSymbolDeclaration> {
				val block = PsiTreeUtil.findChildOfType(this, ArcBlockBody::class.java)
				val declarations = mutableListOf<PsiSymbolDeclaration>()

				block?.statementList?.forEach {
						if (it is ArcVariableDeclaration)
								declarations.add(it.varId as PsiSymbolDeclaration)
				}

				if (this is ArcFuncDeclaration) {
						this.argumentDeclarationList?.argumentDeclarationList?.forEach {
								declarations.add(it as PsiSymbolDeclaration)
						}

						if (this.funcReceiverDeclaration != null) {
								val symbol = this.funcReceiverDeclaration!!.funcReceiverName
								declarations.add(symbol!! as PsiSymbolDeclaration)
						}
				}

				return declarations
		} */

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				// val block = PsiTreeUtil.findChildOfType(this, ArcBlockBody::class.java)
				// block?.statementList?.forEach {
				// 		if (!processor.execute(it, state)) {
				// 				return false
				// 		}
				// }

				if (this is ArcFuncDeclaration) {

						if (this.blockBody != null) {
								if (!ArcPsiUtilImpl.processDeclarations(this.blockBody!!, processor, state, lastParent!!)) {
										return false
								}
						}

						this.argumentDeclarationList?.argumentDeclarationList?.forEach {
								if (!processor.execute(it, state)) {
										return false
								}
						}

						if (this.funcReceiverDeclaration != null) {
								if (!processor.execute(this.funcReceiverDeclaration!!.funcReceiverName!!, state)) {
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

						override fun getPresentableText(): String {
								var str = func.name + (func.argumentDeclarationList?.presentation?.presentableText ?: "()")

								val rt = func.type?.text
								if (rt != null) {
										str += " -> $rt"
								}

								return str
						}
				}
		}

		override fun hasArguments(): Boolean {
				if (this is ArcFuncDeclaration) {
						if (this.argumentDeclarationList == null) {
								return false
						}
						if (this.argumentDeclarationList?.argumentDeclarationList?.isEmpty()!!) {
								return false
						}
						return true
				}
				return false
		}

		override fun getLookupElement(): LookupElement {
				return ArcFunctionLookupElement(this as ArcFuncDeclaration)
		}

		override fun isGlobal(): Boolean {
				return this is ArcFuncDeclaration && this.funcReceiverDeclaration == null
		}
}

class ArcFunctionLookupElement(val fn: ArcFuncDeclaration) : LookupElement() {
		override fun getLookupString(): String {
				return fn.getName()
		}

		override fun renderElement(presentation: LookupElementPresentation) {
				presentation.icon = AllIcons.Nodes.Function
				presentation.itemText = fn.getName()


				presentation.appendTailText("(", true)

				val args = fn.argumentDeclarationList?.argumentDeclarationList
				args?.forEach {
						presentation.appendTailText(it.argumentId.text, true)
						presentation.appendTailText(" ", true)
						presentation.appendTailText(it.type.text, !LanguageInfo.builtinTypeNames.contains(it.type.text))
						if (args.indexOf(it) < args.size - 1)
								presentation.appendTailText(", ", true)
				}
				presentation.appendTailText(")", true)
				fn.type?.let {
						presentation.appendTailText(" ${it.id.text}", !LanguageInfo.builtinTypeNames.contains(it.id.text))
				}

				presentation.typeText = fn.getObject()?.name
				if (presentation.typeText == null) {
						presentation.typeText = fn.containingFile.name
				} else {
						presentation.isTypeGrayed = LanguageInfo.builtinTypeNames.contains(presentation.typeText)
				}
		}

		override fun handleInsert(context: InsertionContext) {
				val doc = context.document
				context.commitDocument()
				val t = context.tailOffset

				doc.insertString(t, "();")
				if (fn.hasArguments()) {
						PsiDocumentManager.getInstance(context.project).commitDocument(doc)
						context.editor.caretModel.moveToOffset(t + 1)
				} else {
						PsiDocumentManager.getInstance(context.project).commitDocument(doc)
						context.editor.caretModel.moveToOffset(t + 3)
				}

				super.handleInsert(context)
		}

}
