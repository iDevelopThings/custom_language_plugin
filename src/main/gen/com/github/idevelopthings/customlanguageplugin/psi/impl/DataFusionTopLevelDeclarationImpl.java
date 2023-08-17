// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.idevelopthings.customlanguageplugin.psi.*;

public class DataFusionTopLevelDeclarationImpl extends ASTWrapperPsiElement implements DataFusionTopLevelDeclaration {

  public DataFusionTopLevelDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitTopLevelDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DataFusionFuncDeclaration getFuncDeclaration() {
    return findChildByClass(DataFusionFuncDeclaration.class);
  }

  @Override
  @NotNull
  public List<DataFusionImportStatement> getImportStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DataFusionImportStatement.class);
  }

  @Override
  @Nullable
  public DataFusionObjectDeclaration getObjectDeclaration() {
    return findChildByClass(DataFusionObjectDeclaration.class);
  }

}
