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

public class DataFusionReturnStatementImpl extends ASTWrapperPsiElement implements DataFusionReturnStatement {

  public DataFusionReturnStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitReturnStatement(this);
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
  @NotNull
  public PsiElement getReturnKw() {
    return findNotNullChildByType(RETURN_KW);
  }

  @Override
  @NotNull
  public PsiElement getSemicolon() {
    return findNotNullChildByType(SEMICOLON);
  }

}
