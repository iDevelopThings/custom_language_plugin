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

public class DataFusionVariableDeclarationImpl extends ASTWrapperPsiElement implements DataFusionVariableDeclaration {

  public DataFusionVariableDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitVariableDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DataFusionExpression getExpression() {
    return findNotNullChildByClass(DataFusionExpression.class);
  }

  @Override
  @Nullable
  public DataFusionType getType() {
    return findChildByClass(DataFusionType.class);
  }

  @Override
  @NotNull
  public DataFusionVarId getVarId() {
    return findNotNullChildByClass(DataFusionVarId.class);
  }

  @Override
  @NotNull
  public PsiElement getEq() {
    return findNotNullChildByType(EQ);
  }

  @Override
  @NotNull
  public PsiElement getSemicolon() {
    return findNotNullChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public PsiElement getVarKw() {
    return findNotNullChildByType(VAR_KW);
  }

}
