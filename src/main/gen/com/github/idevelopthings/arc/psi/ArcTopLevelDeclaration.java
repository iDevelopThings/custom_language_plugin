// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcTopLevelDeclaration extends ArcDeclaration {

  @Nullable
  ArcFuncDeclaration getFuncDeclaration();

  @NotNull
  List<ArcImportStatement> getImportStatementList();

  @Nullable
  ArcObjectDeclaration getObjectDeclaration();

  @Nullable
  ArcNamedElement getNameIdentifier();

  //WARNING: getName(...) is skipped
  //matching getName(ArcTopLevelDeclaration, ...)
  //methods are not found in ArcPsiImplUtil

}
