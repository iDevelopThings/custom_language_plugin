// This is a generated file. Not intended for manual editing.
package com.github.idevelopthings.arc.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;
import static com.github.idevelopthings.arc.parser.ArcParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ArcParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return program(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(AND_EXPR, ARG_LIST_EXPR, ARRAY_ACCESS_EXPR, ASSIGN_EXPR,
      CALL_EXPR, DIV_EXPR, EQUALS_EXPR, EXPRESSION,
      GREATER_THAN_EXPR, GREATER_THAN_OR_EQUALS_EXPR, LESS_THAN_EXPR, LESS_THAN_OR_EQUALS_EXPR,
      MEMBER_ACCESS_EXPR, MEMBER_CALL_EXPR, MINUS_EXPR, MUL_EXPR,
      NOT_EQUALS_EXPR, OBJECT_INSTANTIATE_EXPR, OR_EXPR, PAREN_EXPR,
      PLUS_EXPR, RANGE_EXPR, SLICE_EXPR, UNARY_MIN_EXPR,
      UNARY_NOT_EXPR, UNARY_PLUS_EXPR, VALUE_EXPR),
  };

  /* ********************************************************** */
  // expression ("," expression)*
  public static boolean arg_list_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ARG_LIST_EXPR, "<arg list expr>");
    r = expression(b, l + 1, -1);
    r = r && arg_list_expr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ("," expression)*
  private static boolean arg_list_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arg_list_expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arg_list_expr_1", c)) break;
    }
    return true;
  }

  // "," expression
  private static boolean arg_list_expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID type
  public static boolean argument_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && type(b, l + 1);
    exit_section_(b, m, ARGUMENT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // "(" (argument_declaration ("," argument_declaration)*)? ")"
  public static boolean argument_declaration_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_list")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && argument_declaration_list_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ARGUMENT_DECLARATION_LIST, r);
    return r;
  }

  // (argument_declaration ("," argument_declaration)*)?
  private static boolean argument_declaration_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_list_1")) return false;
    argument_declaration_list_1_0(b, l + 1);
    return true;
  }

  // argument_declaration ("," argument_declaration)*
  private static boolean argument_declaration_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_declaration(b, l + 1);
    r = r && argument_declaration_list_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," argument_declaration)*
  private static boolean argument_declaration_list_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_list_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!argument_declaration_list_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argument_declaration_list_1_0_1", c)) break;
    }
    return true;
  }

  // "," argument_declaration
  private static boolean argument_declaration_list_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_list_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && argument_declaration(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // "{" statement* "}"
  public static boolean block_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_body")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_BODY, null);
    r = consumeToken(b, LCURLY);
    p = r; // pin = 1
    r = r && report_error_(b, block_body_1(b, l + 1));
    r = p && consumeToken(b, RCURLY) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // statement*
  private static boolean block_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_body_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_body_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '{' dictionary_fields? '}'
  public static boolean dictionary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && dictionary_1(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, DICTIONARY, r);
    return r;
  }

  // dictionary_fields?
  private static boolean dictionary_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_1")) return false;
    dictionary_fields(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (ID|value_string) ':' expression
  public static boolean dictionary_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DICTIONARY_FIELD, "<dictionary field>");
    r = dictionary_field_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID|value_string
  private static boolean dictionary_field_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_field_0")) return false;
    boolean r;
    r = consumeToken(b, ID);
    if (!r) r = value_string(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // dictionary_field (',' dictionary_field)* ','?
  public static boolean dictionary_fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_fields")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DICTIONARY_FIELDS, "<dictionary fields>");
    r = dictionary_field(b, l + 1);
    r = r && dictionary_fields_1(b, l + 1);
    r = r && dictionary_fields_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' dictionary_field)*
  private static boolean dictionary_fields_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_fields_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dictionary_fields_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dictionary_fields_1", c)) break;
    }
    return true;
  }

  // ',' dictionary_field
  private static boolean dictionary_fields_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_fields_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && dictionary_field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean dictionary_fields_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dictionary_fields_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // 'else' (block_body| if_statement)
  public static boolean else_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_statement")) return false;
    if (!nextTokenIs(b, ELSE_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KW);
    r = r && else_statement_1(b, l + 1);
    exit_section_(b, m, ELSE_STATEMENT, r);
    return r;
  }

  // block_body| if_statement
  private static boolean else_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_statement_1")) return false;
    boolean r;
    r = block_body(b, l + 1);
    if (!r) r = if_statement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (ID | range_expr) ('as' ID)? ('step' expression)?
  public static boolean for_loop_header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_LOOP_HEADER, "<for loop header>");
    r = for_loop_header_0(b, l + 1);
    r = r && for_loop_header_1(b, l + 1);
    r = r && for_loop_header_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID | range_expr
  private static boolean for_loop_header_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header_0")) return false;
    boolean r;
    r = consumeToken(b, ID);
    if (!r) r = expression(b, l + 1, 1);
    return r;
  }

  // ('as' ID)?
  private static boolean for_loop_header_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header_1")) return false;
    for_loop_header_1_0(b, l + 1);
    return true;
  }

  // 'as' ID
  private static boolean for_loop_header_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AS_KW, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('step' expression)?
  private static boolean for_loop_header_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header_2")) return false;
    for_loop_header_2_0(b, l + 1);
    return true;
  }

  // 'step' expression
  private static boolean for_loop_header_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_header_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STEP_KW);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'for' (for_loop_header block_body | block_body)
  public static boolean for_loop_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_statement")) return false;
    if (!nextTokenIs(b, FOR_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR_KW);
    r = r && for_loop_statement_1(b, l + 1);
    exit_section_(b, m, FOR_LOOP_STATEMENT, r);
    return r;
  }

  // for_loop_header block_body | block_body
  private static boolean for_loop_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = for_loop_statement_1_0(b, l + 1);
    if (!r) r = block_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // for_loop_header block_body
  private static boolean for_loop_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_loop_statement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = for_loop_header(b, l + 1);
    r = r && block_body(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FUNC_KW func_receiver_declaration? func_id argument_declaration_list type? block_body
  public static boolean func_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration")) return false;
    if (!nextTokenIs(b, FUNC_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNC_DECLARATION, null);
    r = consumeToken(b, FUNC_KW);
    p = r; // pin = 1
    r = r && report_error_(b, func_declaration_1(b, l + 1));
    r = p && report_error_(b, func_id(b, l + 1)) && r;
    r = p && report_error_(b, argument_declaration_list(b, l + 1)) && r;
    r = p && report_error_(b, func_declaration_4(b, l + 1)) && r;
    r = p && block_body(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // func_receiver_declaration?
  private static boolean func_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_1")) return false;
    func_receiver_declaration(b, l + 1);
    return true;
  }

  // type?
  private static boolean func_declaration_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_4")) return false;
    type(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // !('}' | FUNC_KW | OBJECT_KW)
  static boolean func_declaration_recovery_parser(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_recovery_parser")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !func_declaration_recovery_parser_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '}' | FUNC_KW | OBJECT_KW
  private static boolean func_declaration_recovery_parser_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_recovery_parser_0")) return false;
    boolean r;
    r = consumeToken(b, RCURLY);
    if (!r) r = consumeToken(b, FUNC_KW);
    if (!r) r = consumeToken(b, OBJECT_KW);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean func_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_id")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FUNC_ID, r);
    return r;
  }

  /* ********************************************************** */
  // "(" func_receiver_name type ")"
  public static boolean func_receiver_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_receiver_declaration")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && func_receiver_name(b, l + 1);
    r = r && type(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, FUNC_RECEIVER_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean func_receiver_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_receiver_name")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FUNC_RECEIVER_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // 'if' '(' expression ')' block_body else_statement?
  public static boolean if_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement")) return false;
    if (!nextTokenIs(b, IF_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF_KW, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    r = r && block_body(b, l + 1);
    r = r && if_statement_5(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // else_statement?
  private static boolean if_statement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_5")) return false;
    else_statement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'import' value_string ';'?
  public static boolean import_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement")) return false;
    if (!nextTokenIs(b, IMPORT_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT_KW);
    r = r && value_string(b, l + 1);
    r = r && import_statement_2(b, l + 1);
    exit_section_(b, m, IMPORT_STATEMENT, r);
    return r;
  }

  // ';'?
  private static boolean import_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement_2")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // '{' list_elements? '}'
  public static boolean list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && list_1(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, LIST, r);
    return r;
  }

  // list_elements?
  private static boolean list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_1")) return false;
    list_elements(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // expression (',' expression)* ','?
  public static boolean list_elements(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_elements")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LIST_ELEMENTS, "<list elements>");
    r = expression(b, l + 1, -1);
    r = r && list_elements_1(b, l + 1);
    r = r && list_elements_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' expression)*
  private static boolean list_elements_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_elements_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!list_elements_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "list_elements_1", c)) break;
    }
    return true;
  }

  // ',' expression
  private static boolean list_elements_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_elements_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean list_elements_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "list_elements_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // "{" object_field_declaration* "}"
  public static boolean object_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_body")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && object_body_1(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT_BODY, r);
    return r;
  }

  // object_field_declaration*
  private static boolean object_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_body_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_field_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_body_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // 'object' object_id object_body
  public static boolean object_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_declaration")) return false;
    if (!nextTokenIs(b, OBJECT_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OBJECT_KW);
    r = r && object_id(b, l + 1);
    r = r && object_body(b, l + 1);
    exit_section_(b, m, OBJECT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // object_field_key type
  public static boolean object_field_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_field_key(b, l + 1);
    r = r && type(b, l + 1);
    exit_section_(b, m, OBJECT_FIELD_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean object_field_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_key")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, OBJECT_FIELD_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean object_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_id")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, OBJECT_ID, r);
    return r;
  }

  /* ********************************************************** */
  // type "{" object_instantiate_fields "}"?
  public static boolean object_instantiate_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_expr")) return false;
    if (!nextTokenIs(b, "<object instantiate expr>", ID, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_INSTANTIATE_EXPR, "<object instantiate expr>");
    r = type(b, l + 1);
    r = r && consumeToken(b, LCURLY);
    r = r && object_instantiate_fields(b, l + 1);
    r = r && object_instantiate_expr_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // "}"?
  private static boolean object_instantiate_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_expr_3")) return false;
    consumeToken(b, RCURLY);
    return true;
  }

  /* ********************************************************** */
  // object_field_key ":" expression
  public static boolean object_instantiate_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_field")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_field_key(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, OBJECT_INSTANTIATE_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // object_instantiate_field ("," object_instantiate_field)* ','?
  public static boolean object_instantiate_fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_fields")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_instantiate_field(b, l + 1);
    r = r && object_instantiate_fields_1(b, l + 1);
    r = r && object_instantiate_fields_2(b, l + 1);
    exit_section_(b, m, OBJECT_INSTANTIATE_FIELDS, r);
    return r;
  }

  // ("," object_instantiate_field)*
  private static boolean object_instantiate_fields_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_fields_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_instantiate_fields_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_instantiate_fields_1", c)) break;
    }
    return true;
  }

  // "," object_instantiate_field
  private static boolean object_instantiate_fields_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_fields_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && object_instantiate_field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ','?
  private static boolean object_instantiate_fields_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_fields_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // top_level_declaration*
  static boolean program(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "program")) return false;
    while (true) {
      int c = current_position_(b);
      if (!top_level_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "program", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // 'return' expression ';'?
  public static boolean return_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_statement")) return false;
    if (!nextTokenIs(b, RETURN_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RETURN_STATEMENT, null);
    r = consumeToken(b, RETURN_KW);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && return_statement_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ';'?
  private static boolean return_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_statement_2")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // (expression? ":" expression)
  //     | (expression ":" expression?)
  //     | expression
  public static boolean slice_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SLICE_EXPR, "<slice expr>");
    r = slice_expr_0(b, l + 1);
    if (!r) r = slice_expr_1(b, l + 1);
    if (!r) r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // expression? ":" expression
  private static boolean slice_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = slice_expr_0_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean slice_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expr_0_0")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // expression ":" expression?
  private static boolean slice_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && consumeToken(b, COLON);
    r = r && slice_expr_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean slice_expr_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expr_1_2")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // if_statement
  //     | for_loop_statement
  //     | variable_declaration
  //     | return_statement
  //     | expression ";"?
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = if_statement(b, l + 1);
    if (!r) r = for_loop_statement(b, l + 1);
    if (!r) r = variable_declaration(b, l + 1);
    if (!r) r = return_statement(b, l + 1);
    if (!r) r = statement_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // expression ";"?
  private static boolean statement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && statement_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ";"?
  private static boolean statement_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_4_1")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // !<<eof>> import_statement* (object_declaration | func_declaration)
  public static boolean top_level_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TOP_LEVEL_DECLARATION, "<top level declaration>");
    r = top_level_declaration_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, top_level_declaration_1(b, l + 1));
    r = p && top_level_declaration_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, ArcParser::top_level_declaration_recovery_parser);
    return r || p;
  }

  // !<<eof>>
  private static boolean top_level_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !eof(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // import_statement*
  private static boolean top_level_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!import_statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "top_level_declaration_1", c)) break;
    }
    return true;
  }

  // object_declaration | func_declaration
  private static boolean top_level_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_2")) return false;
    boolean r;
    r = object_declaration(b, l + 1);
    if (!r) r = func_declaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(';' | 'object' | 'func')
  static boolean top_level_declaration_recovery_parser(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_recovery_parser")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !top_level_declaration_recovery_parser_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ';' | 'object' | 'func'
  private static boolean top_level_declaration_recovery_parser_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_recovery_parser_0")) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, OBJECT_KW);
    if (!r) r = consumeToken(b, FUNC_KW);
    return r;
  }

  /* ********************************************************** */
  // ('[' ']')? ID
  public static boolean type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type")) return false;
    if (!nextTokenIs(b, "<type>", ID, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE, "<type>");
    r = type_0(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ('[' ']')?
  private static boolean type_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_0")) return false;
    type_0_0(b, l + 1);
    return true;
  }

  // '[' ']'
  private static boolean type_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // VALUE_INTEGER | VALUE_FLOAT
  public static boolean value_number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_number")) return false;
    if (!nextTokenIs(b, "<value number>", VALUE_FLOAT, VALUE_INTEGER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_NUMBER, "<value number>");
    r = consumeToken(b, VALUE_INTEGER);
    if (!r) r = consumeToken(b, VALUE_FLOAT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOUBLE_QUOUTE_STRING | SINGLE_QUOUTE_STRING | BACKTICK_STRING
  public static boolean value_string(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_string")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_STRING, "<value string>");
    r = consumeToken(b, DOUBLE_QUOUTE_STRING);
    if (!r) r = consumeToken(b, SINGLE_QUOUTE_STRING);
    if (!r) r = consumeToken(b, BACKTICK_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean var_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_id")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, VAR_ID, r);
    return r;
  }

  /* ********************************************************** */
  // "var" var_id type? "=" expression ";"? {
  // //    pin=4
  //     // recoverWhile=variable_declaration_recovery_parser
  // }
  public static boolean variable_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration")) return false;
    if (!nextTokenIs(b, VAR_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR_KW);
    r = r && var_id(b, l + 1);
    r = r && variable_declaration_2(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && expression(b, l + 1, -1);
    r = r && variable_declaration_5(b, l + 1);
    r = r && variable_declaration_6(b, l + 1);
    exit_section_(b, m, VARIABLE_DECLARATION, r);
    return r;
  }

  // type?
  private static boolean variable_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_2")) return false;
    type(b, l + 1);
    return true;
  }

  // ";"?
  private static boolean variable_declaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_5")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  // {
  // //    pin=4
  //     // recoverWhile=variable_declaration_recovery_parser
  // }
  private static boolean variable_declaration_6(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: BINARY(assign_expr)
  // 1: ATOM(call_expr) POSTFIX(member_call_expr)
  // 2: BINARY(range_expr)
  // 3: BINARY(plus_expr) BINARY(minus_expr)
  // 4: BINARY(mul_expr) BINARY(div_expr)
  // 5: PREFIX(unary_plus_expr) PREFIX(unary_min_expr) PREFIX(unary_not_expr)
  // 6: POSTFIX(member_access_expr)
  // 7: BINARY(array_access_expr)
  // 8: ATOM(value_expr)
  // 9: PREFIX(paren_expr)
  // 10: BINARY(equals_expr) BINARY(not_equals_expr) BINARY(less_than_expr) BINARY(less_than_or_equals_expr)
  //    BINARY(greater_than_expr) BINARY(greater_than_or_equals_expr) BINARY(and_expr) BINARY(or_expr)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = call_expr(b, l + 1);
    if (!r) r = unary_plus_expr(b, l + 1);
    if (!r) r = unary_min_expr(b, l + 1);
    if (!r) r = unary_not_expr(b, l + 1);
    if (!r) r = value_expr(b, l + 1);
    if (!r) r = paren_expr(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && assign_expr_0(b, l + 1)) {
        r = expression(b, l, -1);
        exit_section_(b, l, m, ASSIGN_EXPR, r, true, null);
      }
      else if (g < 1 && leftMarkerIs(b, MEMBER_ACCESS_EXPR) && member_call_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_CALL_EXPR, r, true, null);
      }
      else if (g < 2 && leftMarkerIs(b, VALUE_EXPR) && consumeTokenSmart(b, DOTDOT)) {
        r = expression(b, l, 7);
        exit_section_(b, l, m, RANGE_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, PLUS)) {
        r = expression(b, l, 3);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, MINUS)) {
        r = expression(b, l, 3);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, MUL)) {
        r = expression(b, l, 4);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, DIV)) {
        r = expression(b, l, 4);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else if (g < 6 && member_access_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, MEMBER_ACCESS_EXPR, r, true, null);
      }
      else if (g < 7 && consumeTokenSmart(b, LBRACK)) {
        r = report_error_(b, expression(b, l, 7));
        r = consumeToken(b, RBRACK) && r;
        exit_section_(b, l, m, ARRAY_ACCESS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, EQEQ)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, EQUALS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, NE)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, NOT_EQUALS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, LANGLE)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, LESS_THAN_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, LE)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, LESS_THAN_OR_EQUALS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, RANGLE)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, GREATER_THAN_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, GE)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, GREATER_THAN_OR_EQUALS_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, AND)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, AND_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, OR)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, OR_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // '='|'+='|'-='|'*='|'/='
  private static boolean assign_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assign_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, EQ);
    if (!r) r = consumeTokenSmart(b, PLUSEQ);
    if (!r) r = consumeTokenSmart(b, MINUSEQ);
    if (!r) r = consumeTokenSmart(b, MULEQ);
    if (!r) r = consumeTokenSmart(b, DIVEQ);
    return r;
  }

  // ID ("(" arg_list_expr? ")")
  public static boolean call_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_expr")) return false;
    if (!nextTokenIsSmart(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ID);
    r = r && call_expr_1(b, l + 1);
    exit_section_(b, m, CALL_EXPR, r);
    return r;
  }

  // "(" arg_list_expr? ")"
  private static boolean call_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && call_expr_1_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // arg_list_expr?
  private static boolean call_expr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_expr_1_1")) return false;
    arg_list_expr(b, l + 1);
    return true;
  }

  // "(" arg_list_expr? ")"
  private static boolean member_call_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_call_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && member_call_expr_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // arg_list_expr?
  private static boolean member_call_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_call_expr_0_1")) return false;
    arg_list_expr(b, l + 1);
    return true;
  }

  public static boolean unary_plus_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plus_expr")) return false;
    if (!nextTokenIsSmart(b, PLUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, PLUS);
    p = r;
    r = p && expression(b, l, 5);
    exit_section_(b, l, m, UNARY_PLUS_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_min_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_min_expr")) return false;
    if (!nextTokenIsSmart(b, MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, MINUS);
    p = r;
    r = p && expression(b, l, 5);
    exit_section_(b, l, m, UNARY_MIN_EXPR, r, p, null);
    return r || p;
  }

  public static boolean unary_not_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_not_expr")) return false;
    if (!nextTokenIsSmart(b, NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, NOT);
    p = r;
    r = p && expression(b, l, 5);
    exit_section_(b, l, m, UNARY_NOT_EXPR, r, p, null);
    return r || p;
  }

  // (DOT | COLONCOLON) ID
  private static boolean member_access_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_access_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = member_access_expr_0_0(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT | COLONCOLON
  private static boolean member_access_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_access_expr_0_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, DOT);
    if (!r) r = consumeTokenSmart(b, COLONCOLON);
    return r;
  }

  // object_instantiate_expr
  //     | value_string
  //     | VALUE_NULL
  //     | VALUE_BOOL
  //     | value_number
  //     | ID
  //     | dictionary
  //     | list
  public static boolean value_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, VALUE_EXPR, "<value expr>");
    r = object_instantiate_expr(b, l + 1);
    if (!r) r = value_string(b, l + 1);
    if (!r) r = consumeTokenSmart(b, VALUE_NULL);
    if (!r) r = consumeTokenSmart(b, VALUE_BOOL);
    if (!r) r = value_number(b, l + 1);
    if (!r) r = consumeTokenSmart(b, ID);
    if (!r) r = dictionary(b, l + 1);
    if (!r) r = list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  public static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r;
    r = p && expression(b, l, 9);
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    exit_section_(b, l, m, PAREN_EXPR, r, p, null);
    return r || p;
  }

}
