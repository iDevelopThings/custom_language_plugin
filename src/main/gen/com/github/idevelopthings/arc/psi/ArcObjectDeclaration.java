// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;

public interface ArcObjectDeclaration extends ArcObject {

  @NotNull
  ArcObjectBody getObjectBody();

  @NotNull
  ArcObjectId getObjectId();

  @NotNull
  PsiElement getObjectKw();

  @Nullable
  @NlsSafe String getName();

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(ArcObjectDeclaration, ...)
  //methods are not found in ArcPsiImplUtil

  @NotNull
  List<ArcObjectFieldDeclaration> getFields();

  @Nullable
  ItemPresentation getPresentation();

  @Nullable
  ArcObjectFieldDeclaration getMember(@NotNull String name);

}
