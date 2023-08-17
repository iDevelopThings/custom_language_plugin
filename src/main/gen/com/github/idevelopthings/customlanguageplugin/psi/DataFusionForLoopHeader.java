// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionForLoopHeader extends PsiElement {

  @NotNull
  List<DataFusionExpression> getExpressionList();

  @Nullable
  PsiElement getAsKw();

  @Nullable
  PsiElement getStepKw();

}
