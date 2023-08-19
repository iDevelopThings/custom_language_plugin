// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcDictionaryField extends PsiElement {

  @NotNull
  ArcExpression getExpression();

  @Nullable
  ArcValueString getValueString();

  @NotNull
  PsiElement getColon();

  @Nullable
  PsiElement getId();

}
