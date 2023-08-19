// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcType extends ArcNamedElement {

  @NotNull
  PsiElement getId();

  @Nullable
  PsiElement getLbrack();

  @Nullable
  PsiElement getRbrack();

  //WARNING: getName(...) is skipped
  //matching getName(ArcType, ...)
  //methods are not found in ArcPsiImplUtil

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(ArcType, ...)
  //methods are not found in ArcPsiImplUtil

  @Nullable
  PsiReference getReference();

}
