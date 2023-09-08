// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;
import com.intellij.psi.PsiReference;

public interface ArcImportStatement extends ArcElement {

  @NotNull
  ArcValueString getValueString();

  @Nullable
  PsiElement getComma();

  @NotNull
  PsiElement getImportKw();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

  @Nullable
  PsiReference getReference();

}
