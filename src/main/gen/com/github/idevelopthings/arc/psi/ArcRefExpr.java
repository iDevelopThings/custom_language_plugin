// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcRefExpr extends ArcExpression, ArcIdReferenceExpr {

  @Nullable
  ArcExpression getExpression();

  @Nullable
  PsiElement getColoncolon();

  @Nullable
  PsiElement getDot();

  @NotNull
  PsiElement getId();

  boolean isStatic();

  boolean isCallExpression();

  @Nullable
  PsiReference getReference();

}
