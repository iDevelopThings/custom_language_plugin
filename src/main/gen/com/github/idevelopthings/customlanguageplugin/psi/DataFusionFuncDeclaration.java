// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataFusionFuncDeclaration extends DataFusionFunction {

  @NotNull
  DataFusionArgumentDeclarationList getArgumentDeclarationList();

  @Nullable
  DataFusionBlockBody getBlockBody();

  @NotNull
  DataFusionFuncId getFuncId();

  @Nullable
  DataFusionFuncReceiverDeclaration getFuncReceiverDeclaration();

  @NotNull
  PsiElement getFuncKw();

  @Nullable
  PsiElement getId();

  //WARNING: getName(...) is skipped
  //matching getName(DataFusionFuncDeclaration, ...)
  //methods are not found in DataFusionPsiImplUtil

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(DataFusionFuncDeclaration, ...)
  //methods are not found in DataFusionPsiImplUtil

  //WARNING: getReference(...) is skipped
  //matching getReference(DataFusionFuncDeclaration, ...)
  //methods are not found in DataFusionPsiImplUtil

}
