package com.github.idevelopthings.arc;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.idevelopthings.arc.psi.ArcTypes;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.github.idevelopthings.arc.psi.ArcTypes.*;

%%

%{
  public _ArcLexer() {
    this((java.io.Reader)null);
  }
%}

%{}
  // Dedicated storage for starting position of some previously successful match
  private int zzPostponedMarkedPos = -1;

  // Dedicated nested-comment level counter
  private int zzNestedCommentLevel = 0;
%}

%public
%class _ArcLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL_WS           = \n | \r | \r\n
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+






//WHITE_SPACE_CHAR = [\ \t\f]
//NEW_LINE = [\n\r]+
NL = \R
WS = [ \t\f]

LINE_COMMENT="//".*

VALUE_INTEGER=[0-9]+

ID=[a-zA-Z_][a-zA-Z_0-9]*

BLOCK_COMMENT_START = "/*"
BLOCK_COMMENT_END = "*/"
COMMENT_CONTENT = [^*] | [*]+[^*/]

DOUBLE_QUOUTE_STRING = "\"" (ESC | [^\"])*? ("\""|EOL_WS)
SINGLE_QUOUTE_STRING = "\'" (ESC | [^'])*? ("\'"|EOL_WS)
BACKTICK_STRING      = "`" [^`]* "`"


ESC = "\\" (["\\/"] | "b" | "f" | "n" | "r" | "t" | UNICODE)
UNICODE = "u" HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT

DECIMALS = [0-9] ("_"? [0-9])*

OCTAL_DIGIT = [0-7]
HEX_DIGIT = [0-9a-fA-F]
BIN_DIGIT = [0-1]
EXPONENT = [eE] [+-]? DECIMALS


//%s IN_BLOCK_COMMENT
%s BLOCK_COMMENT
//%s VALUE_FLOAT
//%s MAYBE_SEMICOLON
//%s ID_DECLARATION



%%
<YYINITIAL> {
    {BLOCK_COMMENT_START}    { yybegin(BLOCK_COMMENT); return ArcTypes.BLOCK_COMMENT; }
}
<BLOCK_COMMENT> {
  {COMMENT_CONTENT}        { return ArcTypes.BLOCK_COMMENT; }
  {BLOCK_COMMENT_END}      { yybegin(YYINITIAL); return ArcTypes.BLOCK_COMMENT; }
}
<YYINITIAL> {
    // {WHITE_SPACE_CHAR}+         { return com.intellij.psi.TokenType.WHITE_SPACE; }
    // {NEW_LINE}+                 { return com.intellij.psi.TokenType.WHITE_SPACE; }
    {LINE_COMMENT}              { return LINE_COMMENT; }
    {WHITE_SPACE}               { return com.intellij.psi.TokenType.WHITE_SPACE; }

    // Characters
    ":"                         { return COLON; }
    "::"                        { return COLONCOLON; }
    "."                         { return DOT; }
    ","                         { return COMMA; }
    ";"                         { return SEMICOLON; }
    "{"                         { return LCURLY; }
    "}"                         { return RCURLY; }
    "["                         { return LBRACK; }
    "]"                         { return RBRACK; }
    "("                         { return LPAREN; }
    ")"                         { return RPAREN; }
    ".."                        { return DOTDOT; }
    "..."                       { return DOTDOTDOT; }
    "[]"                        { return BRACKET_PAIR;}
    "?"                         { return QUESTION;}


    // Operators
    "+"                         { return PLUS; }
    "+="                        { return PLUSEQ; }
    "-"                         { return MINUS; }
    "-="                        { return MINUSEQ; }
    "*"                         { return MUL; }
    "*="                        { return MULEQ; }
    "/"                         { return DIV; }
    "/="                        { return DIVEQ; }
    "<"                         { return LANGLE; }
    ">"                         { return RANGLE; }
    "<="                        { return LE; }
    ">="                        { return GE; }
    "="                         { return EQ; }
    "=="                        { return EQEQ; }
    "!="                        { return NE; }
    "&&"                        { return AND; }
    "||"                        { return OR; }
    "!"                         { return NOT; }


    // Keywords
    "var"                       { return VAR_KW; }
    "func"                      { return FUNC_KW; }
    "extern"                    { return EXTERN_KW; }
    "object"                    { return OBJECT_KW; }
    "return"                    { return RETURN_KW; }
    "break"                     { return BREAK_KW; }
    "continue"                  { return CONTINUE_KW; }
    "if"                        { return IF_KW; }
    "else"                      { return ELSE_KW; }
    "for"                       { return FOR_KW; }
    "as"                        { return AS_KW; }
    "step"                      { return STEP_KW; }
    "import"                    { return IMPORT_KW; }
    "delete"                    { return DELETE_KW; }
    "enum"                      { return ENUM_KW; }
    "or"                        { return OR_KW; }
    "defer"                     { return DEFER_KW; }
    "http"                      { return HTTP_KW; }
    "route"                     { return HTTP_ROUTE_KW; }
    "from"                      { return HTTP_FROM_KW; }


    {DOUBLE_QUOUTE_STRING}      { return DOUBLE_QUOUTE_STRING; }
    {SINGLE_QUOUTE_STRING}      { return SINGLE_QUOUTE_STRING; }
    {BACKTICK_STRING}           { return BACKTICK_STRING; }

    "null" | "NULL"             { return VALUE_NULL; }
    "true" | "false"            { return VALUE_BOOL; }

    {VALUE_INTEGER}             { return VALUE_INTEGER; }

    {ID}                        { return ID; }

    "-"? [0-9]* "." [0-9]+ [eE][+\-]? [0-9]+ "f"  { return ArcTypes.VALUE_FLOAT; }
    "-"? [0-9]+ [eE][+\-]? [0-9]+ "f"             { return ArcTypes.VALUE_FLOAT; }
    "-"? [0-9]+ "f"                               { return ArcTypes.VALUE_FLOAT; }


//    "/*"                        { yybegin(IN_BLOCK_COMMENT); yypushback(2); }


     .                           { return BAD_CHARACTER; }
}


///////////////////////////////////////////////////////////////////////////////////////////////////
// Comments
///////////////////////////////////////////////////////////////////////////////////////////////////
//
//<IN_BLOCK_COMMENT> {
//  "/*"    { if (zzNestedCommentLevel++ == 0)
//              zzPostponedMarkedPos = zzStartRead;
//          }
//
//  "*/"    { if (--zzNestedCommentLevel == 0)
//              return ArcTypes.BLOCK_COMMENT;
//          }
//
//  <<EOF>> { zzNestedCommentLevel = 0; return ArcTypes.BLOCK_COMMENT; }
//
//  [^]     { }
//}


/*<MAYBE_SEMICOLON> {
    {WS}                                      { return com.intellij.psi.TokenType.WHITE_SPACE; }
    {NL}                                      { yybegin(YYINITIAL); yypushback(yytext().length()); return SEMICOLON_SYNTHETIC; }
    .                                         { yybegin(YYINITIAL); yypushback(yytext().length()); }
}*/

[^] { return BAD_CHARACTER; }
