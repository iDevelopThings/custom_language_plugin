// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcStatement extends ArcBaseStatement {

  @Nullable
  ArcDeferStatement getDeferStatement();

  @Nullable
  ArcDeleteStatement getDeleteStatement();

  @Nullable
  ArcExpression getExpression();

  @Nullable
  ArcForLoopStatement getForLoopStatement();

  @Nullable
  ArcHttpBodyInjection getHttpBodyInjection();

  @Nullable
  ArcHttpRouteDeclaration getHttpRouteDeclaration();

  @Nullable
  ArcIfStatement getIfStatement();

  @Nullable
  ArcReturnStatement getReturnStatement();

  @Nullable
  ArcVariableDeclaration getVariableDeclaration();

  @Nullable
  PsiElement getComma();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getSemicolonSynthetic();

}
