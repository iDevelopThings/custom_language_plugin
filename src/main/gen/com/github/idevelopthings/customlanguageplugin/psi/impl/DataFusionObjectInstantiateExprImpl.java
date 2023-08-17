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

public class DataFusionObjectInstantiateExprImpl extends DataFusionExpressionImpl implements DataFusionObjectInstantiateExpr {

  public DataFusionObjectInstantiateExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitObjectInstantiateExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DataFusionObjectInstantiateFields getObjectInstantiateFields() {
    return findNotNullChildByClass(DataFusionObjectInstantiateFields.class);
  }

  @Override
  @NotNull
  public DataFusionType getType() {
    return findNotNullChildByClass(DataFusionType.class);
  }

  @Override
  @NotNull
  public PsiElement getLcurly() {
    return findNotNullChildByType(LCURLY);
  }

  @Override
  @Nullable
  public PsiElement getRcurly() {
    return findChildByType(RCURLY);
  }

}
