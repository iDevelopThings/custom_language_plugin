// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;

public interface ArcHttpBodyInjection extends ArcElement {

  @NotNull
  ArcType getType();

  @NotNull
  ArcVarId getVarId();

  @NotNull
  PsiElement getAsKw();

  @Nullable
  PsiElement getComma();

  @NotNull
  PsiElement getHttpFromKw();

  @Nullable
  PsiElement getHttpRouteKw();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

}
