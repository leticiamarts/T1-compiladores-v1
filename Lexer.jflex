import java_cup.runtime.Symbol;

%%

%class Lexer
%unicode

%cup
%line
%column

%{
     	StringBuffer string = new StringBuffer();
%}

%{
	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

Digito = [0-9]
Letra = [a-zA-Z]
Identificador = {Letra}+({Letra}|{Digito}|\_)*
Caracter = \"{Letra}\"


%%

"inteiro" { return symbol(sym.INTEIRO); }
"real" { return symbol(sym.REAL); }
"caractere" { return symbol(sym.CARACTER); }
"palavra" { return symbol(sym.STRING); }
"se" { return symbol(sym.IF); }
"entao" { return symbol(sym.ELSE); }
"para" { return symbol(sym.FOR); }
"enquanto" { return symbol(sym.WHILE); }
"printar" { return symbol(sym.PRINTF); }
"escanear" { return symbol(sym.SCANF); }
"=" { return symbol(sym.EQUAL); }
"!=" { return symbol(sym.DIFF); }
// ":" { return symbol(sym.ASSIGN); }
">" { return symbol(sym.GREATER); }
"<" { return symbol(sym.LESSTHAN); }
"<=" { return symbol(sym.LESSTHANEQ); }
">=" { return symbol(sym.GREATEREQ); }
"+" { return symbol(sym.PLUS); }
"-" { return symbol(sym.MINUS); }
"*" { return symbol(sym.TIMES); }
"/" { return symbol(sym.DIVIDE); }
"&&" { return symbol(sym.AND); }
"||" { return symbol(sym.OR); }
"{" { return symbol(sym.INICIO_BLOCO); }
"}" { return symbol(sym.FIM_BLOCO); }
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
";" { return symbol(sym.SEMI); }

{Identificador} { return symbol(sym.IDENTIFICADOR, new String(yytext())); }
\"([^\"\n\r\\]|\\.)*\" { return symbol(sym.STRING, yytext()); }
{Digito}+ { return symbol(sym.NUMERO, new Integer(yytext())); }
{Digito}+[.]{Digito}+ { return symbol(sym.REAL, new Float(yytext())); }
[ \n\t\r]+ { /* Ignorar espaços em branco */ }
. {  return symbol(sym.error, yytext()); }
