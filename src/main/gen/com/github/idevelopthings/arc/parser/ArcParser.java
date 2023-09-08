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
    boolean r;
    if (t == EXPRESSION) {
      r = expression(b, l + 1, -1);
    }
    else {
      r = program(b, l + 1);
    }
    return r;
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(TYPE, TYPE_REF),
    create_token_set_(ARRAY_ACCESS_EXPR, ASSIGN_EXPR, BOTH_SLICE_EXPR, CALL_EXPR,
      CONDITIONAL_EXPR, DIV_EXPR, EXPRESSION, INDEX_ACCESS_EXPR,
      LHS_SLICE_EXPR, MINUS_EXPR, MUL_EXPR, OBJECT_INSTANTIATE_EXPR,
      OR_ERROR_EXPR, PAREN_EXPR, PLUS_EXPR, RANGE_EXPR,
      REF_EXPR, RHS_SLICE_EXPR, SIMPLE_REF_EXPR, UNARY_MIN_EXPR,
      UNARY_NOT_EXPR, UNARY_PLUS_EXPR, VALUE_EXPR),
  };

  /* ********************************************************** */
  // FUNC_KW argument_declaration_list type_ref? block_body
  public static boolean anonymous_func(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymous_func")) return false;
    if (!nextTokenIs(b, FUNC_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ANONYMOUS_FUNC, null);
    r = consumeToken(b, FUNC_KW);
    p = r; // pin = 1
    r = r && report_error_(b, argument_declaration_list(b, l + 1));
    r = p && report_error_(b, anonymous_func_2(b, l + 1)) && r;
    r = p && block_body(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // type_ref?
  private static boolean anonymous_func_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "anonymous_func_2")) return false;
    type_ref(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '(' [ !')' expression  (',' expression) * ] ')'
  public static boolean arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARG_LIST, null);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, arg_list_1(b, l + 1));
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [ !')' expression  (',' expression) * ]
  private static boolean arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1")) return false;
    arg_list_1_0(b, l + 1);
    return true;
  }

  // !')' expression  (',' expression) *
  private static boolean arg_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = arg_list_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && arg_list_1_0_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !')'
  private static boolean arg_list_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' expression) *
  private static boolean arg_list_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arg_list_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arg_list_1_0_2", c)) break;
    }
    return true;
  }

  // ',' expression
  private static boolean arg_list_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg_list_1_0_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (argument_id type_ref)|(argument_id '...' type_ref)
  public static boolean argument_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_declaration_0(b, l + 1);
    if (!r) r = argument_declaration_1(b, l + 1);
    exit_section_(b, m, ARGUMENT_DECLARATION, r);
    return r;
  }

  // argument_id type_ref
  private static boolean argument_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_id(b, l + 1);
    r = r && type_ref(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // argument_id '...' type_ref
  private static boolean argument_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_declaration_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_id(b, l + 1);
    r = r && consumeToken(b, DOTDOTDOT);
    r = r && type_ref(b, l + 1);
    exit_section_(b, m, null, r);
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
  // ID
  public static boolean argument_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_id")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ARGUMENT_ID, r);
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
  // "[" expression ":" expression "]"
  public static boolean both_slice_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "both_slice_expr")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, BOTH_SLICE_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // 'defer' (anonymous_func | block_body)
  public static boolean defer_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defer_statement")) return false;
    if (!nextTokenIs(b, DEFER_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFER_KW);
    r = r && defer_statement_1(b, l + 1);
    exit_section_(b, m, DEFER_STATEMENT, r);
    return r;
  }

  // anonymous_func | block_body
  private static boolean defer_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "defer_statement_1")) return false;
    boolean r;
    r = anonymous_func(b, l + 1);
    if (!r) r = block_body(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // 'delete' expression semi?
  public static boolean delete_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "delete_statement")) return false;
    if (!nextTokenIs(b, DELETE_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DELETE_STATEMENT, null);
    r = consumeToken(b, DELETE_KW);
    p = r; // pin = 1
    r = r && report_error_(b, expression(b, l + 1, -1));
    r = p && delete_statement_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // semi?
  private static boolean delete_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "delete_statement_2")) return false;
    semi(b, l + 1);
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
  // !('if' '{') block_body | if_statement
  static boolean else_chain(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_chain")) return false;
    if (!nextTokenIs(b, "", IF_KW, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = else_chain_0(b, l + 1);
    if (!r) r = if_statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !('if' '{') block_body
  private static boolean else_chain_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_chain_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = else_chain_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && block_body(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !('if' '{')
  private static boolean else_chain_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_chain_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !else_chain_0_0_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // 'if' '{'
  private static boolean else_chain_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_chain_0_0_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 1, IF_KW, LCURLY);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // 'else' else_chain
  public static boolean else_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_statement")) return false;
    if (!nextTokenIs(b, ELSE_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE_KW);
    r = r && else_chain(b, l + 1);
    exit_section_(b, m, ELSE_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // 'enum' enum_id '{' enum_fields? '}'
  public static boolean enum_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_declaration")) return false;
    if (!nextTokenIs(b, ENUM_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENUM_KW);
    r = r && enum_id(b, l + 1);
    r = r && consumeToken(b, LCURLY);
    r = r && enum_declaration_3(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, ENUM_DECLARATION, r);
    return r;
  }

  // enum_fields?
  private static boolean enum_declaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_declaration_3")) return false;
    enum_fields(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (enum_value|enum_value_ctor) semi?
  public static boolean enum_field_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_declaration")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enum_field_declaration_0(b, l + 1);
    r = r && enum_field_declaration_1(b, l + 1);
    exit_section_(b, m, ENUM_FIELD_DECLARATION, r);
    return r;
  }

  // enum_value|enum_value_ctor
  private static boolean enum_field_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_declaration_0")) return false;
    boolean r;
    r = enum_value(b, l + 1);
    if (!r) r = enum_value_ctor(b, l + 1);
    return r;
  }

  // semi?
  private static boolean enum_field_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_field_declaration_1")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // enum_field_declaration*
  static boolean enum_fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_fields")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enum_field_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enum_fields", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID
  public static boolean enum_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_id")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, ENUM_ID, r);
    return r;
  }

  /* ********************************************************** */
  // ID '=' value_expr
  static boolean enum_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, EQ);
    r = r && value_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ID enum_value_ctor_arg_list
  static boolean enum_value_ctor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && enum_value_ctor_arg_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (ID? type_ref) | type_ref
  public static boolean enum_value_ctor_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_VALUE_CTOR_ARG, "<enum value ctor arg>");
    r = enum_value_ctor_arg_0(b, l + 1);
    if (!r) r = type_ref(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID? type_ref
  private static boolean enum_value_ctor_arg_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enum_value_ctor_arg_0_0(b, l + 1);
    r = r && type_ref(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID?
  private static boolean enum_value_ctor_arg_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_0_0")) return false;
    consumeToken(b, ID);
    return true;
  }

  /* ********************************************************** */
  // "(" (enum_value_ctor_arg ("," enum_value_ctor_arg)*)? ")"
  public static boolean enum_value_ctor_arg_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_list")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && enum_value_ctor_arg_list_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ENUM_VALUE_CTOR_ARG_LIST, r);
    return r;
  }

  // (enum_value_ctor_arg ("," enum_value_ctor_arg)*)?
  private static boolean enum_value_ctor_arg_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_list_1")) return false;
    enum_value_ctor_arg_list_1_0(b, l + 1);
    return true;
  }

  // enum_value_ctor_arg ("," enum_value_ctor_arg)*
  private static boolean enum_value_ctor_arg_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enum_value_ctor_arg(b, l + 1);
    r = r && enum_value_ctor_arg_list_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("," enum_value_ctor_arg)*
  private static boolean enum_value_ctor_arg_list_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_list_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enum_value_ctor_arg_list_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enum_value_ctor_arg_list_1_0_1", c)) break;
    }
    return true;
  }

  // "," enum_value_ctor_arg
  private static boolean enum_value_ctor_arg_list_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enum_value_ctor_arg_list_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && enum_value_ctor_arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EXTERN_KW FUNC_KW func_receiver_declaration? func_id argument_declaration_list type_ref
  public static boolean external_func_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "external_func_declaration")) return false;
    if (!nextTokenIs(b, EXTERN_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXTERNAL_FUNC_DECLARATION, null);
    r = consumeTokens(b, 2, EXTERN_KW, FUNC_KW);
    p = r; // pin = 2
    r = r && report_error_(b, external_func_declaration_2(b, l + 1));
    r = p && report_error_(b, func_id(b, l + 1)) && r;
    r = p && report_error_(b, argument_declaration_list(b, l + 1)) && r;
    r = p && type_ref(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // func_receiver_declaration?
  private static boolean external_func_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "external_func_declaration_2")) return false;
    func_receiver_declaration(b, l + 1);
    return true;
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
  // !EXTERN_KW FUNC_KW func_receiver_declaration? func_id argument_declaration_list type_ref? block_body
  public static boolean func_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration")) return false;
    if (!nextTokenIs(b, FUNC_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FUNC_DECLARATION, null);
    r = func_declaration_0(b, l + 1);
    r = r && consumeToken(b, FUNC_KW);
    p = r; // pin = 2
    r = r && report_error_(b, func_declaration_2(b, l + 1));
    r = p && report_error_(b, func_id(b, l + 1)) && r;
    r = p && report_error_(b, argument_declaration_list(b, l + 1)) && r;
    r = p && report_error_(b, func_declaration_5(b, l + 1)) && r;
    r = p && block_body(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !EXTERN_KW
  private static boolean func_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, EXTERN_KW);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // func_receiver_declaration?
  private static boolean func_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_2")) return false;
    func_receiver_declaration(b, l + 1);
    return true;
  }

  // type_ref?
  private static boolean func_declaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_declaration_5")) return false;
    type_ref(b, l + 1);
    return true;
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
  // "(" func_receiver_name? type ")"
  public static boolean func_receiver_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_receiver_declaration")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && func_receiver_declaration_1(b, l + 1);
    r = r && type(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, FUNC_RECEIVER_DECLARATION, r);
    return r;
  }

  // func_receiver_name?
  private static boolean func_receiver_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "func_receiver_declaration_1")) return false;
    func_receiver_name(b, l + 1);
    return true;
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
  // 'http' block_body
  public static boolean http_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_block")) return false;
    if (!nextTokenIs(b, HTTP_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HTTP_KW);
    r = r && block_body(b, l + 1);
    exit_section_(b, m, HTTP_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // 'from' ('body'|'query'|'route') 'as' var_id type_ref semi?
  public static boolean http_body_injection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_body_injection")) return false;
    if (!nextTokenIs(b, HTTP_FROM_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HTTP_FROM_KW);
    r = r && http_body_injection_1(b, l + 1);
    r = r && consumeToken(b, AS_KW);
    r = r && var_id(b, l + 1);
    r = r && type_ref(b, l + 1);
    r = r && http_body_injection_5(b, l + 1);
    exit_section_(b, m, HTTP_BODY_INJECTION, r);
    return r;
  }

  // 'body'|'query'|'route'
  private static boolean http_body_injection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_body_injection_1")) return false;
    boolean r;
    r = consumeToken(b, "body");
    if (!r) r = consumeToken(b, "query");
    if (!r) r = consumeToken(b, HTTP_ROUTE_KW);
    return r;
  }

  // semi?
  private static boolean http_body_injection_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_body_injection_5")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'route' ('GET'|'POST'|'PUT'|'DELETE'|'PATCH') value_string block_body
  public static boolean http_route_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_route_declaration")) return false;
    if (!nextTokenIs(b, HTTP_ROUTE_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HTTP_ROUTE_KW);
    r = r && http_route_declaration_1(b, l + 1);
    r = r && value_string(b, l + 1);
    r = r && block_body(b, l + 1);
    exit_section_(b, m, HTTP_ROUTE_DECLARATION, r);
    return r;
  }

  // 'GET'|'POST'|'PUT'|'DELETE'|'PATCH'
  private static boolean http_route_declaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "http_route_declaration_1")) return false;
    boolean r;
    r = consumeToken(b, "GET");
    if (!r) r = consumeToken(b, "POST");
    if (!r) r = consumeToken(b, "PUT");
    if (!r) r = consumeToken(b, "DELETE");
    if (!r) r = consumeToken(b, "PATCH");
    return r;
  }

  /* ********************************************************** */
  // 'if' ((!'(' expression)|(&'(' paren_expr)) block_body else_statement?
  public static boolean if_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement")) return false;
    if (!nextTokenIs(b, IF_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF_KW);
    r = r && if_statement_1(b, l + 1);
    r = r && block_body(b, l + 1);
    r = r && if_statement_3(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // (!'(' expression)|(&'(' paren_expr)
  private static boolean if_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_statement_1_0(b, l + 1);
    if (!r) r = if_statement_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !'(' expression
  private static boolean if_statement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_statement_1_0_0(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !'('
  private static boolean if_statement_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, LPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // &'(' paren_expr
  private static boolean if_statement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_statement_1_1_0(b, l + 1);
    r = r && paren_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &'('
  private static boolean if_statement_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, LPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // else_statement?
  private static boolean if_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_3")) return false;
    else_statement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'import' value_string semi?
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

  // semi?
  private static boolean import_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement_2")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "[" expression "]"
  public static boolean index_access_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "index_access_expr")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, INDEX_ACCESS_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // "[" expression ":" "]"
  public static boolean lhs_slice_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lhs_slice_expr")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && expression(b, l + 1, -1);
    r = r && consumeTokens(b, 0, COLON, RBRACK);
    exit_section_(b, m, LHS_SLICE_EXPR, r);
    return r;
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
  // 'object' object_id '{' object_fields? '}'
  public static boolean object_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_declaration")) return false;
    if (!nextTokenIs(b, OBJECT_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OBJECT_KW);
    r = r && object_id(b, l + 1);
    r = r && consumeToken(b, LCURLY);
    r = r && object_declaration_3(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT_DECLARATION, r);
    return r;
  }

  // object_fields?
  private static boolean object_declaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_declaration_3")) return false;
    object_fields(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // !'}' object_field_key type_ref
  public static boolean object_field_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_FIELD_DECLARATION, "<object field declaration>");
    r = object_field_declaration_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, object_field_key(b, l + 1));
    r = p && type_ref(b, l + 1) && r;
    exit_section_(b, l, m, r, p, ArcParser::object_field_declaration_recover);
    return r || p;
  }

  // !'}'
  private static boolean object_field_declaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, RCURLY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !('}' | (object_field_key type_ref))
  static boolean object_field_declaration_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !object_field_declaration_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '}' | (object_field_key type_ref)
  private static boolean object_field_declaration_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RCURLY);
    if (!r) r = object_field_declaration_recover_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // object_field_key type_ref
  private static boolean object_field_declaration_recover_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_field_declaration_recover_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_field_key(b, l + 1);
    r = r && type_ref(b, l + 1);
    exit_section_(b, m, null, r);
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
  // object_field_declaration*
  static boolean object_fields(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_fields")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_field_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_fields", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // <<setIdentMode 'IdentMode.ON'>> <<parseIdentifier>> <<setIdentMode 'IdentMode.OFF'>>
  public static boolean object_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_id")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_ID, "<object id>");
    r = setIdentMode(b, l + 1, IdentMode.ON);
    r = r && parseIdentifier(b, l + 1);
    r = r && setIdentMode(b, l + 1, IdentMode.OFF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // type "{" object_instantiate_fields "}"?
  public static boolean object_instantiate_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_instantiate_expr")) return false;
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
  // top_level_declarations
  static boolean program(PsiBuilder b, int l) {
    return top_level_declarations(b, l + 1);
  }

  /* ********************************************************** */
  // !('.'|'::'|'}' | ',' | ';'|'[' | '<NL>')
  static boolean ref_expr_recovery_parser(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ref_expr_recovery_parser")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !ref_expr_recovery_parser_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '.'|'::'|'}' | ',' | ';'|'[' | '<NL>'
  private static boolean ref_expr_recovery_parser_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ref_expr_recovery_parser_0")) return false;
    boolean r;
    r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, COLONCOLON);
    if (!r) r = consumeToken(b, RCURLY);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, LBRACK);
    if (!r) r = consumeToken(b, SEMICOLON_SYNTHETIC);
    return r;
  }

  /* ********************************************************** */
  // 'return' [expression] semi?
  public static boolean return_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_statement")) return false;
    if (!nextTokenIs(b, RETURN_KW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RETURN_STATEMENT, null);
    r = consumeToken(b, RETURN_KW);
    p = r; // pin = 1
    r = r && report_error_(b, return_statement_1(b, l + 1));
    r = p && return_statement_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [expression]
  private static boolean return_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_statement_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // semi?
  private static boolean return_statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "return_statement_2")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "[" ":" expression "]"
  public static boolean rhs_slice_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rhs_slice_expr")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACK, COLON);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, RHS_SLICE_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // '<NL>'| ',' | ';' | <<eof>>
  static boolean semi(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "semi")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON_SYNTHETIC);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // rhs_slice_expr | lhs_slice_expr | both_slice_expr | index_access_expr
  static boolean slice_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_group")) return false;
    if (!nextTokenIs(b, LBRACK)) return false;
    boolean r;
    r = rhs_slice_expr(b, l + 1);
    if (!r) r = lhs_slice_expr(b, l + 1);
    if (!r) r = both_slice_expr(b, l + 1);
    if (!r) r = index_access_expr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // if_statement
  //     | http_body_injection
  //     | for_loop_statement
  //     | variable_declaration
  //     | return_statement
  //     | delete_statement
  //     | defer_statement
  //     | http_route_declaration
  //     | expression semi?
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = if_statement(b, l + 1);
    if (!r) r = http_body_injection(b, l + 1);
    if (!r) r = for_loop_statement(b, l + 1);
    if (!r) r = variable_declaration(b, l + 1);
    if (!r) r = return_statement(b, l + 1);
    if (!r) r = delete_statement(b, l + 1);
    if (!r) r = defer_statement(b, l + 1);
    if (!r) r = http_route_declaration(b, l + 1);
    if (!r) r = statement_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // expression semi?
  private static boolean statement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && statement_8_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // semi?
  private static boolean statement_8_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement_8_1")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // !<<eof>> import_statement* (object_declaration | func_declaration | external_func_declaration | enum_declaration | http_block)
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

  // object_declaration | func_declaration | external_func_declaration | enum_declaration | http_block
  private static boolean top_level_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_2")) return false;
    boolean r;
    r = object_declaration(b, l + 1);
    if (!r) r = func_declaration(b, l + 1);
    if (!r) r = external_func_declaration(b, l + 1);
    if (!r) r = enum_declaration(b, l + 1);
    if (!r) r = http_block(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(';' | 'object' | 'func' | 'enum' | 'or' | 'import' | 'http' | 'extern')
  static boolean top_level_declaration_recovery_parser(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_recovery_parser")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !top_level_declaration_recovery_parser_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ';' | 'object' | 'func' | 'enum' | 'or' | 'import' | 'http' | 'extern'
  private static boolean top_level_declaration_recovery_parser_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declaration_recovery_parser_0")) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, OBJECT_KW);
    if (!r) r = consumeToken(b, FUNC_KW);
    if (!r) r = consumeToken(b, ENUM_KW);
    if (!r) r = consumeToken(b, OR_KW);
    if (!r) r = consumeToken(b, IMPORT_KW);
    if (!r) r = consumeToken(b, HTTP_KW);
    if (!r) r = consumeToken(b, EXTERN_KW);
    return r;
  }

  /* ********************************************************** */
  // top_level_declaration*
  static boolean top_level_declarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "top_level_declarations")) return false;
    while (true) {
      int c = current_position_(b);
      if (!top_level_declaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "top_level_declarations", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // <<setIdentMode 'IdentMode.ON'>> <<parseIdentifier>> <<setIdentMode 'IdentMode.OFF'>>
  public static boolean type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE, "<type>");
    r = setIdentMode(b, l + 1, IdentMode.ON);
    r = r && parseIdentifier(b, l + 1);
    r = r && setIdentMode(b, l + 1, IdentMode.OFF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '[]' type
  static boolean type_array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_array")) return false;
    if (!nextTokenIs(b, BRACKET_PAIR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_PAIR);
    r = r && type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '?' type
  static boolean type_option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_option")) return false;
    if (!nextTokenIs(b, QUESTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUESTION);
    r = r && type(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // type | type_array | type_option | type_result
  public static boolean type_ref(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_ref")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, TYPE_REF, "<type ref>");
    r = type(b, l + 1);
    if (!r) r = type_array(b, l + 1);
    if (!r) r = type_option(b, l + 1);
    if (!r) r = type_result(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '!' type
  static boolean type_result(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_result")) return false;
    if (!nextTokenIs(b, NOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NOT);
    r = r && type(b, l + 1);
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
  // "var" var_id [type_ref] (("=" expression semi?)|semi?)
  public static boolean variable_declaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration")) return false;
    if (!nextTokenIs(b, VAR_KW)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VAR_KW);
    r = r && var_id(b, l + 1);
    r = r && variable_declaration_2(b, l + 1);
    r = r && variable_declaration_3(b, l + 1);
    exit_section_(b, m, VARIABLE_DECLARATION, r);
    return r;
  }

  // [type_ref]
  private static boolean variable_declaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_2")) return false;
    type_ref(b, l + 1);
    return true;
  }

  // ("=" expression semi?)|semi?
  private static boolean variable_declaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variable_declaration_3_0(b, l + 1);
    if (!r) r = variable_declaration_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // "=" expression semi?
  private static boolean variable_declaration_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && expression(b, l + 1, -1);
    r = r && variable_declaration_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // semi?
  private static boolean variable_declaration_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_3_0_2")) return false;
    semi(b, l + 1);
    return true;
  }

  // semi?
  private static boolean variable_declaration_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable_declaration_3_1")) return false;
    semi(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: BINARY(assign_expr)
  // 1: BINARY(conditional_expr)
  // 2: BINARY(range_expr)
  // 3: BINARY(plus_expr) BINARY(minus_expr)
  // 4: BINARY(mul_expr) BINARY(div_expr)
  // 5: PREFIX(unary_plus_expr) PREFIX(unary_min_expr) PREFIX(unary_not_expr)
  // 6: POSTFIX(qualification_expr)
  // 7: POSTFIX(global_call_expr) POSTFIX(member_call_expr)
  // 8: ATOM(value_expr) POSTFIX(array_access_expr) ATOM(simple_ref_expr) PREFIX(paren_expr)
  // 9: POSTFIX(or_error_expr)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = unary_plus_expr(b, l + 1);
    if (!r) r = unary_min_expr(b, l + 1);
    if (!r) r = unary_not_expr(b, l + 1);
    if (!r) r = value_expr(b, l + 1);
    if (!r) r = simple_ref_expr(b, l + 1);
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
      else if (g < 1 && conditional_expr_0(b, l + 1)) {
        r = expression(b, l, 1);
        exit_section_(b, l, m, CONDITIONAL_EXPR, r, true, null);
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
      else if (g < 6 && qualification_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, REF_EXPR, r, true, null);
      }
      else if (g < 7 && leftMarkerIs(b, SIMPLE_REF_EXPR) && arg_list(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CALL_EXPR, r, true, null);
      }
      else if (g < 7 && leftMarkerIs(b, REF_EXPR) && member_call_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, CALL_EXPR, r, true, null);
      }
      else if (g < 8 && slice_group(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, ARRAY_ACCESS_EXPR, r, true, null);
      }
      else if (g < 9 && or_error_expr_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, OR_ERROR_EXPR, r, true, null);
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

  // '<' | '>' | '<=' | '>=' | '==' | '!=' | '&&' | '||'
  private static boolean conditional_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "conditional_expr_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, LANGLE);
    if (!r) r = consumeTokenSmart(b, RANGLE);
    if (!r) r = consumeTokenSmart(b, LE);
    if (!r) r = consumeTokenSmart(b, GE);
    if (!r) r = consumeTokenSmart(b, EQEQ);
    if (!r) r = consumeTokenSmart(b, NE);
    if (!r) r = consumeTokenSmart(b, AND);
    if (!r) r = consumeTokenSmart(b, OR);
    return r;
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

  // ('.'|'::') ID
  private static boolean qualification_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualification_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qualification_expr_0_0(b, l + 1);
    r = r && consumeToken(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // '.'|'::'
  private static boolean qualification_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualification_expr_0_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, DOT);
    if (!r) r = consumeTokenSmart(b, COLONCOLON);
    return r;
  }

  // !<<isBuiltin>> arg_list
  private static boolean member_call_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_call_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = member_call_expr_0_0(b, l + 1);
    r = r && arg_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<isBuiltin>>
  private static boolean member_call_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_call_expr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !isBuiltin(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // object_instantiate_expr
  //     | value_string
  //     | VALUE_NULL
  //     | VALUE_BOOL
  //     | value_number
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
    if (!r) r = dictionary(b, l + 1);
    if (!r) r = list(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID
  public static boolean simple_ref_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_ref_expr")) return false;
    if (!nextTokenIsSmart(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ID);
    exit_section_(b, m, SIMPLE_REF_EXPR, r);
    return r;
  }

  public static boolean paren_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paren_expr")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r;
    r = p && expression(b, l, 8);
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    exit_section_(b, l, m, PAREN_EXPR, r, p, null);
    return r || p;
  }

  // 'or' (block_body | expression)
  private static boolean or_error_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "or_error_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, OR_KW);
    r = r && or_error_expr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // block_body | expression
  private static boolean or_error_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "or_error_expr_0_1")) return false;
    boolean r;
    r = block_body(b, l + 1);
    if (!r) r = expression(b, l + 1, -1);
    return r;
  }

}
