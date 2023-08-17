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

public class DataFusionMinusExprImpl extends DataFusionExpressionImpl implements DataFusionMinusExpr {

  public DataFusionMinusExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitMinusExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DataFusionExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DataFusionExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getMinus() {
    return findNotNullChildByType(MINUS);
  }

}
