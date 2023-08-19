// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.language.presentation.ArgumentDeclarationListPresentation;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface ArcArgumentDeclarationList extends PsiElement {

  @NotNull
  List<ArcArgumentDeclaration> getArgumentDeclarationList();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

  @NotNull
  ArgumentDeclarationListPresentation getPresentation();

  boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place);

}
