// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionObjectInstantiateExpr extends DataFusionExpression {

  @NotNull
  DataFusionObjectInstantiateFields getObjectInstantiateFields();

  @NotNull
  DataFusionType getType();

  @NotNull
  PsiElement getLcurly();

  @Nullable
  PsiElement getRcurly();

}
