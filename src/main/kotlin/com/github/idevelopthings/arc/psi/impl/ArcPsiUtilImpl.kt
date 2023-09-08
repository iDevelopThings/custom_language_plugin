package com.github.idevelopthings.arc.psi.impl

import com.github.idevelopthings.arc.completion.references.*
import com.github.idevelopthings.arc.language.LanguageInfo
import com.github.idevelopthings.arc.language.presentation.ArgumentDeclarationListPresentation
import com.github.idevelopthings.arc.language.typesystem.TypeLookup
import com.github.idevelopthings.arc.psi.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.RecursionManager
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.findParentOfType
import com.intellij.refactoring.suggested.startOffset

object ArcPsiUtilImpl {


		private val LOG = logger<ArcPsiUtilImpl>()


		/* @JvmStatic
		fun getOwnDeclarations(element: ArcFuncReceiverDeclaration): Collection<PsiSymbolDeclaration> {
				val result = mutableListOf<PsiSymbolDeclaration>()

				result.add(element.getFuncReceiverName()!!)

				return result

		} */

		fun getArcResolvableIdentifier(element: PsiElement): PsiElement? {
				return when (element) {
						is ArcArgumentDeclaration -> {
								element.argumentId
						}

						else -> {
								null
						}
				}
		}

		@JvmStatic
		fun getId(element: ArcTypeRef) = element.type?.id!!

		@JvmStatic
		fun getId(element: ArcType) = element.node.findChildByType(ArcTypes.ID)?.psi!!

		@JvmStatic
		fun getId(element: ArcObjectId) = element.node.findChildByType(ArcTypes.ID)?.psi!!

		@JvmStatic
		fun getLHS(element: ArcCallExpr): ArcSimpleRefExpr? {
				return PsiTreeUtil.findChildOfType(element, ArcSimpleRefExpr::class.java)
		}

		@JvmStatic
		fun processDeclarations(element: PsiElement, processor: PsiScopeProcessor, state: ResolveState, place: PsiElement): Boolean {
				when (element) {
						is ArcArgumentDeclarationList -> {
								element.argumentDeclarationList.forEach {
										if (!processor.execute(it, state)) {
												return false
										}
								}
						}

						is ArcBlockBody -> {
								element.statementList.forEach {
										if (!it.processDeclarations(processor, state, element.parent, element)) {
												return false
										}
										// if (!processor.execute(it, state)) {
										// 		return false
										// }
								}
						}

						is ArcEnumDeclaration -> {
								if (!processor.execute(element.enumId, state)) {
										return false
								}

								element.enumFieldDeclarationList.forEach {
										if (!processor.execute(it, state)) {
												return false
										}
								}
						}
				}

				return true
		}

		@JvmStatic
		fun getPresentation(el: PsiElement): ItemPresentation? {
				when (el) {
						is ArcArgumentDeclarationList -> {
								return ArgumentDeclarationListPresentation(el)
						}
				}

				return object : ItemPresentation {
						override fun getPresentableText() = el.text
						override fun getLocationString() = el.containingFile.name
						override fun getIcon(unused: Boolean) = AllIcons.Nodes.Type
				}
		}

		@JvmStatic
		fun getLookupElement(el: ArcVarId): LookupElement? {
				thisLogger().warn("getLookupElement: ArcVarId called, but was removed...")
				return null
		}

		@JvmStatic
		fun getLookupElement(el: PsiElement): LookupElement? {
				when (el) {
						is ArcObjectFieldDeclaration -> {
								return ArcObjectFieldLookupElement(el)
						}

						is ArcFuncReceiverName -> {
								return LookupElementBuilder.create(el.text).withPsiElement(el).withIcon(AllIcons.Nodes.NewParameter).withTypeText((el.parent as ArcFuncReceiverDeclaration).type.name, true)
						}

						is ArcArgumentDeclaration -> {
								return LookupElementBuilder.create(el.argumentId.text).withPsiElement(el).withIcon(AllIcons.Nodes.Parameter).withTypeText(el.type.text, true)
						}

						is ArcVariableDeclaration -> {
								val varType = TypeLookup.getTypeElement(el, true)

								return LookupElementBuilder.create(el.varId.text).withPsiElement(el).withIcon(AllIcons.Nodes.Variable).withTypeText(varType?.text ?: "Unknown variable type...", true)
						}
				}
				return null
		}

		@JvmStatic
		fun isCallExpression(element: PsiElement): Boolean {
				when (element) {
						is ArcCallExpr -> {
								return true
						}

						is ArcRefExpr -> {
								element.findParentOfType<ArcCallExpr>()?.let {
										return true
								}
						}
				}
				return false
		}

		@JvmStatic
		fun isStatic(element: PsiElement?): Boolean {
				return when (element) {

						is ArcRefExpr -> {
								if (element.expression is ArcSimpleRefExpr) return element.coloncolon != null

								return element.coloncolon != null
						}

						else -> false
				}
		}

