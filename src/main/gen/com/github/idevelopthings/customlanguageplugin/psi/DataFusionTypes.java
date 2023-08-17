// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.customlanguageplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.idevelopthings.customlanguageplugin.psi.impl.*;

public interface DataFusionTypes {

  IElementType AND_EXPR = new DataFusionElementType("AND_EXPR");
  IElementType ARGUMENT_DECLARATION = new DataFusionElementType("ARGUMENT_DECLARATION");
  IElementType ARGUMENT_DECLARATION_LIST = new DataFusionElementType("ARGUMENT_DECLARATION_LIST");
  IElementType ARG_LIST_EXPR = new DataFusionElementType("ARG_LIST_EXPR");
  IElementType ARRAY_ACCESS_EXPR = new DataFusionElementType("ARRAY_ACCESS_EXPR");
  IElementType ASSIGN_EXPR = new DataFusionElementType("ASSIGN_EXPR");
  IElementType BLOCK_BODY = new DataFusionElementType("BLOCK_BODY");
  IElementType CALL_EXPR = new DataFusionElementType("CALL_EXPR");
  IElementType DICTIONARY = new DataFusionElementType("DICTIONARY");
  IElementType DICTIONARY_FIELD = new DataFusionElementType("DICTIONARY_FIELD");
  IElementType DICTIONARY_FIELDS = new DataFusionElementType("DICTIONARY_FIELDS");
  IElementType DIV_EXPR = new DataFusionElementType("DIV_EXPR");
  IElementType ELSE_STATEMENT = new DataFusionElementType("ELSE_STATEMENT");
  IElementType EQUALS_EXPR = new DataFusionElementType("EQUALS_EXPR");
  IElementType EXPRESSION = new DataFusionElementType("EXPRESSION");
  IElementType FOR_LOOP_HEADER = new DataFusionElementType("FOR_LOOP_HEADER");
  IElementType FOR_LOOP_STATEMENT = new DataFusionElementType("FOR_LOOP_STATEMENT");
  IElementType FUNC_DECLARATION = new DataFusionElementType("FUNC_DECLARATION");
  IElementType FUNC_ID = new DataFusionElementType("FUNC_ID");
  IElementType FUNC_RECEIVER_DECLARATION = new DataFusionElementType("FUNC_RECEIVER_DECLARATION");
  IElementType FUNC_RECEIVER_NAME = new DataFusionElementType("FUNC_RECEIVER_NAME");
  IElementType GREATER_THAN_EXPR = new DataFusionElementType("GREATER_THAN_EXPR");
  IElementType GREATER_THAN_OR_EQUALS_EXPR = new DataFusionElementType("GREATER_THAN_OR_EQUALS_EXPR");
  IElementType IF_STATEMENT = new DataFusionElementType("IF_STATEMENT");
  IElementType IMPORT_STATEMENT = new DataFusionElementType("IMPORT_STATEMENT");
  IElementType LESS_THAN_EXPR = new DataFusionElementType("LESS_THAN_EXPR");
  IElementType LESS_THAN_OR_EQUALS_EXPR = new DataFusionElementType("LESS_THAN_OR_EQUALS_EXPR");
  IElementType LIST = new DataFusionElementType("LIST");
  IElementType LIST_ELEMENTS = new DataFusionElementType("LIST_ELEMENTS");
  IElementType MEMBER_ACCESS_EXPR = new DataFusionElementType("MEMBER_ACCESS_EXPR");
  IElementType MEMBER_CALL_EXPR = new DataFusionElementType("MEMBER_CALL_EXPR");
  IElementType MINUS_EXPR = new DataFusionElementType("MINUS_EXPR");
  IElementType MUL_EXPR = new DataFusionElementType("MUL_EXPR");
  IElementType NOT_EQUALS_EXPR = new DataFusionElementType("NOT_EQUALS_EXPR");
  IElementType OBJECT_BODY = new DataFusionElementType("OBJECT_BODY");
  IElementType OBJECT_DECLARATION = new DataFusionElementType("OBJECT_DECLARATION");
  IElementType OBJECT_FIELD_DECLARATION = new DataFusionElementType("OBJECT_FIELD_DECLARATION");
  IElementType OBJECT_FIELD_KEY = new DataFusionElementType("OBJECT_FIELD_KEY");
  IElementType OBJECT_ID = new DataFusionElementType("OBJECT_ID");
  IElementType OBJECT_INSTANTIATE_EXPR = new DataFusionElementType("OBJECT_INSTANTIATE_EXPR");
  IElementType OBJECT_INSTANTIATE_FIELD = new DataFusionElementType("OBJECT_INSTANTIATE_FIELD");
  IElementType OBJECT_INSTANTIATE_FIELDS = new DataFusionElementType("OBJECT_INSTANTIATE_FIELDS");
  IElementType OR_EXPR = new DataFusionElementType("OR_EXPR");
  IElementType PAREN_EXPR = new DataFusionElementType("PAREN_EXPR");
  IElementType PLUS_EXPR = new DataFusionElementType("PLUS_EXPR");
  IElementType RANGE_EXPR = new DataFusionElementType("RANGE_EXPR");
  IElementType RETURN_STATEMENT = new DataFusionElementType("RETURN_STATEMENT");
  IElementType SLICE_EXPR = new DataFusionElementType("SLICE_EXPR");
  IElementType STATEMENT = new DataFusionElementType("STATEMENT");
  IElementType TOP_LEVEL_DECLARATION = new DataFusionElementType("TOP_LEVEL_DECLARATION");
  IElementType TYPE = new DataFusionElementType("TYPE");
  IElementType UNARY_MIN_EXPR = new DataFusionElementType("UNARY_MIN_EXPR");
  IElementType UNARY_NOT_EXPR = new DataFusionElementType("UNARY_NOT_EXPR");
  IElementType UNARY_PLUS_EXPR = new DataFusionElementType("UNARY_PLUS_EXPR");
  IElementType VALUE_EXPR = new DataFusionElementType("VALUE_EXPR");
  IElementType VALUE_NUMBER = new DataFusionElementType("VALUE_NUMBER");
  IElementType VALUE_STRING = new DataFusionElementType("VALUE_STRING");
  IElementType VARIABLE_DECLARATION = new DataFusionElementType("VARIABLE_DECLARATION");
  IElementType VAR_ID = new DataFusionElementType("VAR_ID");

