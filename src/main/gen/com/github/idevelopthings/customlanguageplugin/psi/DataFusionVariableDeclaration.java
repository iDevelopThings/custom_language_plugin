// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionVariableDeclaration extends PsiElement {

  @NotNull
  DataFusionExpression getExpression();

  @Nullable
  DataFusionType getType();

  @NotNull
  DataFusionVarId getVarId();

  @NotNull
  PsiElement getEq();

  @NotNull
  PsiElement getSemicolon();

  @NotNull
  PsiElement getVarKw();

}
