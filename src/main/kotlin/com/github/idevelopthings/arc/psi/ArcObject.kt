package com.github.idevelopthings.arc.psi

import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.language.LanguageInfo
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.childrenOfType

interface ArcObject : ArcBaseDeclaration {

		fun getFields(): List<ArcObjectFieldDeclaration>

		fun getMember(name: String): ArcObjectFieldDeclaration?

		override fun getPresentation(): ItemPresentation?

		fun getMethods(): List<ArcFuncDeclaration>

}

abstract class ArcObjectImpl(node: ASTNode) : ArcBaseDeclarationImpl(node),
		ArcObject,
		NavigationItem,
		PsiElementWithLookup {

		override fun getFields(): List<ArcObjectFieldDeclaration> {
				return this.childrenOfType<ArcObjectFieldDeclaration>()
		}

		override fun getMember(name: String): ArcObjectFieldDeclaration? {
				return getFields().firstOrNull { it.objectFieldKey.id.text == name }
		}

		override fun getName(): String? {
				val idEl = this.findChildByType<PsiElement?>(ArcTypes.OBJECT_ID)
				return idEl?.text
		}

		override fun getPresentation(): ItemPresentation? {
				return object : ItemPresentation {
						override fun getLocationString(): String {
								return containingFile.name
						}

						override fun getIcon(unused: Boolean): javax.swing.Icon {
								return AllIcons.Nodes.Class
						}

						override fun getPresentableText(): String? {
								return name
						}
				}
		}

		override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean {
				val fields = getFields()
				for (field in fields) {
						if (!processor.execute(field, state)) {
								return false
						}
				}
				return true
		}

		override fun getMethods(): List<ArcFuncDeclaration> {
				return CachedValuesManager.getCachedValue(this) {
						val methods = ArcUtil.findMethodDeclarations(this as ArcObjectDeclaration)
						CachedValueProvider.Result.create(methods, PsiModificationTracker.MODIFICATION_COUNT)
				}
		}

		override fun getLookupElement(): LookupElement {
				return ArcObjectLookupElement(this as ArcObjectDeclaration)
		}

}

class ArcObjectLookupElement(private val el: ArcObjectDeclaration) : LookupElement() {
		override fun getLookupString(): String {
				return el.name!!
		}

		override fun renderElement(presentation: LookupElementPresentation) {
				presentation.icon = AllIcons.Nodes.Class
				presentation.itemText = el.getName()

				presentation.typeText = el.containingFile.name
				presentation.isTypeGrayed = true
		}

		/*override fun handleInsert(context: InsertionContext) {
				val doc = context.document
				context.commitDocument()
				doc.insertString(context.tailOffset, "();")
				context.editor.caretModel.moveToOffset(context.tailOffset + 1)
				PsiDocumentManager.getInstance(context.project).commitDocument(doc)

				super.handleInsert(context)
		}*/

}

class ArcObjectFieldLookupElement(private val el: ArcObjectFieldDeclaration) : LookupElement() {
		override fun getLookupString(): String {
				return el.objectFieldKey.id.text
		}

		override fun renderElement(presentation: LookupElementPresentation) {
				presentation.icon = AllIcons.Nodes.Field
				presentation.itemText = lookupString
				presentation.typeText = el.type.id.text
				if (LanguageInfo.builtinTypeNames.contains(el.type.id.text)) {
						presentation.isTypeGrayed = false
				}
		}

		/*override fun handleInsert(context: InsertionContext) {
				val doc = context.document
				context.commitDocument()
				doc.insertString(context.tailOffset, "();")
				context.editor.caretModel.moveToOffset(context.tailOffset + 1)
				PsiDocumentManager.getInstance(context.project).commitDocument(doc)

				super.handleInsert(context)
		}*/

}
