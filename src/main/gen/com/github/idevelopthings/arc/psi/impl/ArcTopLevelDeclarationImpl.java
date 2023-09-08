// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import com.github.idevelopthings.arc.psi.ArcBaseTopLevelDeclarationImpl;
import com.github.idevelopthings.arc.psi.*;

public class ArcTopLevelDeclarationImpl extends ArcBaseTopLevelDeclarationImpl implements ArcTopLevelDeclaration {

  public ArcTopLevelDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ArcVisitor visitor) {
    visitor.visitTopLevelDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ArcVisitor) accept((ArcVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ArcEnumDeclaration getEnumDeclaration() {
    return findChildByClass(ArcEnumDeclaration.class);
  }

  @Override
  @Nullable
  public ArcExternalFuncDeclaration getExternalFuncDeclaration() {
    return findChildByClass(ArcExternalFuncDeclaration.class);
  }

  @Override
  @Nullable
  public ArcFuncDeclaration getFuncDeclaration() {
    return findChildByClass(ArcFuncDeclaration.class);
  }

  @Override
  @Nullable
  public ArcHttpBlock getHttpBlock() {
    return findChildByClass(ArcHttpBlock.class);
  }

  @Override
  @NotNull
  public List<ArcImportStatement> getImportStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ArcImportStatement.class);
  }

  @Override
  @Nullable
  public ArcObjectDeclaration getObjectDeclaration() {
    return findChildByClass(ArcObjectDeclaration.class);
  }

  @Override
  @NotNull
  public String getName() {
    return ArcPsiUtilImpl.getName(this);
  }

}
