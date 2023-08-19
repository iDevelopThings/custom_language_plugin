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

public class ArcForLoopStatementImpl extends ASTWrapperPsiElement implements ArcForLoopStatement {

  public ArcForLoopStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitForLoopStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcBlockBody getBlockBody() {
    return findNotNullChildByClass(ArcBlockBody.class);
  }

  @Override
  @Nullable
  public ArcForLoopHeader getForLoopHeader() {
    return findChildByClass(ArcForLoopHeader.class);
  }

  @Override
  @NotNull
  public PsiElement getForKw() {
    return findNotNullChildByType(FOR_KW);
  }

}
