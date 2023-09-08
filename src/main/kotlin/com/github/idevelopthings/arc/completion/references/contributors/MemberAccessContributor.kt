package com.github.idevelopthings.arc.completion.references.contributors

import com.github.idevelopthings.arc.ArcPsiUtil
import com.github.idevelopthings.arc.completion.references.*
import com.github.idevelopthings.arc.psi.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference
import com.intellij.psi.util.elementType
import com.intellij.refactoring.suggested.startOffset
import com.intellij.util.ProcessingContext

class VariableReferenceContributor : PsiReferenceProvider() {

		override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {

				thisLogger().warn("nodeType: ${element.node.elementType}")

				val el = element as ArcSimpleRefExpr

				val ref = el.reference
				val r = ref?.resolve()

				if (r is ArcVariableDeclaration) {
						val id = el.id
						val startOffset: Int = id.startOffset - el.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)


						val reference: PsiMultiReference =
								object : PsiMultiReference(arrayOf<PsiReference>(PlsReference(id, range), ref), element) {
										override fun resolve(): PsiElement? {
												return this.multiResolve(false).firstOrNull()?.element
										}
								}

						return arrayOf(reference)
				}


				return arrayOf()
		}

}


class MemberAccessContributor : PsiReferenceProvider() {

		override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<out PsiReference> {
				if (ArcTokenSets.COMMENTS.contains(element.elementType))
						return arrayOf()

				thisLogger().warn("nodeType: ${element.node.elementType}")

				val results = mutableListOf<PsiReference>()
				val references = mutableListOf<PsiReference>()

				if (element is ArcRefExpr) {
						val segments = ArcPsiUtil.getMemberAccessSegments(element)
						if (segments.isEmpty()) {
								return arrayOf()
						}
						// Resolve our first segment, this will be the reference to the var
						val firstSegment = segments.first()
						val firstSegmentRef = firstSegment.reference
						segments.removeFirst()

						if (firstSegmentRef == null) {
								thisLogger().warn("firstSegmentRef is null")
								return arrayOf()
						}

						val r = firstSegmentRef.resolve()
						if (r !is ArcResolvable) {
								thisLogger().warn("r is not ArcResolvable, it's: ${r?.javaClass?.name} --- $r")
								return arrayOf()
						}

						val id = firstSegment.id
						val startOffset: Int = id.startOffset - element.textRange.startOffset
						val range = TextRange(startOffset, startOffset + id.textLength)

						val reference: PsiMultiReference =
								object : PsiMultiReference(arrayOf<PsiReference>(PlsReference(id, range), firstSegmentRef), element) {
										override fun resolve(): PsiElement? {
												return this.multiResolve(false).firstOrNull()?.element
										}
								}

						references.add(firstSegmentRef)
						results.add(reference)

						var prevReference: PsiReference? = firstSegmentRef
						while (segments.isNotEmpty() && prevReference != null) {
								val segment = segments.first()
								segments.removeFirst()

								val r = prevReference.resolve()
								if (r !is ArcResolvable) {
										thisLogger().warn("r is not ArcResolvable, it's: ${r?.javaClass?.name} --- $r")
										break
								}

								val id = segment.id
								val startOffset: Int = id.startOffset - element.textRange.startOffset
								val range = TextRange(startOffset, startOffset + id.textLength)

								var refRef: PsiElement? = null
								for (variant in prevReference.variants) {
										val v = variant as ArcTypeReference
										for (subV in v.variants) {
												if (subV is ArcObjectFieldDeclaration) {
														if (subV.objectFieldKey.id.text!! == segment.id.text) {
																refRef = subV.objectFieldKey
																break
														}
												}
										}

										if (refRef != null) {
												break
										}
								}

								if (refRef != null) {

								}

								// val reference: PsiMultiReference =
								// 		object : PsiMultiReference(arrayOf<PsiReference>(prevReference, PlsReference(id, range)), element) {
								// 				override fun resolve(): PsiElement? {
								// 						return this.multiResolve(false).firstOrNull()?.element
								// 				}
								// 		}
								// results.add(reference)
								// val reference2: PsiMultiReference =
								// 		object : PsiMultiReference(arrayOf<PsiReference>(reference, PlsReference(refRef!!, range)), element) {
								// 				override fun resolve(): PsiElement? {
								// 						return this.multiResolve(false).firstOrNull()?.element
								// 				}
								// 		}
								// results.add(reference2)


								// references.add(refRef as PsiReference)

						}

						return results.toTypedArray()
				}

				return arrayOf()
		}
}
