// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;

public interface ArcReturnStatement extends ArcElement {

  @Nullable
  ArcExpression getExpression();

  @Nullable
  PsiElement getComma();

  @NotNull
  PsiElement getReturnKw();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

}
