// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionAssignExpr extends DataFusionExpression {

  @NotNull
  List<DataFusionExpression> getExpressionList();

  @Nullable
  PsiElement getDiveq();

  @Nullable
  PsiElement getEq();

  @Nullable
  PsiElement getMinuseq();

  @Nullable
  PsiElement getMuleq();

  @Nullable
  PsiElement getPluseq();

}
