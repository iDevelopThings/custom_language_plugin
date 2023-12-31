// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcBaseDeclarationImpl;
import com.github.idevelopthings.arc.psi.*;
import com.intellij.codeInsight.lookup.LookupElement;

public class ArcVariableDeclarationImpl extends ArcBaseDeclarationImpl implements ArcVariableDeclaration {

  public ArcVariableDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitVariableDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcExpression getExpression() {
    return findChildByClass(ArcExpression.class);
  }

  @Override
  @Nullable
  public ArcType getType() {
    return findChildByClass(ArcType.class);
  }

  @Override
  @NotNull
  public ArcVarId getVarId() {
    return findNotNullChildByClass(ArcVarId.class);
  }

  @Override
  @Nullable
  public PsiElement getComma() {
    return findChildByType(COMMA);
  }

  @Override
  @Nullable
  public PsiElement getEq() {
    return findChildByType(EQ);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @Nullable
  public PsiElement getSemicolonSynthetic() {
    return findChildByType(SEMICOLON_SYNTHETIC);
  }

  @Override
  @NotNull
  public PsiElement getVarKw() {
    return findNotNullChildByType(VAR_KW);
  }

  @Override
  @Nullable
  public LookupElement getLookupElement() {
    return ArcPsiUtilImpl.getLookupElement(this);
  }

}
