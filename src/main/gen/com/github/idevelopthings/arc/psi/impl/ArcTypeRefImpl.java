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

public class ArcTypeRefImpl extends ArcTypeImpl implements ArcTypeRef {

  public ArcTypeRefImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitTypeRef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcType getType() {
    return findChildByClass(ArcType.class);
  }

  @Override
  @Nullable
  public PsiElement getBracketPair() {
    return findChildByType(BRACKET_PAIR);
  }

  @Override
  @Nullable
  public PsiElement getNot() {
    return findChildByType(NOT);
  }

  @Override
  @Nullable
  public PsiElement getQuestion() {
    return findChildByType(QUESTION);
  }

  @Override
  @NotNull
  public String getName() {
    return ArcPsiUtilImpl.getName(this);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return ArcPsiUtilImpl.getNameIdentifier(this);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ArcPsiUtilImpl.getReference(this);
  }

}
