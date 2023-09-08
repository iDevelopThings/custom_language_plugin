// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.*;
import com.intellij.psi.PsiReference;

public class ArcCallExprImpl extends ArcExpressionImpl implements ArcCallExpr {

  public ArcCallExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitCallExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcArgList getArgList() {
    return findNotNullChildByClass(ArcArgList.class);
  }

  @Override
  @NotNull
  public ArcRefExpr getRefExpr() {
    return findNotNullChildByClass(ArcRefExpr.class);
  }

  @Override
  public boolean isBuiltin() {
    return ArcPsiUtilImpl.isBuiltin(this);
  }

  @Override
  public boolean isStatic() {
    return ArcPsiUtilImpl.isStatic(this);
  }

  @Override
  @Nullable
  public ArcSimpleRefExpr getLHS() {
    return ArcPsiUtilImpl.getLHS(this);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ArcPsiUtilImpl.getReference(this);
  }

}
