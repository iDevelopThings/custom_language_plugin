// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ArcVisitor extends PsiElementVisitor {

  public void visitAndExpr(@NotNull ArcAndExpr o) {
    visitExpression(o);
  }

  public void visitArgListExpr(@NotNull ArcArgListExpr o) {
    visitExpression(o);
  }

  public void visitArgumentDeclaration(@NotNull ArcArgumentDeclaration o) {
    visitPsiElement(o);
  }

  public void visitArgumentDeclarationList(@NotNull ArcArgumentDeclarationList o) {
    visitPsiElement(o);
  }

  public void visitArrayAccessExpr(@NotNull ArcArrayAccessExpr o) {
    visitExpression(o);
  }

  public void visitAssignExpr(@NotNull ArcAssignExpr o) {
    visitExpression(o);
  }

  public void visitBlockBody(@NotNull ArcBlockBody o) {
    visitPsiElement(o);
  }

  public void visitCallExpr(@NotNull ArcCallExpr o) {
    visitExpression(o);
  }

  public void visitDictionary(@NotNull ArcDictionary o) {
    visitPsiElement(o);
  }

  public void visitDictionaryField(@NotNull ArcDictionaryField o) {
    visitPsiElement(o);
  }

  public void visitDictionaryFields(@NotNull ArcDictionaryFields o) {
    visitPsiElement(o);
  }

  public void visitDivExpr(@NotNull ArcDivExpr o) {
    visitExpression(o);
  }

  public void visitElseStatement(@NotNull ArcElseStatement o) {
    visitPsiElement(o);
  }

  public void visitEqualsExpr(@NotNull ArcEqualsExpr o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull ArcExpression o) {
    visitPsiElement(o);
  }

  public void visitForLoopHeader(@NotNull ArcForLoopHeader o) {
    visitPsiElement(o);
  }

  public void visitForLoopStatement(@NotNull ArcForLoopStatement o) {
    visitPsiElement(o);
  }

  public void visitFuncDeclaration(@NotNull ArcFuncDeclaration o) {
    visitFunction(o);
  }

  public void visitFuncId(@NotNull ArcFuncId o) {
    visitNamedElement(o);
  }

  public void visitFuncReceiverDeclaration(@NotNull ArcFuncReceiverDeclaration o) {
    visitPsiElement(o);
  }

  public void visitFuncReceiverName(@NotNull ArcFuncReceiverName o) {
    visitPsiElement(o);
  }

  public void visitGreaterThanExpr(@NotNull ArcGreaterThanExpr o) {
    visitExpression(o);
  }

  public void visitGreaterThanOrEqualsExpr(@NotNull ArcGreaterThanOrEqualsExpr o) {
    visitExpression(o);
  }

  public void visitIfStatement(@NotNull ArcIfStatement o) {
    visitPsiElement(o);
  }

  public void visitImportStatement(@NotNull ArcImportStatement o) {
    visitPsiElement(o);
  }

  public void visitLessThanExpr(@NotNull ArcLessThanExpr o) {
    visitExpression(o);
  }

  public void visitLessThanOrEqualsExpr(@NotNull ArcLessThanOrEqualsExpr o) {
    visitExpression(o);
  }

  public void visitList(@NotNull ArcList o) {
    visitPsiElement(o);
  }

  public void visitListElements(@NotNull ArcListElements o) {
    visitPsiElement(o);
  }

  public void visitMemberAccessExpr(@NotNull ArcMemberAccessExpr o) {
    visitExpression(o);
  }

  public void visitMemberCallExpr(@NotNull ArcMemberCallExpr o) {
    visitExpression(o);
  }

  public void visitMinusExpr(@NotNull ArcMinusExpr o) {
    visitExpression(o);
  }

  public void visitMulExpr(@NotNull ArcMulExpr o) {
    visitExpression(o);
  }

  public void visitNotEqualsExpr(@NotNull ArcNotEqualsExpr o) {
    visitExpression(o);
  }

  public void visitObjectBody(@NotNull ArcObjectBody o) {
    visitPsiElement(o);
  }

  public void visitObjectDeclaration(@NotNull ArcObjectDeclaration o) {
    visitObject(o);
  }

  public void visitObjectFieldDeclaration(@NotNull ArcObjectFieldDeclaration o) {
    visitPsiElement(o);
  }

  public void visitObjectFieldKey(@NotNull ArcObjectFieldKey o) {
    visitPsiElement(o);
  }

  public void visitObjectId(@NotNull ArcObjectId o) {
    visitNamedElement(o);
  }

  public void visitObjectInstantiateExpr(@NotNull ArcObjectInstantiateExpr o) {
    visitExpression(o);
  }

  public void visitObjectInstantiateField(@NotNull ArcObjectInstantiateField o) {
    visitPsiElement(o);
  }

  public void visitObjectInstantiateFields(@NotNull ArcObjectInstantiateFields o) {
    visitPsiElement(o);
  }

  public void visitOrExpr(@NotNull ArcOrExpr o) {
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

  public void visitReturnStatement(@NotNull ArcReturnStatement o) {
    visitPsiElement(o);
  }

  public void visitSliceExpr(@NotNull ArcSliceExpr o) {
    visitExpression(o);
  }

  public void visitStatement(@NotNull ArcStatement o) {
    visitBaseStatement(o);
  }

  public void visitTopLevelDeclaration(@NotNull ArcTopLevelDeclaration o) {
    visitDeclaration(o);
  }

  public void visitType(@NotNull ArcType o) {
    visitNamedElement(o);
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
    visitPsiElement(o);
  }

  public void visitValueString(@NotNull ArcValueString o) {
    visitPsiElement(o);
  }

  public void visitVarId(@NotNull ArcVarId o) {
    visitNamedElement(o);
  }

  public void visitVariableDeclaration(@NotNull ArcVariableDeclaration o) {
    visitPsiElement(o);
  }

  public void visitBaseStatement(@NotNull ArcBaseStatement o) {
    visitPsiElement(o);
  }

  public void visitDeclaration(@NotNull ArcDeclaration o) {
    visitPsiElement(o);
  }

  public void visitFunction(@NotNull ArcFunction o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull ArcNamedElement o) {
    visitPsiElement(o);
  }

  public void visitObject(@NotNull ArcObject o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
