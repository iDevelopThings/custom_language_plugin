// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;

public interface ArcObjectFieldDeclaration extends ArcBaseDeclaration, PsiElementWithLookup {

  @Nullable
  ArcObjectFieldKey getObjectFieldKey();

  @Nullable
  ArcType getType();

  @NotNull
  LookupElement getLookupElement();

}
