// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcForLoopHeader extends PsiElement {

  @NotNull
  List<ArcExpression> getExpressionList();

  @Nullable
  PsiElement getAsKw();

  @Nullable
  PsiElement getStepKw();

}
