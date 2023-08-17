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

public class DataFusionValueStringImpl extends ASTWrapperPsiElement implements DataFusionValueString {

  public DataFusionValueStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DataFusionVisitor visitor) {
    visitor.visitValueString(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DataFusionVisitor) accept((DataFusionVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getBacktickString() {
    return findChildByType(BACKTICK_STRING);
  }

  @Override
  @Nullable
  public PsiElement getDoubleQuouteString() {
    return findChildByType(DOUBLE_QUOUTE_STRING);
  }

  @Override
  @Nullable
  public PsiElement getSingleQuouteString() {
    return findChildByType(SINGLE_QUOUTE_STRING);
  }

}
