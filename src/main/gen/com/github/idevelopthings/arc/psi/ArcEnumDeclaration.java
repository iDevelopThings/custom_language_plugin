// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface ArcEnumDeclaration extends ArcEnum {

  @NotNull
  List<ArcEnumFieldDeclaration> getEnumFieldDeclarationList();

  @NotNull
  ArcEnumId getEnumId();

  @NotNull
  PsiElement getEnumKw();

  @NotNull
  PsiElement getLcurly();

  @NotNull
  PsiElement getRcurly();

  boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @NotNull PsiElement place);

}
