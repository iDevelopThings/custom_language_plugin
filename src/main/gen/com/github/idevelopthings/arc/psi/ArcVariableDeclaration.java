// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcVariableDeclaration extends PsiElement {

  @NotNull
  ArcExpression getExpression();

  @Nullable
  ArcType getType();

  @NotNull
  ArcVarId getVarId();

  @NotNull
  PsiElement getEq();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getVarKw();

}
