// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.completion.references.ArcResolvable;

public interface ArcArgumentDeclaration extends ArcResolvable {

  @NotNull
  ArcArgumentId getArgumentId();

  @NotNull
  ArcType getType();

  @Nullable
  PsiElement getDotdotdot();

}
