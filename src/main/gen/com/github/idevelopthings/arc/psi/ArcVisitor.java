// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.github.idevelopthings.arc.psi.ext.ArcElement;
import com.github.idevelopthings.arc.completion.references.ArcResolvable;
import com.intellij.psi.PsiElement;

public class ArcVisitor extends PsiElementVisitor {

  public void visitAnonymousFunc(@NotNull ArcAnonymousFunc o) {
    visitFunction(o);
  }

  public void visitArgList(@NotNull ArcArgList o) {
    visitElement(o);
  }

  public void visitArgumentDeclaration(@NotNull ArcArgumentDeclaration o) {
    visitResolvable(o);
  }

  public void visitArgumentDeclarationList(@NotNull ArcArgumentDeclarationList o) {
    visitElement(o);
  }

  public void visitArgumentId(@NotNull ArcArgumentId o) {
    visitNamedElement(o);
  }

  public void visitArrayAccessExpr(@NotNull ArcArrayAccessExpr o) {
    visitExpression(o);
  }

  public void visitAssignExpr(@NotNull ArcAssignExpr o) {
    visitExpression(o);
  }

  public void visitBlockBody(@NotNull ArcBlockBody o) {
    visitElement(o);
  }

  public void visitBothSliceExpr(@NotNull ArcBothSliceExpr o) {
    visitExpression(o);
  }

  public void visitCallExpr(@NotNull ArcCallExpr o) {
    visitExpression(o);
  }

  public void visitConditionalExpr(@NotNull ArcConditionalExpr o) {
    visitExpression(o);
  }

  public void visitDeferStatement(@NotNull ArcDeferStatement o) {
    visitElement(o);
  }

  public void visitDeleteStatement(@NotNull ArcDeleteStatement o) {
    visitElement(o);
  }

  public void visitDictionary(@NotNull ArcDictionary o) {
    visitElement(o);
  }

  public void visitDictionaryField(@NotNull ArcDictionaryField o) {
    visitElement(o);
  }

  public void visitDictionaryFields(@NotNull ArcDictionaryFields o) {
    visitElement(o);
  }

  public void visitDivExpr(@NotNull ArcDivExpr o) {
    visitExpression(o);
  }

  public void visitElseStatement(@NotNull ArcElseStatement o) {
    visitElement(o);
  }

  public void visitEnumDeclaration(@NotNull ArcEnumDeclaration o) {
    visitEnum(o);
  }

  public void visitEnumFieldDeclaration(@NotNull ArcEnumFieldDeclaration o) {
    visitEnumField(o);
  }

  public void visitEnumId(@NotNull ArcEnumId o) {
    visitNamedElement(o);
  }

  public void visitEnumValueCtorArg(@NotNull ArcEnumValueCtorArg o) {
    visitElement(o);
  }

  public void visitEnumValueCtorArgList(@NotNull ArcEnumValueCtorArgList o) {
    visitElement(o);
  }

  public void visitExpression(@NotNull ArcExpression o) {
    visitElement(o);
  }

  public void visitExternalFuncDeclaration(@NotNull ArcExternalFuncDeclaration o) {
    visitFunction(o);
  }

  public void visitForLoopHeader(@NotNull ArcForLoopHeader o) {
    visitElement(o);
  }

  public void visitForLoopStatement(@NotNull ArcForLoopStatement o) {
    visitElement(o);
  }

  public void visitFuncDeclaration(@NotNull ArcFuncDeclaration o) {
    visitFunction(o);
  }

  public void visitFuncId(@NotNull ArcFuncId o) {
    visitNamedElement(o);
  }

  public void visitFuncReceiverDeclaration(@NotNull ArcFuncReceiverDeclaration o) {
    visitElement(o);
  }

  public void visitFuncReceiverName(@NotNull ArcFuncReceiverName o) {
    visitNamedElement(o);
  }

  public void visitHttpBlock(@NotNull ArcHttpBlock o) {
    visitElement(o);
  }

  public void visitHttpBodyInjection(@NotNull ArcHttpBodyInjection o) {
    visitElement(o);
  }

  public void visitHttpRouteDeclaration(@NotNull ArcHttpRouteDeclaration o) {
    visitElement(o);
  }

  public void visitIfStatement(@NotNull ArcIfStatement o) {
    visitElement(o);
  }

  public void visitImportStatement(@NotNull ArcImportStatement o) {
    visitElement(o);
  }

  public void visitIndexAccessExpr(@NotNull ArcIndexAccessExpr o) {
    visitExpression(o);
  }

  public void visitLhsSliceExpr(@NotNull ArcLhsSliceExpr o) {
    visitExpression(o);
  }

