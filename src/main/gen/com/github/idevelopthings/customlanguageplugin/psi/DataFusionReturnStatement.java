// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionReturnStatement extends PsiElement {

  @Nullable
  DataFusionExpression getExpression();

  @NotNull
  PsiElement getReturnKw();

  @NotNull
  PsiElement getSemicolon();

}
