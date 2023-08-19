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

public class ArcMemberCallExprImpl extends ArcMemberCallExpressionMixinImpl implements ArcMemberCallExpr {

  public ArcMemberCallExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitMemberCallExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ArcExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getLparen() {
    return findNotNullChildByType(LPAREN);
  }

  @Override
  @NotNull
  public PsiElement getRparen() {
    return findNotNullChildByType(RPAREN);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ArcPsiImplUtil.getReference(this);
  }

}
