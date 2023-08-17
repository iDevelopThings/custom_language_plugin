// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface DataFusionValueExpr extends DataFusionExpression, DataFusionBaseExpressionElement {

  @Nullable
  DataFusionDictionary getDictionary();

  @Nullable
  DataFusionList getList();

  @Nullable
  DataFusionValueNumber getValueNumber();

  @Nullable
  DataFusionValueString getValueString();

  @Nullable
  PsiElement getId();

  @Nullable
  PsiElement getValueBool();

  @Nullable
  PsiElement getValueNull();

  //WARNING: getName(...) is skipped
  //matching getName(DataFusionValueExpr, ...)
  //methods are not found in DataFusionPsiImplUtil

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PsiReference getReference();

}
