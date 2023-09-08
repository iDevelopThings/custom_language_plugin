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

public class ArcDeferStatementImpl extends ArcElementImpl implements ArcDeferStatement {

  public ArcDeferStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitDeferStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcAnonymousFunc getAnonymousFunc() {
    return findChildByClass(ArcAnonymousFunc.class);
  }

  @Override
  @Nullable
  public ArcBlockBody getBlockBody() {
    return findChildByClass(ArcBlockBody.class);
  }

  @Override
  @NotNull
  public PsiElement getDeferKw() {
    return findNotNullChildByType(DEFER_KW);
  }

}
