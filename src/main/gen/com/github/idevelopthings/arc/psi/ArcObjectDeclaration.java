// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface ArcObjectDeclaration extends ArcObject {

  @NotNull
  List<ArcObjectFieldDeclaration> getObjectFieldDeclarationList();

  @NotNull
  ArcObjectId getObjectId();

  @NotNull
  PsiElement getLcurly();

  @NotNull
  PsiElement getObjectKw();

  @NotNull
  PsiElement getRcurly();

  @Nullable
  String getName();

  @Nullable
  ItemPresentation getPresentation();

}
