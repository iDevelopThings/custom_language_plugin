// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.completion.references.ArcBaseResolvable;
import com.github.idevelopthings.arc.psi.*;

public class ArcArgumentDeclarationImpl extends ArcBaseResolvable implements ArcArgumentDeclaration {

  public ArcArgumentDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitArgumentDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcArgumentId getArgumentId() {
    return findNotNullChildByClass(ArcArgumentId.class);
  }

  @Override
  @NotNull
  public ArcType getType() {
    return findNotNullChildByClass(ArcType.class);
  }

  @Override
  @Nullable
  public PsiElement getDotdotdot() {
    return findChildByType(DOTDOTDOT);
  }

}
