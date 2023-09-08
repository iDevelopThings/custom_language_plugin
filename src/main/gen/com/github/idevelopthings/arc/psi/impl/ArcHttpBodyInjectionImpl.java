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

public class ArcHttpBodyInjectionImpl extends ArcElementImpl implements ArcHttpBodyInjection {

  public ArcHttpBodyInjectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitHttpBodyInjection(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcType getType() {
    return findNotNullChildByClass(ArcType.class);
  }

  @Override
  @NotNull
  public ArcVarId getVarId() {
    return findNotNullChildByClass(ArcVarId.class);
  }

  @Override
  @NotNull
  public PsiElement getAsKw() {
    return findNotNullChildByType(AS_KW);
  }

  @Override
  @Nullable
  public PsiElement getComma() {
    return findChildByType(COMMA);
  }

  @Override
  @NotNull
  public PsiElement getHttpFromKw() {
    return findNotNullChildByType(HTTP_FROM_KW);
  }

  @Override
  @Nullable
  public PsiElement getHttpRouteKw() {
    return findChildByType(HTTP_ROUTE_KW);
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

}
