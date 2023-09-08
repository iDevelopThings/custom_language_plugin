package com.github.idevelopthings.arc.completion.resolution

import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.intellij.psi.PsiElement

interface TypeResolver {
		fun resolveType(element: PsiElement): TypeInfo?

		fun canAccept(element: PsiElement): Boolean
}

abstract class TypeResolverBase : TypeResolver {

		override fun canAccept(element: PsiElement): Boolean {
				return true
		}

		override fun resolveType(element: PsiElement): TypeInfo? {
				return null
		}
}