  IElementType AND = new DataFusionTokenType("&&");
  IElementType AS_KW = new DataFusionTokenType("as");
  IElementType BACKTICK_STRING = new DataFusionTokenType("BACKTICK_STRING");
  IElementType BLOCK_COMMENT = new DataFusionTokenType("BLOCK_COMMENT");
  IElementType BREAK_KW = new DataFusionTokenType("break");
  IElementType COLON = new DataFusionTokenType(":");
  IElementType COLONCOLON = new DataFusionTokenType("::");
  IElementType COMMA = new DataFusionTokenType(",");
  IElementType CONTINUE_KW = new DataFusionTokenType("continue");
  IElementType DIV = new DataFusionTokenType("/");
  IElementType DIVEQ = new DataFusionTokenType("/=");
  IElementType DOT = new DataFusionTokenType(".");
  IElementType DOTDOT = new DataFusionTokenType("..");
  IElementType DOUBLE_QUOUTE_STRING = new DataFusionTokenType("DOUBLE_QUOUTE_STRING");
  IElementType ELSE_KW = new DataFusionTokenType("else");
  IElementType EQ = new DataFusionTokenType("=");
  IElementType EQEQ = new DataFusionTokenType("==");
  IElementType FOR_KW = new DataFusionTokenType("for");
  IElementType FUNC_KW = new DataFusionTokenType("func");
  IElementType GE = new DataFusionTokenType(">=");
  IElementType ID = new DataFusionTokenType("ID");
  IElementType IF_KW = new DataFusionTokenType("if");
  IElementType IMPORT_KW = new DataFusionTokenType("import");
  IElementType LANGLE = new DataFusionTokenType("<");
  IElementType LBRACK = new DataFusionTokenType("[");
  IElementType LCURLY = new DataFusionTokenType("{");
  IElementType LE = new DataFusionTokenType("<=");
  IElementType LINE_COMMENT = new DataFusionTokenType("LINE_COMMENT");
  IElementType LPAREN = new DataFusionTokenType("(");
  IElementType MINUS = new DataFusionTokenType("-");
  IElementType MINUSEQ = new DataFusionTokenType("-=");
  IElementType MUL = new DataFusionTokenType("*");
  IElementType MULEQ = new DataFusionTokenType("*=");
  IElementType NE = new DataFusionTokenType("!=");
  IElementType NOT = new DataFusionTokenType("!");
  IElementType OBJECT_KW = new DataFusionTokenType("object");
  IElementType OR = new DataFusionTokenType("||");
  IElementType PLUS = new DataFusionTokenType("+");
  IElementType PLUSEQ = new DataFusionTokenType("+=");
  IElementType RANGLE = new DataFusionTokenType(">");
  IElementType RBRACK = new DataFusionTokenType("]");
  IElementType RCURLY = new DataFusionTokenType("}");
  IElementType RETURN_KW = new DataFusionTokenType("return");
  IElementType RPAREN = new DataFusionTokenType(")");
  IElementType SEMICOLON = new DataFusionTokenType(";");
  IElementType SINGLE_QUOUTE_STRING = new DataFusionTokenType("SINGLE_QUOUTE_STRING");
  IElementType STEP_KW = new DataFusionTokenType("step");
  IElementType VALUE_BOOL = new DataFusionTokenType("VALUE_BOOL");
  IElementType VALUE_FLOAT = new DataFusionTokenType("VALUE_FLOAT");
  IElementType VALUE_INTEGER = new DataFusionTokenType("VALUE_INTEGER");
  IElementType VALUE_NULL = new DataFusionTokenType("VALUE_NULL");
  IElementType VAR_KW = new DataFusionTokenType("var");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == AND_EXPR) {
        return new DataFusionAndExprImpl(node);
      }
      else if (type == ARGUMENT_DECLARATION) {
        return new DataFusionArgumentDeclarationImpl(node);
      }
      else if (type == ARGUMENT_DECLARATION_LIST) {
        return new DataFusionArgumentDeclarationListImpl(node);
      }
      else if (type == ARG_LIST_EXPR) {
        return new DataFusionArgListExprImpl(node);
      }
      else if (type == ARRAY_ACCESS_EXPR) {
        return new DataFusionArrayAccessExprImpl(node);
      }
      else if (type == ASSIGN_EXPR) {
        return new DataFusionAssignExprImpl(node);
      }
      else if (type == BLOCK_BODY) {
        return new DataFusionBlockBodyImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new DataFusionCallExprImpl(node);
      }
      else if (type == DICTIONARY) {
        return new DataFusionDictionaryImpl(node);
      }
      else if (type == DICTIONARY_FIELD) {
        return new DataFusionDictionaryFieldImpl(node);
      }
      else if (type == DICTIONARY_FIELDS) {
        return new DataFusionDictionaryFieldsImpl(node);
      }
      else if (type == DIV_EXPR) {
        return new DataFusionDivExprImpl(node);
      }
      else if (type == ELSE_STATEMENT) {
        return new DataFusionElseStatementImpl(node);
      }
      else if (type == EQUALS_EXPR) {
        return new DataFusionEqualsExprImpl(node);
      }
      else if (type == FOR_LOOP_HEADER) {
        return new DataFusionForLoopHeaderImpl(node);
      }
      else if (type == FOR_LOOP_STATEMENT) {
        return new DataFusionForLoopStatementImpl(node);
      }
      else if (type == FUNC_DECLARATION) {
        return new DataFusionFuncDeclarationImpl(node);
      }
      else if (type == FUNC_ID) {
        return new DataFusionFuncIdImpl(node);
      }
      else if (type == FUNC_RECEIVER_DECLARATION) {
        return new DataFusionFuncReceiverDeclarationImpl(node);
      }
      else if (type == FUNC_RECEIVER_NAME) {
        return new DataFusionFuncReceiverNameImpl(node);
      }
      else if (type == GREATER_THAN_EXPR) {
        return new DataFusionGreaterThanExprImpl(node);
      }
      else if (type == GREATER_THAN_OR_EQUALS_EXPR) {
        return new DataFusionGreaterThanOrEqualsExprImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new DataFusionIfStatementImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new DataFusionImportStatementImpl(node);
      }
      else if (type == LESS_THAN_EXPR) {
        return new DataFusionLessThanExprImpl(node);
      }
      else if (type == LESS_THAN_OR_EQUALS_EXPR) {
        return new DataFusionLessThanOrEqualsExprImpl(node);
      }
      else if (type == LIST) {
        return new DataFusionListImpl(node);
      }
      else if (type == LIST_ELEMENTS) {
        return new DataFusionListElementsImpl(node);
      }
      else if (type == MEMBER_ACCESS_EXPR) {
        return new DataFusionMemberAccessExprImpl(node);
      }
      else if (type == MEMBER_CALL_EXPR) {
        return new DataFusionMemberCallExprImpl(node);
      }
      else if (type == MINUS_EXPR) {
        return new DataFusionMinusExprImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new DataFusionMulExprImpl(node);
      }
      else if (type == NOT_EQUALS_EXPR) {
        return new DataFusionNotEqualsExprImpl(node);
      }
      else if (type == OBJECT_BODY) {
        return new DataFusionObjectBodyImpl(node);
      }
      else if (type == OBJECT_DECLARATION) {
        return new DataFusionObjectDeclarationImpl(node);
      }
      else if (type == OBJECT_FIELD_DECLARATION) {
        return new DataFusionObjectFieldDeclarationImpl(node);
      }
      else if (type == OBJECT_FIELD_KEY) {
        return new DataFusionObjectFieldKeyImpl(node);
      }
      else if (type == OBJECT_ID) {
        return new DataFusionObjectIdImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_EXPR) {
        return new DataFusionObjectInstantiateExprImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_FIELD) {
        return new DataFusionObjectInstantiateFieldImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_FIELDS) {
        return new DataFusionObjectInstantiateFieldsImpl(node);
      }
      else if (type == OR_EXPR) {
        return new DataFusionOrExprImpl(node);
      }
      else if (type == PAREN_EXPR) {
        return new DataFusionParenExprImpl(node);
      }
      else if (type == PLUS_EXPR) {
        return new DataFusionPlusExprImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new DataFusionRangeExprImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new DataFusionReturnStatementImpl(node);
      }
      else if (type == SLICE_EXPR) {
        return new DataFusionSliceExprImpl(node);
      }
      else if (type == STATEMENT) {
        return new DataFusionStatementImpl(node);
      }
      else if (type == TOP_LEVEL_DECLARATION) {
        return new DataFusionTopLevelDeclarationImpl(node);
      }
      else if (type == TYPE) {
        return new DataFusionTypeImpl(node);
      }
      else if (type == UNARY_MIN_EXPR) {
        return new DataFusionUnaryMinExprImpl(node);
      }
      else if (type == UNARY_NOT_EXPR) {
        return new DataFusionUnaryNotExprImpl(node);
      }
      else if (type == UNARY_PLUS_EXPR) {
        return new DataFusionUnaryPlusExprImpl(node);
      }
      else if (type == VALUE_EXPR) {
        return new DataFusionValueExprImpl(node);
      }
      else if (type == VALUE_NUMBER) {
        return new DataFusionValueNumberImpl(node);
      }
      else if (type == VALUE_STRING) {
        return new DataFusionValueStringImpl(node);
      }
      else if (type == VARIABLE_DECLARATION) {
        return new DataFusionVariableDeclarationImpl(node);
      }
      else if (type == VAR_ID) {
        return new DataFusionVarIdImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
