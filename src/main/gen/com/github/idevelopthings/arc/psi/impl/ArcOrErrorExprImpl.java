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

public class ArcOrErrorExprImpl extends ArcExpressionImpl implements ArcOrErrorExpr {

  public ArcOrErrorExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitOrErrorExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcBlockBody getBlockBody() {
    return findChildByClass(ArcBlockBody.class);
  }

  @Override
  @NotNull
  public List<ArcExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getOrKw() {
    return findNotNullChildByType(OR_KW);
  }

}
