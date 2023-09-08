// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface ArcFuncDeclaration extends ArcFunction {

  @Nullable
  ArcArgumentDeclarationList getArgumentDeclarationList();

  @Nullable
  ArcBlockBody getBlockBody();

  @Nullable
  ArcFuncId getFuncId();

  @Nullable
  ArcFuncReceiverDeclaration getFuncReceiverDeclaration();

  @Nullable
  ArcType getType();

  @NotNull
  PsiElement getFuncKw();

  @NotNull
  String getName();

  @Nullable
  ItemPresentation getPresentation();

}
