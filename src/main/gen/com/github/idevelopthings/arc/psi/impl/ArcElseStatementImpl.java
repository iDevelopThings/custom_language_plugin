// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ext.ArcElementImpl;
import com.github.idevelopthings.arc.psi.*;

public class ArcElseStatementImpl extends ArcElementImpl implements ArcElseStatement {

  public ArcElseStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitElseStatement(this);
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
  @Nullable
  public ArcIfStatement getIfStatement() {
    return findChildByClass(ArcIfStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getElseKw() {
    return findNotNullChildByType(ELSE_KW);
  }

}
