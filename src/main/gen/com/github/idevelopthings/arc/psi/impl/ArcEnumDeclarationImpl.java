// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcEnumImpl;
import com.github.idevelopthings.arc.psi.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class ArcEnumDeclarationImpl extends ArcEnumImpl implements ArcEnumDeclaration {

  public ArcEnumDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitEnumDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ArcEnumFieldDeclaration> getEnumFieldDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcEnumFieldDeclaration.class);
  }

  @Override
  @NotNull
  public ArcEnumId getEnumId() {
    return findNotNullChildByClass(ArcEnumId.class);
  }

  @Override
  @NotNull
  public PsiElement getEnumKw() {
    return findNotNullChildByType(ENUM_KW);
  }

  @Override
  @NotNull
  public PsiElement getLcurly() {
    return findNotNullChildByType(LCURLY);
  }

  @Override
  @NotNull
  public PsiElement getRcurly() {
    return findNotNullChildByType(RCURLY);
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @NotNull PsiElement place) {
    return ArcPsiUtilImpl.processDeclarations(this, processor, state, place);
  }

}
