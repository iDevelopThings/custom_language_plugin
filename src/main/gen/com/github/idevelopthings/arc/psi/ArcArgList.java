// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;

public interface ArcArgList extends ArcElement {

  @NotNull
  List<ArcExpression> getExpressionList();

  @NotNull
  PsiElement getLparen();

  @Nullable
  PsiElement getRparen();

}
