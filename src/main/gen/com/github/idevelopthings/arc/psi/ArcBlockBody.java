// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcBlockBody extends PsiElement {

  @NotNull
  List<ArcStatement> getStatementList();

  @NotNull
  PsiElement getLcurly();

  @Nullable
  PsiElement getRcurly();

}
