// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

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

  //WARNING: getName(...) is skipped
  //matching getName(ArcFuncDeclaration, ...)
  //methods are not found in ArcPsiImplUtil

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(ArcFuncDeclaration, ...)
  //methods are not found in ArcPsiImplUtil

  @Nullable
  PsiReference getReference();

  //WARNING: getPresentation(...) is skipped
  //matching getPresentation(ArcFuncDeclaration, ...)
  //methods are not found in ArcPsiImplUtil

}
