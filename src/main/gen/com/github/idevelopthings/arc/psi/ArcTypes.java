// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.idevelopthings.arc.psi.impl.*;

public interface ArcTypes {

  IElementType ANONYMOUS_FUNC = new ArcElementType("ANONYMOUS_FUNC");
  IElementType ARGUMENT_DECLARATION = new ArcElementType("ARGUMENT_DECLARATION");
  IElementType ARGUMENT_DECLARATION_LIST = new ArcElementType("ARGUMENT_DECLARATION_LIST");
  IElementType ARGUMENT_ID = new ArcElementType("ARGUMENT_ID");
  IElementType ARG_LIST = new ArcElementType("ARG_LIST");
  IElementType ARRAY_ACCESS_EXPR = new ArcElementType("ARRAY_ACCESS_EXPR");
  IElementType ASSIGN_EXPR = new ArcElementType("ASSIGN_EXPR");
  IElementType BLOCK_BODY = new ArcElementType("BLOCK_BODY");
  IElementType BOTH_SLICE_EXPR = new ArcElementType("BOTH_SLICE_EXPR");
  IElementType CALL_EXPR = new ArcElementType("CALL_EXPR");
  IElementType CONDITIONAL_EXPR = new ArcElementType("CONDITIONAL_EXPR");
  IElementType DEFER_STATEMENT = new ArcElementType("DEFER_STATEMENT");
  IElementType DELETE_STATEMENT = new ArcElementType("DELETE_STATEMENT");
  IElementType DICTIONARY = new ArcElementType("DICTIONARY");
  IElementType DICTIONARY_FIELD = new ArcElementType("DICTIONARY_FIELD");
  IElementType DICTIONARY_FIELDS = new ArcElementType("DICTIONARY_FIELDS");
  IElementType DIV_EXPR = new ArcElementType("DIV_EXPR");
  IElementType ELSE_STATEMENT = new ArcElementType("ELSE_STATEMENT");
  IElementType ENUM_DECLARATION = new ArcElementType("ENUM_DECLARATION");
  IElementType ENUM_FIELD_DECLARATION = new ArcElementType("ENUM_FIELD_DECLARATION");
  IElementType ENUM_ID = new ArcElementType("ENUM_ID");
  IElementType ENUM_VALUE_CTOR_ARG = new ArcElementType("ENUM_VALUE_CTOR_ARG");
  IElementType ENUM_VALUE_CTOR_ARG_LIST = new ArcElementType("ENUM_VALUE_CTOR_ARG_LIST");
  IElementType EXPRESSION = new ArcElementType("EXPRESSION");
  IElementType EXTERNAL_FUNC_DECLARATION = new ArcElementType("EXTERNAL_FUNC_DECLARATION");
  IElementType FOR_LOOP_HEADER = new ArcElementType("FOR_LOOP_HEADER");
  IElementType FOR_LOOP_STATEMENT = new ArcElementType("FOR_LOOP_STATEMENT");
  IElementType FUNC_DECLARATION = new ArcElementType("FUNC_DECLARATION");
  IElementType FUNC_ID = new ArcElementType("FUNC_ID");
  IElementType FUNC_RECEIVER_DECLARATION = new ArcElementType("FUNC_RECEIVER_DECLARATION");
  IElementType FUNC_RECEIVER_NAME = new ArcElementType("FUNC_RECEIVER_NAME");
  IElementType HTTP_BLOCK = new ArcElementType("HTTP_BLOCK");
  IElementType HTTP_BODY_INJECTION = new ArcElementType("HTTP_BODY_INJECTION");
  IElementType HTTP_ROUTE_DECLARATION = new ArcElementType("HTTP_ROUTE_DECLARATION");
  IElementType IF_STATEMENT = new ArcElementType("IF_STATEMENT");
  IElementType IMPORT_STATEMENT = new ArcElementType("IMPORT_STATEMENT");
  IElementType INDEX_ACCESS_EXPR = new ArcElementType("INDEX_ACCESS_EXPR");
  IElementType LHS_SLICE_EXPR = new ArcElementType("LHS_SLICE_EXPR");
  IElementType LIST = new ArcElementType("LIST");
  IElementType LIST_ELEMENTS = new ArcElementType("LIST_ELEMENTS");
  IElementType MINUS_EXPR = new ArcElementType("MINUS_EXPR");
  IElementType MUL_EXPR = new ArcElementType("MUL_EXPR");
  IElementType OBJECT_DECLARATION = new ArcElementType("OBJECT_DECLARATION");
  IElementType OBJECT_FIELD_DECLARATION = new ArcElementType("OBJECT_FIELD_DECLARATION");
  IElementType OBJECT_FIELD_KEY = new ArcElementType("OBJECT_FIELD_KEY");
  IElementType OBJECT_ID = new ArcElementType("OBJECT_ID");
  IElementType OBJECT_INSTANTIATE_EXPR = new ArcElementType("OBJECT_INSTANTIATE_EXPR");
  IElementType OBJECT_INSTANTIATE_FIELD = new ArcElementType("OBJECT_INSTANTIATE_FIELD");
  IElementType OBJECT_INSTANTIATE_FIELDS = new ArcElementType("OBJECT_INSTANTIATE_FIELDS");
  IElementType OR_ERROR_EXPR = new ArcElementType("OR_ERROR_EXPR");
  IElementType PAREN_EXPR = new ArcElementType("PAREN_EXPR");
  IElementType PLUS_EXPR = new ArcElementType("PLUS_EXPR");
  IElementType RANGE_EXPR = new ArcElementType("RANGE_EXPR");
  IElementType REF_EXPR = new ArcElementType("REF_EXPR");
  IElementType RETURN_STATEMENT = new ArcElementType("RETURN_STATEMENT");
  IElementType RHS_SLICE_EXPR = new ArcElementType("RHS_SLICE_EXPR");
  IElementType SIMPLE_REF_EXPR = new ArcElementType("SIMPLE_REF_EXPR");
  IElementType STATEMENT = new ArcElementType("STATEMENT");
  IElementType TOP_LEVEL_DECLARATION = new ArcElementType("TOP_LEVEL_DECLARATION");
  IElementType TYPE = new ArcElementType("TYPE");
  IElementType TYPE_REF = new ArcElementType("TYPE_REF");
  IElementType UNARY_MIN_EXPR = new ArcElementType("UNARY_MIN_EXPR");
  IElementType UNARY_NOT_EXPR = new ArcElementType("UNARY_NOT_EXPR");
  IElementType UNARY_PLUS_EXPR = new ArcElementType("UNARY_PLUS_EXPR");
  IElementType VALUE_EXPR = new ArcElementType("VALUE_EXPR");
  IElementType VALUE_NUMBER = new ArcElementType("VALUE_NUMBER");
  IElementType VALUE_STRING = new ArcElementType("VALUE_STRING");
  IElementType VARIABLE_DECLARATION = new ArcElementType("VARIABLE_DECLARATION");
  IElementType VAR_ID = new ArcElementType("VAR_ID");

