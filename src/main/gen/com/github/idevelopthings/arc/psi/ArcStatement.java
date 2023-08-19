// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcStatement extends ArcBaseStatement {

  @Nullable
  ArcExpression getExpression();

  @Nullable
  ArcForLoopStatement getForLoopStatement();

  @Nullable
  ArcIfStatement getIfStatement();

  @Nullable
  ArcReturnStatement getReturnStatement();

  @Nullable
  ArcVariableDeclaration getVariableDeclaration();

  @Nullable
  PsiElement getSemicolon();

}
