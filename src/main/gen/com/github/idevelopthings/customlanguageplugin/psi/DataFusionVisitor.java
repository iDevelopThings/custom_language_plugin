// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class DataFusionVisitor extends PsiElementVisitor {

  public void visitAndExpr(@NotNull DataFusionAndExpr o) {
    visitExpression(o);
  }

  public void visitArgListExpr(@NotNull DataFusionArgListExpr o) {
    visitExpression(o);
  }

  public void visitArgumentDeclaration(@NotNull DataFusionArgumentDeclaration o) {
    visitPsiElement(o);
  }

  public void visitArgumentDeclarationList(@NotNull DataFusionArgumentDeclarationList o) {
    visitPsiElement(o);
  }

  public void visitArrayAccessExpr(@NotNull DataFusionArrayAccessExpr o) {
    visitExpression(o);
  }

  public void visitAssignExpr(@NotNull DataFusionAssignExpr o) {
    visitExpression(o);
  }

  public void visitBlockBody(@NotNull DataFusionBlockBody o) {
    visitPsiElement(o);
  }

  public void visitCallExpr(@NotNull DataFusionCallExpr o) {
    visitExpression(o);
  }

  public void visitDictionary(@NotNull DataFusionDictionary o) {
    visitPsiElement(o);
  }

  public void visitDictionaryField(@NotNull DataFusionDictionaryField o) {
    visitPsiElement(o);
  }

  public void visitDictionaryFields(@NotNull DataFusionDictionaryFields o) {
    visitPsiElement(o);
  }

  public void visitDivExpr(@NotNull DataFusionDivExpr o) {
    visitExpression(o);
  }

  public void visitElseStatement(@NotNull DataFusionElseStatement o) {
    visitPsiElement(o);
  }

  public void visitEqualsExpr(@NotNull DataFusionEqualsExpr o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull DataFusionExpression o) {
    visitPsiElement(o);
  }

  public void visitForLoopHeader(@NotNull DataFusionForLoopHeader o) {
    visitPsiElement(o);
  }

  public void visitForLoopStatement(@NotNull DataFusionForLoopStatement o) {
    visitPsiElement(o);
  }

  public void visitFuncDeclaration(@NotNull DataFusionFuncDeclaration o) {
    visitFunction(o);
  }

  public void visitFuncId(@NotNull DataFusionFuncId o) {
    visitNamedElement(o);
  }

  public void visitFuncReceiverDeclaration(@NotNull DataFusionFuncReceiverDeclaration o) {
    visitPsiElement(o);
  }

  public void visitFuncReceiverName(@NotNull DataFusionFuncReceiverName o) {
    visitPsiElement(o);
  }

  public void visitGreaterThanExpr(@NotNull DataFusionGreaterThanExpr o) {
    visitExpression(o);
  }

  public void visitGreaterThanOrEqualsExpr(@NotNull DataFusionGreaterThanOrEqualsExpr o) {
    visitExpression(o);
  }

  public void visitIfStatement(@NotNull DataFusionIfStatement o) {
    visitPsiElement(o);
  }

  public void visitImportStatement(@NotNull DataFusionImportStatement o) {
    visitPsiElement(o);
  }

  public void visitLessThanExpr(@NotNull DataFusionLessThanExpr o) {
    visitExpression(o);
  }

  public void visitLessThanOrEqualsExpr(@NotNull DataFusionLessThanOrEqualsExpr o) {
    visitExpression(o);
  }

  public void visitList(@NotNull DataFusionList o) {
    visitPsiElement(o);
  }

  public void visitListElements(@NotNull DataFusionListElements o) {
    visitPsiElement(o);
  }

  public void visitMemberAccessExpr(@NotNull DataFusionMemberAccessExpr o) {
    visitExpression(o);
  }

  public void visitMemberCallExpr(@NotNull DataFusionMemberCallExpr o) {
    visitExpression(o);
  }

  public void visitMinusExpr(@NotNull DataFusionMinusExpr o) {
    visitExpression(o);
  }

  public void visitMulExpr(@NotNull DataFusionMulExpr o) {
    visitExpression(o);
  }

  public void visitNotEqualsExpr(@NotNull DataFusionNotEqualsExpr o) {
    visitExpression(o);
  }

  public void visitObjectBody(@NotNull DataFusionObjectBody o) {
    visitPsiElement(o);
  }

  public void visitObjectDeclaration(@NotNull DataFusionObjectDeclaration o) {
    visitObject(o);
  }

  public void visitObjectFieldDeclaration(@NotNull DataFusionObjectFieldDeclaration o) {
    visitPsiElement(o);
  }

  public void visitObjectFieldKey(@NotNull DataFusionObjectFieldKey o) {
    visitPsiElement(o);
  }

  public void visitObjectId(@NotNull DataFusionObjectId o) {
    visitNamedElement(o);
  }

  public void visitObjectInstantiateExpr(@NotNull DataFusionObjectInstantiateExpr o) {
    visitExpression(o);
  }

  public void visitObjectInstantiateField(@NotNull DataFusionObjectInstantiateField o) {
    visitPsiElement(o);
  }

  public void visitObjectInstantiateFields(@NotNull DataFusionObjectInstantiateFields o) {
    visitPsiElement(o);
  }

  public void visitOrExpr(@NotNull DataFusionOrExpr o) {
    visitExpression(o);
  }

  public void visitParenExpr(@NotNull DataFusionParenExpr o) {
    visitExpression(o);
  }

  public void visitPlusExpr(@NotNull DataFusionPlusExpr o) {
    visitExpression(o);
  }

  public void visitRangeExpr(@NotNull DataFusionRangeExpr o) {
    visitExpression(o);
  }

  public void visitReturnStatement(@NotNull DataFusionReturnStatement o) {
    visitPsiElement(o);
  }

  public void visitSliceExpr(@NotNull DataFusionSliceExpr o) {
    visitExpression(o);
  }

  public void visitStatement(@NotNull DataFusionStatement o) {
    visitBaseStatement(o);
  }

  public void visitTopLevelDeclaration(@NotNull DataFusionTopLevelDeclaration o) {
    visitPsiElement(o);
  }

  public void visitType(@NotNull DataFusionType o) {
    visitNamedElement(o);
  }

  public void visitUnaryMinExpr(@NotNull DataFusionUnaryMinExpr o) {
    visitExpression(o);
  }

  public void visitUnaryNotExpr(@NotNull DataFusionUnaryNotExpr o) {
    visitExpression(o);
  }

  public void visitUnaryPlusExpr(@NotNull DataFusionUnaryPlusExpr o) {
    visitExpression(o);
  }

  public void visitValueExpr(@NotNull DataFusionValueExpr o) {
    visitExpression(o);
    // visitBaseExpressionElement(o);
  }

  public void visitValueNumber(@NotNull DataFusionValueNumber o) {
    visitPsiElement(o);
  }

  public void visitValueString(@NotNull DataFusionValueString o) {
    visitPsiElement(o);
  }

  public void visitVarId(@NotNull DataFusionVarId o) {
    visitNamedElement(o);
  }

  public void visitVariableDeclaration(@NotNull DataFusionVariableDeclaration o) {
    visitPsiElement(o);
  }

  public void visitBaseStatement(@NotNull DataFusionBaseStatement o) {
    visitPsiElement(o);
  }

  public void visitFunction(@NotNull DataFusionFunction o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull DataFusionNamedElement o) {
    visitPsiElement(o);
  }

  public void visitObject(@NotNull DataFusionObject o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
