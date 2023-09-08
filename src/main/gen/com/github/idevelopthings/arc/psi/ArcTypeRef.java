// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcTypeRef extends ArcType {

  @Nullable
  ArcType getType();

  @Nullable
  PsiElement getBracketPair();

  @Nullable
  PsiElement getNot();

  @Nullable
  PsiElement getQuestion();

  @NotNull
  String getName();

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PsiReference getReference();

}
