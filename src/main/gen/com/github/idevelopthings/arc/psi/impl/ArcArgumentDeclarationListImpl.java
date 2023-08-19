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
import com.github.idevelopthings.arc.language.presentation.ArgumentDeclarationListPresentation;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class ArcArgumentDeclarationListImpl extends ASTWrapperPsiElement implements ArcArgumentDeclarationList {

  public ArcArgumentDeclarationListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitArgumentDeclarationList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ArcArgumentDeclaration> getArgumentDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcArgumentDeclaration.class);
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

  @Override
  @NotNull
  public ArgumentDeclarationListPresentation getPresentation() {
    return ArcPsiImplUtil.getPresentation(this);
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @Nullable PsiElement lastParent, @NotNull PsiElement place) {
    return ArcPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
