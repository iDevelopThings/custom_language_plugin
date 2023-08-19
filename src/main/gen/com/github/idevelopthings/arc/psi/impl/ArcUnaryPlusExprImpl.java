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

public class ArcUnaryPlusExprImpl extends ArcExpressionImpl implements ArcUnaryPlusExpr {

  public ArcUnaryPlusExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitUnaryPlusExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcExpression getExpression() {
    return findChildByClass(ArcExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getPlus() {
    return findNotNullChildByType(PLUS);
  }

}
