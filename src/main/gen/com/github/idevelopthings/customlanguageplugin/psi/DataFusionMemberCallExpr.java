// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface DataFusionMemberCallExpr extends DataFusionExpression {

  @NotNull
  List<DataFusionExpression> getExpressionList();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

  @Nullable
  PsiReference getReference();

}
