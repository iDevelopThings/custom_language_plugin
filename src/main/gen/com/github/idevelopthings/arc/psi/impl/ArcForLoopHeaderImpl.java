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

public class ArcForLoopHeaderImpl extends ASTWrapperPsiElement implements ArcForLoopHeader {

  public ArcForLoopHeaderImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitForLoopHeader(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ArcExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getAsKw() {
    return findChildByType(AS_KW);
  }

  @Override
  @Nullable
  public PsiElement getStepKw() {
    return findChildByType(STEP_KW);
  }

}
