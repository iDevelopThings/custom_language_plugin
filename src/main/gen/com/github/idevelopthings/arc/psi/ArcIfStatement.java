// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcIfStatement extends PsiElement {

  @NotNull
  ArcBlockBody getBlockBody();

  @Nullable
  ArcElseStatement getElseStatement();

  @NotNull
  ArcExpression getExpression();

  @NotNull
  PsiElement getIfKw();

  @NotNull
  PsiElement getLparen();

  @NotNull
  PsiElement getRparen();

}
