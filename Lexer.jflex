import java_cup.runtime.Symbol;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }
%}

/* Definições de padrões */
Digit = [0-9]
Letter = [a-zA-Z_]
Identifier = {Letter}({Letter}|{Digit})*
Number = {Digit}+("."{Digit}+)?

/* Declaração do estado STRING */
%state STRING

%%

"inteiro" { return symbol(sym.INTEIRO); }
"real" { return symbol(sym.REAL); }
"caracter" { return symbol(sym.CARACTER); }
"string" { return symbol(sym.TYPESTRING); }
"if" { return symbol(sym.IF); }
"else" { return symbol(sym.ELSE); }
"for" { return symbol(sym.FOR); }
"while" { return symbol(sym.WHILE); }
"printf" { return symbol(sym.PRINTF); }
"scanf" { return symbol(sym.SCANF); }
"=" { return symbol(sym.EQUAL); }
"+" { return symbol(sym.PLUS); }
"-" { return symbol(sym.MINUS); }
"*" { return symbol(sym.TIMES); }
"/" { return symbol(sym.DIVIDE); }
"==" { return symbol(sym.EQEQ); }
"!=" { return symbol(sym.NOTEQ); }
">" { return symbol(sym.GT); }
"<" { return symbol(sym.LT); }
">=" { return symbol(sym.GTE); }
"<=" { return symbol(sym.LTE); }
"&&" { return symbol(sym.AND); }
"||" { return symbol(sym.OR); }
"{" { return symbol(sym.LBRACE); }
"}" { return symbol(sym.RBRACE); }
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
";" { return symbol(sym.SEMI); }

{Identifier} { return symbol(sym.IDENTIFIER, yytext()); }
{Number} { return symbol(sym.NUMBER, yytext()); }

/* Transição para o estado STRING ao encontrar " */
\" { yybegin(STRING); string.setLength(0); }

<STRING> {
  \" { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString()); } // Captura o fim da string
  \\\" { string.append('\"'); }  // Captura a aspa dupla escapada
  \\ { string.append('\\'); }    // Captura a barra invertida escapada
  [^\"]+ { string.append(yytext()); } // Captura qualquer outro texto dentro da string
}
. { /* Ignora qualquer outro caractere */ }
