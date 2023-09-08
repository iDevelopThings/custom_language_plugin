// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.idevelopthings.arc.psi.ext.ArcElement;

public interface ArcDictionary extends ArcElement {

  @Nullable
  ArcDictionaryFields getDictionaryFields();

  @NotNull
  PsiElement getLcurly();

  @NotNull
  PsiElement getRcurly();

}
