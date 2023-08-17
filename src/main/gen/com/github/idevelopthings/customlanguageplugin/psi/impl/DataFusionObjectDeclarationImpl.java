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
import com.intellij.openapi.util.NlsSafe;

public class DataFusionObjectDeclarationImpl extends DataFusionObjectImpl implements DataFusionObjectDeclaration {

  public DataFusionObjectDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitObjectDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DataFusionObjectBody getObjectBody() {
    return findNotNullChildByClass(DataFusionObjectBody.class);
  }

  @Override
  @NotNull
  public DataFusionObjectId getObjectId() {
    return findNotNullChildByClass(DataFusionObjectId.class);
  }

  @Override
  @NotNull
  public PsiElement getObjectKw() {
    return findNotNullChildByType(OBJECT_KW);
  }

}
