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

public class ArcObjectFieldDeclarationImpl extends ArcBaseDeclarationImpl implements ArcObjectFieldDeclaration {

  public ArcObjectFieldDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitObjectFieldDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcObjectFieldKey getObjectFieldKey() {
    return findChildByClass(ArcObjectFieldKey.class);
  }

  @Override
  @Nullable
  public ArcType getType() {
    return findChildByClass(ArcType.class);
  }

  @Override
  @Nullable
  public LookupElement getLookupElement() {
    return ArcPsiUtilImpl.getLookupElement(this);
  }

}
