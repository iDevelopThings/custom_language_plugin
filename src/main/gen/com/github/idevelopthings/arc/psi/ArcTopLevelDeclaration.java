// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArcTopLevelDeclaration extends ArcBaseTopLevelDeclaration {

  @Nullable
  ArcEnumDeclaration getEnumDeclaration();

  @Nullable
  ArcExternalFuncDeclaration getExternalFuncDeclaration();

  @Nullable
  ArcFuncDeclaration getFuncDeclaration();

  @Nullable
  ArcHttpBlock getHttpBlock();

  @NotNull
  List<ArcImportStatement> getImportStatementList();

  @Nullable
  ArcObjectDeclaration getObjectDeclaration();

  @NotNull
  String getName();

}
