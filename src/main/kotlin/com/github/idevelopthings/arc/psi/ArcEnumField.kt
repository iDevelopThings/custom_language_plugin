package com.github.idevelopthings.arc.psi

import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiDocumentManager

interface ArcEnumField : ArcBaseDeclaration {
		fun isConstructorField(): Boolean

		override fun getLookupElement(): LookupElement
}

abstract class ArcEnumFieldImpl(node: ASTNode) : ArcBaseDeclarationImpl(node), ArcEnumField {

		override fun isConstructorField(): Boolean {
				return (this as ArcEnumFieldDeclaration).getEq() == null
		}

		override fun getLookupElement(): LookupElement {
				return ArcEnumValueLookupElement(this as ArcEnumFieldDeclaration)
		}
}

class ArcEnumValueLookupElement(private val el: ArcEnumFieldDeclaration) : LookupElement() {
		override fun getLookupString(): String {
				return el.name!!
		}

		override fun renderElement(presentation: LookupElementPresentation) {
				presentation.icon = AllIcons.Nodes.Enum
				presentation.itemText = lookupString
				if (!el.isConstructorField()) {
						presentation.typeText = el.expression?.text
				} else {
						var paramIdx = 0
						val argStrings = mutableListOf<String>()
						el.enumValueCtorArgList?.enumValueCtorArgList?.forEach {
								val name = it.id?.text ?: paramIdx.toString()
								val type = it.type.id.text
								argStrings.add("$name $type")
								paramIdx++
						}

						presentation.appendTailText("(${argStrings.joinToString()})", true)
				}
		}

		override fun handleInsert(context: InsertionContext) {
				if (!el.isConstructorField()) {
						super.handleInsert(context)
						return
				}

				val doc = context.document
				context.commitDocument()
				val t = context.tailOffset

				doc.insertString(t, "()")
				PsiDocumentManager.getInstance(context.project).commitDocument(doc)
				context.editor.caretModel.moveToOffset(t + 1)

				super.handleInsert(context)
		}
}
