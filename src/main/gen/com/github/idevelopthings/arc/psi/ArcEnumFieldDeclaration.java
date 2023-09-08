// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcEnumFieldDeclaration extends ArcEnumField {

  @Nullable
  ArcEnumValueCtorArgList getEnumValueCtorArgList();

  @Nullable
  ArcExpression getExpression();

  @Nullable
  PsiElement getComma();

  @Nullable
  PsiElement getEq();

  @NotNull
  PsiElement getId();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

}