  IElementType AND = new ArcTokenType("&&");
  IElementType AS_KW = new ArcTokenType("as");
  IElementType BACKTICK_STRING = new ArcTokenType("BACKTICK_STRING");
  IElementType BLOCK_COMMENT = new ArcTokenType("BLOCK_COMMENT");
  IElementType BRACKET_PAIR = new ArcTokenType("[]");
  IElementType BREAK_KW = new ArcTokenType("break");
  IElementType COLON = new ArcTokenType(":");
  IElementType COLONCOLON = new ArcTokenType("::");
  IElementType COMMA = new ArcTokenType(",");
  IElementType CONTINUE_KW = new ArcTokenType("continue");
  IElementType DEFER_KW = new ArcTokenType("defer");
  IElementType DELETE_KW = new ArcTokenType("delete");
  IElementType DIV = new ArcTokenType("/");
  IElementType DIVEQ = new ArcTokenType("/=");
  IElementType DOT = new ArcTokenType(".");
  IElementType DOTDOT = new ArcTokenType("..");
  IElementType DOTDOTDOT = new ArcTokenType("...");
  IElementType DOUBLE_QUOUTE_STRING = new ArcTokenType("DOUBLE_QUOUTE_STRING");
  IElementType ELSE_KW = new ArcTokenType("else");
  IElementType ENUM_KW = new ArcTokenType("enum");
  IElementType EQ = new ArcTokenType("=");
  IElementType EQEQ = new ArcTokenType("==");
  IElementType EXTERN_KW = new ArcTokenType("extern");
  IElementType FOR_KW = new ArcTokenType("for");
  IElementType FUNC_KW = new ArcTokenType("func");
  IElementType GE = new ArcTokenType(">=");
  IElementType HTTP_FROM_KW = new ArcTokenType("from");
  IElementType HTTP_KW = new ArcTokenType("http");
  IElementType HTTP_ROUTE_KW = new ArcTokenType("route");
  IElementType ID = new ArcTokenType("ID");
  IElementType IF_KW = new ArcTokenType("if");
  IElementType IMPORT_KW = new ArcTokenType("import");
  IElementType LANGLE = new ArcTokenType("<");
  IElementType LBRACK = new ArcTokenType("[");
  IElementType LCURLY = new ArcTokenType("{");
  IElementType LE = new ArcTokenType("<=");
  IElementType LINE_COMMENT = new ArcTokenType("LINE_COMMENT");
  IElementType LPAREN = new ArcTokenType("(");
  IElementType MINUS = new ArcTokenType("-");
  IElementType MINUSEQ = new ArcTokenType("-=");
  IElementType MUL = new ArcTokenType("*");
  IElementType MULEQ = new ArcTokenType("*=");
  IElementType NE = new ArcTokenType("!=");
  IElementType NOT = new ArcTokenType("!");
  IElementType OBJECT_KW = new ArcTokenType("object");
  IElementType OR = new ArcTokenType("||");
  IElementType OR_KW = new ArcTokenType("or");
  IElementType PLUS = new ArcTokenType("+");
  IElementType PLUSEQ = new ArcTokenType("+=");
  IElementType QUESTION = new ArcTokenType("?");
  IElementType RANGLE = new ArcTokenType(">");
  IElementType RBRACK = new ArcTokenType("]");
  IElementType RCURLY = new ArcTokenType("}");
  IElementType RETURN_KW = new ArcTokenType("return");
  IElementType RPAREN = new ArcTokenType(")");
  IElementType SEMICOLON = new ArcTokenType(";");
  IElementType SEMICOLON_SYNTHETIC = new ArcTokenType("<NL>");
  IElementType SINGLE_QUOUTE_STRING = new ArcTokenType("SINGLE_QUOUTE_STRING");
  IElementType STEP_KW = new ArcTokenType("step");
  IElementType VALUE_BOOL = new ArcTokenType("VALUE_BOOL");
  IElementType VALUE_FLOAT = new ArcTokenType("VALUE_FLOAT");
  IElementType VALUE_INTEGER = new ArcTokenType("VALUE_INTEGER");
  IElementType VALUE_NULL = new ArcTokenType("VALUE_NULL");
  IElementType VAR_KW = new ArcTokenType("var");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ANONYMOUS_FUNC) {
        return new ArcAnonymousFuncImpl(node);
      }
      else if (type == ARGUMENT_DECLARATION) {
        return new ArcArgumentDeclarationImpl(node);
      }
      else if (type == ARGUMENT_DECLARATION_LIST) {
        return new ArcArgumentDeclarationListImpl(node);
      }
      else if (type == ARGUMENT_ID) {
        return new ArcArgumentIdImpl(node);
      }
      else if (type == ARG_LIST) {
        return new ArcArgListImpl(node);
      }
      else if (type == ARRAY_ACCESS_EXPR) {
        return new ArcArrayAccessExprImpl(node);
      }
      else if (type == ASSIGN_EXPR) {
        return new ArcAssignExprImpl(node);
      }
      else if (type == BLOCK_BODY) {
        return new ArcBlockBodyImpl(node);
      }
      else if (type == BOTH_SLICE_EXPR) {
        return new ArcBothSliceExprImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new ArcCallExprImpl(node);
      }
      else if (type == CONDITIONAL_EXPR) {
        return new ArcConditionalExprImpl(node);
      }
      else if (type == DEFER_STATEMENT) {
        return new ArcDeferStatementImpl(node);
      }
      else if (type == DELETE_STATEMENT) {
        return new ArcDeleteStatementImpl(node);
      }
      else if (type == DICTIONARY) {
        return new ArcDictionaryImpl(node);
      }
      else if (type == DICTIONARY_FIELD) {
        return new ArcDictionaryFieldImpl(node);
      }
      else if (type == DICTIONARY_FIELDS) {
        return new ArcDictionaryFieldsImpl(node);
      }
      else if (type == DIV_EXPR) {
        return new ArcDivExprImpl(node);
      }
      else if (type == ELSE_STATEMENT) {
        return new ArcElseStatementImpl(node);
      }
      else if (type == ENUM_DECLARATION) {
        return new ArcEnumDeclarationImpl(node);
      }
      else if (type == ENUM_FIELD_DECLARATION) {
        return new ArcEnumFieldDeclarationImpl(node);
      }
      else if (type == ENUM_ID) {
        return new ArcEnumIdImpl(node);
      }
      else if (type == ENUM_VALUE_CTOR_ARG) {
        return new ArcEnumValueCtorArgImpl(node);
      }
      else if (type == ENUM_VALUE_CTOR_ARG_LIST) {
        return new ArcEnumValueCtorArgListImpl(node);
      }
      else if (type == EXTERNAL_FUNC_DECLARATION) {
        return new ArcExternalFuncDeclarationImpl(node);
      }
      else if (type == FOR_LOOP_HEADER) {
        return new ArcForLoopHeaderImpl(node);
      }
      else if (type == FOR_LOOP_STATEMENT) {
        return new ArcForLoopStatementImpl(node);
      }
      else if (type == FUNC_DECLARATION) {
        return new ArcFuncDeclarationImpl(node);
      }
      else if (type == FUNC_ID) {
        return new ArcFuncIdImpl(node);
      }
      else if (type == FUNC_RECEIVER_DECLARATION) {
        return new ArcFuncReceiverDeclarationImpl(node);
      }
      else if (type == FUNC_RECEIVER_NAME) {
        return new ArcFuncReceiverNameImpl(node);
      }
      else if (type == HTTP_BLOCK) {
        return new ArcHttpBlockImpl(node);
      }
      else if (type == HTTP_BODY_INJECTION) {
        return new ArcHttpBodyInjectionImpl(node);
      }
      else if (type == HTTP_ROUTE_DECLARATION) {
        return new ArcHttpRouteDeclarationImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new ArcIfStatementImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new ArcImportStatementImpl(node);
      }
      else if (type == INDEX_ACCESS_EXPR) {
        return new ArcIndexAccessExprImpl(node);
      }
      else if (type == LHS_SLICE_EXPR) {
        return new ArcLhsSliceExprImpl(node);
      }
      else if (type == LIST) {
        return new ArcListImpl(node);
      }
      else if (type == LIST_ELEMENTS) {
        return new ArcListElementsImpl(node);
      }
      else if (type == MINUS_EXPR) {
        return new ArcMinusExprImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new ArcMulExprImpl(node);
      }
      else if (type == OBJECT_DECLARATION) {
        return new ArcObjectDeclarationImpl(node);
      }
      else if (type == OBJECT_FIELD_DECLARATION) {
        return new ArcObjectFieldDeclarationImpl(node);
      }
      else if (type == OBJECT_FIELD_KEY) {
        return new ArcObjectFieldKeyImpl(node);
      }
      else if (type == OBJECT_ID) {
        return new ArcObjectIdImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_EXPR) {
        return new ArcObjectInstantiateExprImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_FIELD) {
        return new ArcObjectInstantiateFieldImpl(node);
      }
      else if (type == OBJECT_INSTANTIATE_FIELDS) {
        return new ArcObjectInstantiateFieldsImpl(node);
      }
      else if (type == OR_ERROR_EXPR) {
        return new ArcOrErrorExprImpl(node);
      }
      else if (type == PAREN_EXPR) {
        return new ArcParenExprImpl(node);
      }
      else if (type == PLUS_EXPR) {
        return new ArcPlusExprImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new ArcRangeExprImpl(node);
      }
      else if (type == REF_EXPR) {
        return new ArcRefExprImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new ArcReturnStatementImpl(node);
      }
      else if (type == RHS_SLICE_EXPR) {
        return new ArcRhsSliceExprImpl(node);
      }
      else if (type == SIMPLE_REF_EXPR) {
        return new ArcSimpleRefExprImpl(node);
      }
      else if (type == STATEMENT) {
        return new ArcStatementImpl(node);
      }
      else if (type == TOP_LEVEL_DECLARATION) {
        return new ArcTopLevelDeclarationImpl(node);
      }
      else if (type == TYPE) {
        return new ArcTypeImpl(node);
      }
      else if (type == TYPE_REF) {
        return new ArcTypeRefImpl(node);
      }
      else if (type == UNARY_MIN_EXPR) {
        return new ArcUnaryMinExprImpl(node);
      }
      else if (type == UNARY_NOT_EXPR) {
        return new ArcUnaryNotExprImpl(node);
      }
      else if (type == UNARY_PLUS_EXPR) {
        return new ArcUnaryPlusExprImpl(node);
      }
      else if (type == VALUE_EXPR) {
        return new ArcValueExprImpl(node);
      }
      else if (type == VALUE_NUMBER) {
        return new ArcValueNumberImpl(node);
      }
      else if (type == VALUE_STRING) {
        return new ArcValueStringImpl(node);
      }
      else if (type == VARIABLE_DECLARATION) {
        return new ArcVariableDeclarationImpl(node);
      }
      else if (type == VAR_ID) {
        return new ArcVarIdImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
