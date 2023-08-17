// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.idevelopthings.customlanguageplugin.psi.*;

public class DataFusionIfStatementImpl extends ASTWrapperPsiElement implements DataFusionIfStatement {

  public DataFusionIfStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitIfStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DataFusionBlockBody getBlockBody() {
    return findNotNullChildByClass(DataFusionBlockBody.class);
  }

  @Override
  @Nullable
  public DataFusionElseStatement getElseStatement() {
    return findChildByClass(DataFusionElseStatement.class);
  }

  @Override
  @NotNull
  public DataFusionExpression getExpression() {
    return findNotNullChildByClass(DataFusionExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getIfKw() {
    return findNotNullChildByType(IF_KW);
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

}
