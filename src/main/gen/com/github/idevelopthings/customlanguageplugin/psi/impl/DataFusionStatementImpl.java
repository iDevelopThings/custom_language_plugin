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

public class DataFusionStatementImpl extends DataFusionBaseStatementImpl implements DataFusionStatement {

  public DataFusionStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DataFusionExpression getExpression() {
    return findChildByClass(DataFusionExpression.class);
  }

  @Override
  @Nullable
  public DataFusionForLoopStatement getForLoopStatement() {
    return findChildByClass(DataFusionForLoopStatement.class);
  }

  @Override
  @Nullable
  public DataFusionIfStatement getIfStatement() {
    return findChildByClass(DataFusionIfStatement.class);
  }

  @Override
  @Nullable
  public DataFusionReturnStatement getReturnStatement() {
    return findChildByClass(DataFusionReturnStatement.class);
  }

  @Override
  @Nullable
  public DataFusionVariableDeclaration getVariableDeclaration() {
    return findChildByClass(DataFusionVariableDeclaration.class);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
