package com.github.idevelopthings.arc.psi

//
//class ArcPsiTypeReference(element: PsiElement, textRange: TextRange) :
//		PsiReferenceBase<PsiElement?>(element, textRange), PsiPolyVariantReference {
//
//		private val key: String
//
//		init {
//				key = element.text.substring(textRange.startOffset, textRange.endOffset)
//		}
//
//		override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
//				return ArcUtil.findTypeDefinitions(myElement!!.project, key)
//						.map { PsiElementResolveResult(it) }
//						.toTypedArray()
//		}
//
//		override fun resolve(): PsiElement? {
//				val resolveResults: Array<ResolveResult> = multiResolve(false)
//				return if (resolveResults.size == 1) resolveResults[0].element else null
//		}
//
//
////		override fun getVariants(): Array<out Any> {
////				return ArcUtil.findTypeDefinitions(myElement!!.project)
////						.map { LookupElementBuilder.create(it).withIcon(Icons.LogoLight).withTypeText(it.containingFile.name) }
////						.toTypedArray()
////		}
//}
