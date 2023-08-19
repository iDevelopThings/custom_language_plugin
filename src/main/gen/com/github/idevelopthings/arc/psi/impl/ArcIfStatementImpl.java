// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.idevelopthings.arc.psi.*;

public class ArcIfStatementImpl extends ASTWrapperPsiElement implements ArcIfStatement {

  public ArcIfStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitIfStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcBlockBody getBlockBody() {
    return findNotNullChildByClass(ArcBlockBody.class);
  }

  @Override
  @Nullable
  public ArcElseStatement getElseStatement() {
    return findChildByClass(ArcElseStatement.class);
  }

  @Override
  @NotNull
  public ArcExpression getExpression() {
    return findNotNullChildByClass(ArcExpression.class);
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
