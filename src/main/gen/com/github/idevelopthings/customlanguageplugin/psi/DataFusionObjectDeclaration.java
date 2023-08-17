// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.NlsSafe;

public interface DataFusionObjectDeclaration extends DataFusionObject {

  @NotNull
  DataFusionObjectBody getObjectBody();

  @NotNull
  DataFusionObjectId getObjectId();

  @NotNull
  PsiElement getObjectKw();

  @Nullable
  @NlsSafe String getName();

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(DataFusionObjectDeclaration, ...)
  //methods are not found in DataFusionPsiImplUtil

  //WARNING: getReference(...) is skipped
  //matching getReference(DataFusionObjectDeclaration, ...)
  //methods are not found in DataFusionPsiImplUtil

  @NotNull
  List<DataFusionObjectFieldDeclaration> getFields();

}