		@JvmStatic
		fun isBuiltin(callExpr: ArcCallExpr): Boolean {
				return LanguageInfo.builtinGlobalFunctions.contains(callExpr.refExpr.text)
		}

		@JvmStatic
		fun getName(element: ASTWrapperPsiElement): String {
				return when (element) {
						is ArcObjectDeclaration -> element.objectId.text
						is ArcObjectId -> element.id.text

						is ArcFuncDeclaration -> element.funcId?.id?.text ?: "Unknown Name : Failed to get func id name"
						is ArcFuncId -> element.id.text

						is ArcVariableDeclaration -> element.varId.text
						is ArcVarId -> element.id.text

						is ArcTypeRef -> element.type?.id?.text ?: "Unknown Name : Failed to get type id name"
						is ArcType -> element.id?.text!!

						is ArcBaseExpressionElementImpl -> element.node.findChildByType(ArcTypes.ID)?.let { return it.text } ?: ""

						else -> {
								LOG.warn("getName: unknown element type: ${element.javaClass}")
								""
						}
				}
		}

		@JvmStatic
		fun getNameIdentifier(element: ASTWrapperPsiElement): PsiElement? {
				return when (element) {
						is ArcFuncReceiverName -> return element
						is ArcVarId -> return element

						is ArcTypeImpl,
						is ArcObjectIdImpl,
						is ArcBaseExpressionElementImpl,
						is ArcFuncId,
						is ArcEnumId,
						is ArcArgumentId,
						-> element.node.findChildByType(ArcTypes.ID)?.let {
								return it.psi
						}


						else -> {
								LOG.warn("getNameIdentifier: unknown element type: ${element.javaClass}")
								null
						}
				}
		}


		fun setName(element: ASTWrapperPsiElement, name: String): PsiElement {
				return when (element) {
						is ArcNamedElementImpl -> {
								val idNode = element.node.findChildByType(ArcTypes.ID)
								if (idNode != null) {
										val newIdNode = ArcElementFactory.createIdentifier(element.project, name).node
										element.node.replaceChild(idNode, newIdNode)
								}
								element
						}

						else -> {
								LOG.warn("setName: unknown element type: ${element.javaClass}")
								element
						}
				}
		}

		@JvmStatic
		fun getReference(element: ArcCallExpr): PsiReference? {
				if (element.isBuiltin) {
						return null
				}

				val result = CachedValuesManager.getCachedValue(element) {
						val id = element.refExpr.id
						val startOffset: Int = id.startOffset - element.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)

						val ref = ArcFunctionReference(element, range)
						CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
				}

