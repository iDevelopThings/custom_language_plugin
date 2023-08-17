// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes.*;
import com.github.idevelopthings.customlanguageplugin.psi.*;
import com.intellij.psi.PsiReference;

public class DataFusionValueExprImpl extends DataFusionBaseExpressionElementImpl implements DataFusionValueExpr {

  public DataFusionValueExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitValueExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DataFusionDictionary getDictionary() {
    return findChildByClass(DataFusionDictionary.class);
  }

  @Override
  @Nullable
  public DataFusionList getList() {
    return findChildByClass(DataFusionList.class);
  }

  @Override
  @Nullable
  public DataFusionValueNumber getValueNumber() {
    return findChildByClass(DataFusionValueNumber.class);
  }

  @Override
  @Nullable
  public DataFusionValueString getValueString() {
    return findChildByClass(DataFusionValueString.class);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getValueBool() {
    return findChildByType(VALUE_BOOL);
  }

  @Override
  @Nullable
  public PsiElement getValueNull() {
    return findChildByType(VALUE_NULL);
  }

}
