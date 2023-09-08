package com.github.idevelopthings.arc.psi

import com.intellij.codeInsight.lookup.LookupElement

interface PsiElementWithLookup {
		fun getLookupElement(): LookupElement
}
