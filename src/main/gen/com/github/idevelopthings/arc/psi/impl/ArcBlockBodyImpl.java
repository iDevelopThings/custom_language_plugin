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

public class ArcBlockBodyImpl extends ASTWrapperPsiElement implements ArcBlockBody {

  public ArcBlockBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitBlockBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ArcStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getLcurly() {
    return findNotNullChildByType(LCURLY);
  }

  @Override
  @Nullable
  public PsiElement getRcurly() {
    return findChildByType(RCURLY);
  }

}
