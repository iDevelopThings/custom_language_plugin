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

public class DataFusionFuncDeclarationImpl extends DataFusionFunctionImpl implements DataFusionFuncDeclaration {

  public DataFusionFuncDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitFuncDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DataFusionArgumentDeclarationList getArgumentDeclarationList() {
    return findNotNullChildByClass(DataFusionArgumentDeclarationList.class);
  }

  @Override
  @Nullable
  public DataFusionBlockBody getBlockBody() {
    return findChildByClass(DataFusionBlockBody.class);
  }

  @Override
  @NotNull
  public DataFusionFuncId getFuncId() {
    return findNotNullChildByClass(DataFusionFuncId.class);
  }

  @Override
  @Nullable
  public DataFusionFuncReceiverDeclaration getFuncReceiverDeclaration() {
    return findChildByClass(DataFusionFuncReceiverDeclaration.class);
  }

  @Override
  @NotNull
  public PsiElement getFuncKw() {
    return findNotNullChildByType(FUNC_KW);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
