// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionIfStatement extends PsiElement {

  @NotNull
  DataFusionBlockBody getBlockBody();

  @Nullable
  DataFusionElseStatement getElseStatement();

  @NotNull
  DataFusionExpression getExpression();

  @NotNull
  PsiElement getIfKw();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
