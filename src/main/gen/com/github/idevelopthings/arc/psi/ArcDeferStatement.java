// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;

public interface ArcDeferStatement extends ArcElement {

  @Nullable
  ArcAnonymousFunc getAnonymousFunc();

  @Nullable
  ArcBlockBody getBlockBody();

  @NotNull
  PsiElement getDeferKw();

}
