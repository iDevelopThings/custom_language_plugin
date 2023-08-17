// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface DataFusionMemberAccessExpr extends DataFusionExpression {

  @NotNull
  DataFusionExpression getExpression();

  @Nullable
  PsiElement getColoncolon();

  @Nullable
  PsiElement getDot();

  @Nullable
  PsiElement getId();

  @Nullable
  PsiReference getReference();

  boolean isCallExpression();

  @Nullable
  PsiElement getRHS();

  @Nullable
  DataFusionValueExpr getLHS();

}
