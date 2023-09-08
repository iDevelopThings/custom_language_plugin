// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface ArcBlockBody extends ArcElement {

  @NotNull
  List<ArcStatement> getStatementList();

  @NotNull
  PsiElement getLcurly();

  @Nullable
  PsiElement getRcurly();

  boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, @NotNull PsiElement place);

}