  public void visitList(@NotNull ArcList o) {
    visitElement(o);
  }

  public void visitListElements(@NotNull ArcListElements o) {
    visitElement(o);
  }

  public void visitMinusExpr(@NotNull ArcMinusExpr o) {
    visitExpression(o);
  }

  public void visitMulExpr(@NotNull ArcMulExpr o) {
    visitExpression(o);
  }

  public void visitObjectDeclaration(@NotNull ArcObjectDeclaration o) {
    visitObject(o);
  }

  public void visitObjectFieldDeclaration(@NotNull ArcObjectFieldDeclaration o) {
    visitBaseDeclaration(o);
    // visitPsiElementWithLookup(o);
  }

  public void visitObjectFieldKey(@NotNull ArcObjectFieldKey o) {
    visitElement(o);
  }

  public void visitObjectId(@NotNull ArcObjectId o) {
    visitNamedElement(o);
  }

  public void visitObjectInstantiateExpr(@NotNull ArcObjectInstantiateExpr o) {
    visitExpression(o);
  }

  public void visitObjectInstantiateField(@NotNull ArcObjectInstantiateField o) {
    visitElement(o);
  }

  public void visitObjectInstantiateFields(@NotNull ArcObjectInstantiateFields o) {
    visitElement(o);
  }

  public void visitOrErrorExpr(@NotNull ArcOrErrorExpr o) {
    visitExpression(o);
  }

  public void visitParenExpr(@NotNull ArcParenExpr o) {
    visitExpression(o);
  }

  public void visitPlusExpr(@NotNull ArcPlusExpr o) {
    visitExpression(o);
  }

  public void visitRangeExpr(@NotNull ArcRangeExpr o) {
    visitExpression(o);
  }

  public void visitRefExpr(@NotNull ArcRefExpr o) {
    visitExpression(o);
    // visitIdReferenceExpr(o);
  }

  public void visitReturnStatement(@NotNull ArcReturnStatement o) {
    visitElement(o);
  }

  public void visitRhsSliceExpr(@NotNull ArcRhsSliceExpr o) {
    visitExpression(o);
  }

  public void visitSimpleRefExpr(@NotNull ArcSimpleRefExpr o) {
    visitRefExpr(o);
    // visitResolvable(o);
  }

  public void visitStatement(@NotNull ArcStatement o) {
    visitBaseStatement(o);
  }

  public void visitTopLevelDeclaration(@NotNull ArcTopLevelDeclaration o) {
    visitBaseTopLevelDeclaration(o);
  }

  public void visitType(@NotNull ArcType o) {
    visitNamedElement(o);
  }

  public void visitTypeRef(@NotNull ArcTypeRef o) {
    visitType(o);
  }

  public void visitUnaryMinExpr(@NotNull ArcUnaryMinExpr o) {
    visitExpression(o);
  }

  public void visitUnaryNotExpr(@NotNull ArcUnaryNotExpr o) {
    visitExpression(o);
  }

  public void visitUnaryPlusExpr(@NotNull ArcUnaryPlusExpr o) {
    visitExpression(o);
  }

  public void visitValueExpr(@NotNull ArcValueExpr o) {
    visitExpression(o);
    // visitBaseExpressionElement(o);
  }

  public void visitValueNumber(@NotNull ArcValueNumber o) {
    visitElement(o);
  }

  public void visitValueString(@NotNull ArcValueString o) {
    visitElement(o);
  }

  public void visitVarId(@NotNull ArcVarId o) {
    visitPsiElement(o);
    // visitPsiElementWithLookup(o);
    // visitNamedElement(o);
  }

  public void visitVariableDeclaration(@NotNull ArcVariableDeclaration o) {
    visitBaseDeclaration(o);
  }

  public void visitResolvable(@NotNull ArcResolvable o) {
    visitElement(o);
  }

  public void visitBaseDeclaration(@NotNull ArcBaseDeclaration o) {
    visitElement(o);
  }

  public void visitBaseStatement(@NotNull ArcBaseStatement o) {
    visitElement(o);
  }

  public void visitBaseTopLevelDeclaration(@NotNull ArcBaseTopLevelDeclaration o) {
    visitElement(o);
  }

  public void visitEnum(@NotNull ArcEnum o) {
    visitElement(o);
  }

  public void visitEnumField(@NotNull ArcEnumField o) {
    visitElement(o);
  }

  public void visitFunction(@NotNull ArcFunction o) {
    visitElement(o);
  }

  public void visitNamedElement(@NotNull ArcNamedElement o) {
    visitElement(o);
  }

  public void visitObject(@NotNull ArcObject o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

  public void visitElement(@NotNull ArcElement o) {
    super.visitElement(o);
  }

}
