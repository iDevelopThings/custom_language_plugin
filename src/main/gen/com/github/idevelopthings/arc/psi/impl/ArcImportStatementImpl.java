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

public class ArcImportStatementImpl extends ASTWrapperPsiElement implements ArcImportStatement {

  public ArcImportStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitImportStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ArcValueString getValueString() {
    return findNotNullChildByClass(ArcValueString.class);
  }

  @Override
  @NotNull
  public PsiElement getImportKw() {
    return findNotNullChildByType(IMPORT_KW);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
