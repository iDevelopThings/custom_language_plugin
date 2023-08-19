// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcObjectInstantiateExpr extends ArcExpression {

  @NotNull
  ArcObjectInstantiateFields getObjectInstantiateFields();

  @NotNull
  ArcType getType();

  @NotNull
  PsiElement getLcurly();

  @Nullable
  PsiElement getRcurly();

}
