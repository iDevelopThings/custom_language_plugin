// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;

public class ArcObjectDeclarationImpl extends ArcObjectImpl implements ArcObjectDeclaration {

  public ArcObjectDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitObjectDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcObjectBody getObjectBody() {
    return findNotNullChildByClass(ArcObjectBody.class);
  }

  @Override
  @NotNull
  public ArcObjectId getObjectId() {
    return findNotNullChildByClass(ArcObjectId.class);
  }

  @Override
  @NotNull
  public PsiElement getObjectKw() {
    return findNotNullChildByType(OBJECT_KW);
  }

}
