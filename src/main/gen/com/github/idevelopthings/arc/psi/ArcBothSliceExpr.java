// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcBothSliceExpr extends ArcExpression {

  @NotNull
  List<ArcExpression> getExpressionList();

  @NotNull
  PsiElement getColon();

  @NotNull
  PsiElement getLbrack();

  @NotNull
  PsiElement getRbrack();

}
