// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcNamedElementImpl;
import com.github.idevelopthings.arc.psi.*;

public class ArcEnumIdImpl extends ArcNamedElementImpl implements ArcEnumId {

  public ArcEnumIdImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitEnumId(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
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

}
