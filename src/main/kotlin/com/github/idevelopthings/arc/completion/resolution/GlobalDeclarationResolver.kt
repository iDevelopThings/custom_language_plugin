package com.github.idevelopthings.arc.completion.resolution

import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.github.idevelopthings.arc.completion.data.typeInfoFromNamedElement
import com.github.idevelopthings.arc.psi.ArcBaseDeclaration
import com.github.idevelopthings.arc.psi.ArcEnumDeclaration
import com.github.idevelopthings.arc.psi.ArcFuncDeclaration
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.intellij.psi.PsiElement

class GlobalDeclarationResolver : TypeResolverBase() {

		override fun canAccept(element: PsiElement): Boolean {
				return element is ArcEnumDeclaration ||
							 element is ArcFuncDeclaration ||
							 element is ArcObjectDeclaration
		}

		override fun resolveType(element: PsiElement): TypeInfo? {
				return typeInfoFromNamedElement(element)
		}

}
