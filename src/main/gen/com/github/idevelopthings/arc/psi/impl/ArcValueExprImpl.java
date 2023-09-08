// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcBaseExpressionElementImpl;
import com.github.idevelopthings.arc.psi.*;
import com.intellij.psi.PsiReference;

public class ArcValueExprImpl extends ArcBaseExpressionElementImpl implements ArcValueExpr {

  public ArcValueExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitValueExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcDictionary getDictionary() {
    return findChildByClass(ArcDictionary.class);
  }

  @Override
  @Nullable
  public ArcList getList() {
    return findChildByClass(ArcList.class);
  }

  @Override
  @Nullable
  public ArcValueNumber getValueNumber() {
    return findChildByClass(ArcValueNumber.class);
  }

  @Override
  @Nullable
  public ArcValueString getValueString() {
    return findChildByClass(ArcValueString.class);
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

  @Override
  @NotNull
  public String getName() {
    return ArcPsiUtilImpl.getName(this);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return ArcPsiUtilImpl.getNameIdentifier(this);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ArcPsiUtilImpl.getReference(this);
  }

}
