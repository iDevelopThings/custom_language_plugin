// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ArcValueExpr extends ArcExpression, ArcBaseExpressionElement {

  @Nullable
  ArcDictionary getDictionary();

  @Nullable
  ArcList getList();

  @Nullable
  ArcValueNumber getValueNumber();

  @Nullable
  ArcValueString getValueString();

  @Nullable
  PsiElement getValueBool();

  @Nullable
  PsiElement getValueNull();

  @NotNull
  String getName();

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PsiReference getReference();

}
