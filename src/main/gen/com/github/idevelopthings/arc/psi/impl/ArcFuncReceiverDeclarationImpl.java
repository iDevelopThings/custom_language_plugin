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

public class ArcFuncReceiverDeclarationImpl extends ASTWrapperPsiElement implements ArcFuncReceiverDeclaration {

  public ArcFuncReceiverDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitFuncReceiverDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcFuncReceiverName getFuncReceiverName() {
    return findNotNullChildByClass(ArcFuncReceiverName.class);
  }

  @Override
  @NotNull
  public ArcType getType() {
    return findNotNullChildByClass(ArcType.class);
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
