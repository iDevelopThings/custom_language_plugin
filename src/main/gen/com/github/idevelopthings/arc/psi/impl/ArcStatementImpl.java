// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcBaseStatementImpl;
import com.github.idevelopthings.arc.psi.*;

public class ArcStatementImpl extends ArcBaseStatementImpl implements ArcStatement {

  public ArcStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcDeferStatement getDeferStatement() {
    return findChildByClass(ArcDeferStatement.class);
  }

  @Override
  @Nullable
  public ArcDeleteStatement getDeleteStatement() {
    return findChildByClass(ArcDeleteStatement.class);
  }

  @Override
  @Nullable
  public ArcExpression getExpression() {
    return findChildByClass(ArcExpression.class);
  }

  @Override
  @Nullable
  public ArcForLoopStatement getForLoopStatement() {
    return findChildByClass(ArcForLoopStatement.class);
  }

  @Override
  @Nullable
  public ArcHttpBodyInjection getHttpBodyInjection() {
    return findChildByClass(ArcHttpBodyInjection.class);
  }

  @Override
  @Nullable
  public ArcHttpRouteDeclaration getHttpRouteDeclaration() {
    return findChildByClass(ArcHttpRouteDeclaration.class);
  }

  @Override
  @Nullable
  public ArcIfStatement getIfStatement() {
    return findChildByClass(ArcIfStatement.class);
  }

  @Override
  @Nullable
  public ArcReturnStatement getReturnStatement() {
    return findChildByClass(ArcReturnStatement.class);
  }

  @Override
  @Nullable
  public ArcVariableDeclaration getVariableDeclaration() {
    return findChildByClass(ArcVariableDeclaration.class);
  }

  @Override
  @Nullable
  public PsiElement getComma() {
    return findChildByType(COMMA);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @Nullable
  public PsiElement getSemicolonSynthetic() {
    return findChildByType(SEMICOLON_SYNTHETIC);
  }

}
