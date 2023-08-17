package com.github.idevelopthings.customlanguageplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes;
import com.github.idevelopthings.customlanguageplugin.psi.DataFusionTokenSets;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.github.idevelopthings.customlanguageplugin.psi.DataFusionTypes.*;

%%

%{
  public _DataFusionLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _DataFusionLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE_CHAR = [\ \t\f]
NEW_LINE = [\n\r]+

LINE_COMMENT="//".*

VALUE_INTEGER=[0-9]+

ID=[a-zA-Z_][a-zA-Z_0-9]*

BLOCK_COMMENT_START = "/*"
BLOCK_COMMENT_END = "*/"
COMMENT_CONTENT = [^*] | [*]+[^*/]

DOUBLE_QUOUTE_STRING = "\"" (ESC | [^\"])*? "\""
SINGLE_QUOUTE_STRING = "\'" (ESC | [^'])*? "\'"
BACKTICK_STRING      = "`" [^`]* "`"


ESC = "\\" (["\\/"] | "b" | "f" | "n" | "r" | "t" | UNICODE)
UNICODE = "u" HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT

DECIMALS = [0-9] ("_"? [0-9])*

OCTAL_DIGIT = [0-7]
HEX_DIGIT = [0-9a-fA-F]
BIN_DIGIT = [0-1]
EXPONENT = [eE] [+-]? DECIMALS


%state BLOCK_COMMENT, VALUE_FLOAT


%%

<YYINITIAL> {
  {BLOCK_COMMENT_START}    { yybegin(BLOCK_COMMENT); return DataFusionTypes.BLOCK_COMMENT; }
}
<BLOCK_COMMENT> {
  {COMMENT_CONTENT}        { return DataFusionTypes.BLOCK_COMMENT; }
  {BLOCK_COMMENT_END}      { yybegin(YYINITIAL); return DataFusionTypes.BLOCK_COMMENT; }
}

<YYINITIAL> {
    {WHITE_SPACE_CHAR}+         { return com.intellij.psi.TokenType.WHITE_SPACE; }
    {NEW_LINE}+                 { return com.intellij.psi.TokenType.WHITE_SPACE; }

    {LINE_COMMENT}              { return LINE_COMMENT; }

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


    {DOUBLE_QUOUTE_STRING}      { return DOUBLE_QUOUTE_STRING; }
    {SINGLE_QUOUTE_STRING}      { return SINGLE_QUOUTE_STRING; }
    {BACKTICK_STRING}           { return BACKTICK_STRING; }

    "null" | "NULL"             { return VALUE_NULL; }
    "true" | "false"            { return VALUE_BOOL; }

    {VALUE_INTEGER}             { return VALUE_INTEGER; }

    "-"? [0-9]* "." [0-9]+ [eE][+\-]? [0-9]+ "f"  { return DataFusionTypes.VALUE_FLOAT; }
    "-"? [0-9]+ [eE][+\-]? [0-9]+ "f"             { return DataFusionTypes.VALUE_FLOAT; }
    "-"? [0-9]+ "f"                               { return DataFusionTypes.VALUE_FLOAT; }

    {ID}                        { return ID; }

    .                           { return BAD_CHARACTER; }
}

[^] { return BAD_CHARACTER; }
