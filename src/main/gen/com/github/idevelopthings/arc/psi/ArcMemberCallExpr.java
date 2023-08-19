// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcMemberCallExpr extends ArcExpression {

  @NotNull
  List<ArcExpression> getExpressionList();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

  @Nullable
  PsiReference getReference();

}
