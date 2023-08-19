// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcAssignExpr extends ArcExpression {

  @NotNull
  List<ArcExpression> getExpressionList();

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
