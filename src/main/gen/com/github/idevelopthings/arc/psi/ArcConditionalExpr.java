// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcConditionalExpr extends ArcExpression {

  @NotNull
  List<ArcExpression> getExpressionList();

  @Nullable
  PsiElement getAnd();

  @Nullable
  PsiElement getEqeq();

  @Nullable
  PsiElement getGe();

  @Nullable
  PsiElement getLangle();

  @Nullable
  PsiElement getLe();

  @Nullable
  PsiElement getNe();

  @Nullable
  PsiElement getOr();

  @Nullable
  PsiElement getRangle();

}
