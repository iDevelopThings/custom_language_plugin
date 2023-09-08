package com.github.idevelopthings.arc.completion.providers

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.ArcUtil
import com.github.idevelopthings.arc.completion.Common
import com.github.idevelopthings.arc.completion.collector.ScopeCollector
import com.github.idevelopthings.arc.completion.references.ArcMemberReference
import com.github.idevelopthings.arc.completion.references.ArcReferenceBase
import com.github.idevelopthings.arc.completion.references.ArcResolvable
import com.github.idevelopthings.arc.completion.resolution.VarTypeResolver
import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.github.idevelopthings.arc.psi.ArcFuncReceiverName
import com.github.idevelopthings.arc.psi.ArcRefExpr
import com.github.idevelopthings.arc.psi.ArcSimpleRefExpr
import com.github.idevelopthings.arc.psi.ArcTypes
import com.github.idevelopthings.arc.psi.ArcVarId
import com.github.idevelopthings.arc.psi.ArcVariableDeclaration
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.intellij.util.ProcessingContext


class MemberCompletionProvider : CompletionProvider<CompletionParameters?>() {

		companion object {
				val COMPLETION_ITEMS_KEY = Key.create<MutableList<LookupElement>>("COMPLETION_ITEMS_KEY")
		}


		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, originalResultSet: CompletionResultSet) {
				val offset = parameters.offset
				val charSeq = parameters.editor.document.immutableCharSequence
				var currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset)

				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}


				if (
						originalResultSet.prefixMatcher.prefix.isEmpty() &&
						Common.isAfterBadIdentifier(charSeq, offset)
				) {
						return
				}

				// If the previous character is a dot, we are completing a field/method...
				if (!isMemberCompletion(currentElement, charSeq, offset)) return

				if (Common.prevIsDot(charSeq, offset)) {
						currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset - 2)
						if (currentElement?.parent !is ArcSimpleRefExpr && currentElement?.parent !is ArcRefExpr) {
								thisLogger().warn("currentElement is not ArcSimpleRefExpr(${currentElement?.text})")
								return
						}

						currentElement = currentElement.parent
				} else if (Common.prevIsColonColon(charSeq, offset)) {
						currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset - 3)
						if (currentElement?.parent !is ArcSimpleRefExpr && currentElement?.parent !is ArcRefExpr) {
								thisLogger().warn("currentElement is not ArcSimpleRefExpr(${currentElement?.text})")
								return
						}
						currentElement = currentElement.parent
				} else {
						currentElement = currentElement.prevSibling.prevSibling
				}

				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}

				thisLogger().warn("currentElement: $currentElement -> ${currentElement.text}")

				val ref = currentElement.reference
				val declaration = ref?.resolve()

				if (ref is ArcMemberReference) {
						ref.memberType?.fields?.forEach {
								originalResultSet.addElement(it.getLookupElement()!!)
						}

						ref.memberType?.functions?.forEach {
								originalResultSet.addElement(it.getLookupElement()!!)
						}

						return
				}

				if (declaration == null) {
						thisLogger().warn("declaration is null")
						return
				}

				var reference: ArcReferenceBase<*>? = null
				when (declaration) {
						is ArcVariableDeclaration -> {
								reference = TypeLookup.getTypeElement(declaration)?.reference as ArcReferenceBase<*>
						}

						is ArcFuncReceiverName -> {
								val typeElement = TypeLookup.getTypeElement(declaration)
								reference = typeElement?.reference as ArcReferenceBase<*>
						}

						is ArcVarId -> {
								reference = TypeLookup.getTypeElement(declaration.parent as ArcVariableDeclaration)?.reference as ArcReferenceBase<*>
						}
				}

				if (reference == null) {
						thisLogger().warn("reference is null")
						return
				}

				val variants = reference.variants
				if (variants.isEmpty()) return


				originalResultSet.addAllElements(variants.filterIsInstance<LookupElement>())
		}

		private fun isMemberCompletion(e: PsiElement, charSeq: CharSequence, offset: Int): Boolean {
				// We should be invoking completion at some positions like:
				// something. <-
				// something.else <-
				// but not here: something <-


				if (Common.prevIsDot(charSeq, offset) || Common.prevIsColonColon(charSeq, offset)) {
						return true
				}

				// If our current is an ID, in theory the prev should be a dot
				if (e.elementType == ArcTypes.ID) {
						// checking if the previous element is a dot
						// we have `something.xxx` at this point
						return e.prevSibling?.elementType == ArcTypes.DOT || e.prevSibling?.elementType == ArcTypes.COLONCOLON
				}

				return false
		}

}
