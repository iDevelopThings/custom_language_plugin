// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcCallExpr extends ArcExpression {

  @NotNull
  ArcArgList getArgList();

  @NotNull
  ArcRefExpr getRefExpr();

  boolean isBuiltin();

  boolean isStatic();

  @Nullable
  ArcSimpleRefExpr getLHS();

  @Nullable
  PsiReference getReference();

}
