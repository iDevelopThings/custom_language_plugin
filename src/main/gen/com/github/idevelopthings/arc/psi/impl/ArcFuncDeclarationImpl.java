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
import com.intellij.psi.PsiReference;

public class ArcFuncDeclarationImpl extends ArcFunctionImpl implements ArcFuncDeclaration {

  public ArcFuncDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitFuncDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcArgumentDeclarationList getArgumentDeclarationList() {
    return findChildByClass(ArcArgumentDeclarationList.class);
  }

  @Override
  @Nullable
  public ArcBlockBody getBlockBody() {
    return findChildByClass(ArcBlockBody.class);
  }

  @Override
  @Nullable
  public ArcFuncId getFuncId() {
    return findChildByClass(ArcFuncId.class);
  }

  @Override
  @Nullable
  public ArcFuncReceiverDeclaration getFuncReceiverDeclaration() {
    return findChildByClass(ArcFuncReceiverDeclaration.class);
  }

  @Override
  @Nullable
  public ArcType getType() {
    return findChildByClass(ArcType.class);
  }

  @Override
  @NotNull
  public PsiElement getFuncKw() {
    return findNotNullChildByType(FUNC_KW);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ArcPsiImplUtil.getReference(this);
  }

}