				return result
		}

		@JvmStatic
		fun getReference(element: ArcVariableDeclaration): PsiReference {
				return CachedValuesManager.getCachedValue(element) {
						val id = element.varId
						val startOffset: Int = id.startOffset - element.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)

						val ref = ArcVarReference(element.varId, range)
						CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
				}
		}

		@JvmStatic
		fun getReference(element: ArcTypeRef): PsiReference? {
				return CachedValuesManager.getCachedValue(element) {
						val id = element.type?.id!!
						val startOffset: Int = id.startOffset - element.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)

						val ref = ArcTypeReference(element, range)
						CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
				}
		}

		@JvmStatic
		fun getReference(element: ArcType): PsiReference? {
				return CachedValuesManager.getCachedValue(element) {
						val id = element.node.findChildByType(ArcTypes.ID)!!
						val startOffset: Int = id.startOffset - element.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)

						val ref = ArcTypeReference(element, range)
						CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
				}
		}

		@JvmStatic
		fun getReference(element: PsiElement): PsiReference? {
				when (element) {
						is ArcImportStatement -> {
								return CachedValuesManager.getCachedValue(element) {
										val startOffset: Int = element.valueString.startOffset + 1 - element.textRange.startOffset
										val range = TextRange(startOffset, startOffset + element.valueString.textLength - 2)
										CachedValueProvider.Result.create(ArcFileReference(element, range), *arrayOf<Any>(element))
								}
						}

						is ArcSimpleRefExpr -> {
								// return SimpleTestReference(element)


								/* val startOffset: Int = element.id.startOffset - element.textRange.startOffset
								val range = TextRange(startOffset, startOffset + element.id.textLength)

								if (element.parent is ArcStatement) {
										return ArcVarReference(element, range)
								} else {
										return ArcMemberReference(element, range)
								} */



								return CachedValuesManager.getCachedValue(element) {
										val startOffset: Int = element.id.startOffset - element.textRange.startOffset
										val range = TextRange(startOffset, startOffset + element.id.textLength)

										if (element.parent is ArcStatement) {
												val ref = ArcVarReference(element, range)
												CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
										} else {
												val ref = ArcMemberReference(element, range)
												CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
										}
								}                    /* return CachedValuesManager.getCachedValue(element) {
												val startOffset: Int = element.id.startOffset - element.textRange.startOffset
												val range = TextRange(startOffset, startOffset + element.id.textLength)

												val ref = object : PsiReferenceBase<ArcSimpleRefExpr>(element, range), PsiReference {
														val items = mutableListOf<PsiElement>()

														override fun resolve(): PsiElement? {
																PsiTreeUtil.treeWalkUp(
																		{ psiElement: PsiElement, _: ResolveState ->
																				if (psiElement is ArcVariableDeclaration) {
																						if (psiElement.varId.text == element.id.text) {
																								items.add(psiElement.varId)
																								return@treeWalkUp false
																						}
																				}
																				true
																		},
																		element,
																		element.containingFile,
																		ResolveState.initial()
																)

																return items.firstOrNull()
														}

														override fun getVariants(): Array<Any> {

																val variants = mutableListOf<Any>()
																items.forEach {
																		val ref = ((it as ArcVarId).parent as ArcVariableDeclaration).type?.reference
																		if (ref != null) {
																				variants.add(ref)
																		}
																}

																return variants.toTypedArray()
														}

												}
												CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
										} */
						}

						is ArcArgumentDeclaration -> {
								return CachedValuesManager.getCachedValue(element) {
										val startOffset: Int = element.argumentId.startOffset - element.textRange.startOffset
										val range = TextRange(startOffset, startOffset + element.argumentId.textLength)
										val ref = ArcArgumentReference(element, range)

										CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
								}
						}

						is ArcRefExpr -> {
								return CachedValuesManager.getCachedValue(element) {
										val startOffset: Int = element.id.startOffset - element.textRange.startOffset
										val range = TextRange(startOffset, startOffset + element.id.textLength)
										val ref = ArcMemberReference(element, range)

										CachedValueProvider.Result.create(ref, *arrayOf<Any>(element))
								}
						}
				}


				// thisLogger().warn("getReference fallback call to: $element")
				val value = RecursionManager.doPreventingRecursion(element, true, Computable {
						CachedValuesManager.getCachedValue(element) {
								CachedValueProvider.Result.create(null, element)
						}
				})

				return value
		}


		/*@JvmStatic
		fun getReference(element: PsiElement): PsiReference? {
				val value = RecursionManager.doPreventingRecursion(element, true, Computable {
						CachedValuesManager.getCachedValue(element) {


								val reference: PsiMultiReference =
										object : PsiMultiReference(arrayOf<PsiReference>(ArcFieldNameReference(element as ArcNamedElement, null), ArcVarReference(element as ArcNamedElement, null)), element) {
												override fun resolve(): PsiElement? {
														// TODO: Handle
														return this.multiResolve(false).firstOrNull()?.element
												}

										}


								CachedValueProvider.Result.create(reference, element)
						}
				})

				return value
		}*/

		/*
		fun originalGetReference(element: PsiElement): PsiReference? {
		//						return ReferenceProvidersRegistry.getReferencesFromProviders(element)?.firstOrNull()

								if (element is ArcSimpleRefExpr) {
										if (element.isCallExpression) {
												if ((element.parent as ArcRefExpr).isStatic())
														return ReferenceProvidersRegistry
																.getReferencesFromProviders(element)
																.firstOrNull()
										}
								}

								return when (element) {

										is ArcCallExpr -> {
												element.node.findChildByType(ArcTypes.ID).let {
														if (it == null) return null

														val startOffset: Int = element.startOffset - element.textRange.startOffset
														val range = TextRange(startOffset, startOffset + element.textLength)

														return ArcFunctionReference(element, range)
												}
										}

										is ArcValueExprImpl,
										is ArcSimpleRefExpr,
										is ArcNamedElementImpl -> {
												element.node.findChildByType(ArcTypes.ID).let {
														if (it == null) {
																return null
														}

														val startOffset: Int = it.startOffset - element.textRange.startOffset
														val range = TextRange(startOffset, startOffset + it.textLength)

														return ArcReference(element, range)
												}
										}

										is ArcObjectFieldKey -> {
												val fieldRef = element as ArcObjectFieldKeyImpl

												// If our parent is an object instantiate expr then we're trying to go to the declaration of the field
												val objInstExpr = fieldRef.findParentOfType<ArcObjectInstantiateExpr>()
												if (objInstExpr != null) {
														val startOffset: Int = fieldRef.startOffset - fieldRef.parent.textRange.startOffset
														val range = TextRange(startOffset, startOffset + fieldRef.textLength)

														return ArcReference(fieldRef, range)
												}

												return null
										}

										is ArcIdReferenceExpr -> {
												val id: PsiElement? = if (!element.isMemberAccess())
														element.getIdElement()
												else
														PsiTreeUtil.findChildOfType(element, ArcSimpleRefExpr::class.java)
												val startOffset: Int = id?.startOffset!! - element.textRange.startOffset
												val range = TextRange(startOffset, startOffset + id.textLength)

												return ArcReference(element, range)
										}

										is ArcFuncDeclarationImpl -> {
												return getReference(element.funcId as ASTWrapperPsiElement)
										}

										else -> {
												LOG.warn("getReference: unknown element type: ${element.javaClass}")
												null
										}
								}
						}*/


}
