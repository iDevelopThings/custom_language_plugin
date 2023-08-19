// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcCallExpr extends ArcExpression {

  @Nullable
  ArcExpression getExpression();

  @NotNull
  PsiElement getId();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
