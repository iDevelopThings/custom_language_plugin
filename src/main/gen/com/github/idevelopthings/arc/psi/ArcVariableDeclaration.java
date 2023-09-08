// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;

public interface ArcVariableDeclaration extends ArcBaseDeclaration {

  @Nullable
  ArcExpression getExpression();

  @Nullable
  ArcType getType();

  @NotNull
  ArcVarId getVarId();

  @Nullable
  PsiElement getComma();

  @Nullable
  PsiElement getEq();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

  @NotNull
  PsiElement getVarKw();

  @Nullable
  LookupElement getLookupElement();

}
