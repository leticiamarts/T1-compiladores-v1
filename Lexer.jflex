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

%state STRING

%%

<YYINITIAL> {

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
"==" { return symbol(sym.EQUAL); }
"!=" { return symbol(sym.DIFF); }
"=" { return symbol(sym.EQUAL); }
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

{Identificador} { return symbol(sym.IDENTIFICADOR); }
{Caracter} {return symbol(sym.CARACTER);}
{Digito}+ { return symbol(sym.NUMERO); }
{Digito}+[.]{Digito}+ { return symbol(sym.NUMERO); }
[ \n\t\r] { /* Ignorar espaços em branco */ }
}

<STRING> {
\" { yybegin(YYINITIAL); return symbol(sym.STRING); }
[^\n\r\"\\]+ { string.append(yytext()); }
\\t { string.append('\t'); }
\\n { string.append('\n'); }
\\r { string.append('\r'); }
\\\" { string.append('\"'); }
\\ { string.append('\\'); }
}
