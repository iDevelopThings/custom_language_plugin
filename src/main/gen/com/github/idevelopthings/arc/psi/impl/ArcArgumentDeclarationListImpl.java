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
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class ArcArgumentDeclarationListImpl extends ArcElementImpl implements ArcArgumentDeclarationList {

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
  @Nullable
  public ItemPresentation getPresentation() {
    return ArcPsiUtilImpl.getPresentation(this);
  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @NotNull PsiElement place) {
    return ArcPsiUtilImpl.processDeclarations(this, processor, state, place);
  }

}
