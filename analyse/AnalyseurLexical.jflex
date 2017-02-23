package plic.analyse ;

import java_cup.runtime.*;
import plic.arbre.* ;
import plic.exceptions.*;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}



chiffre = [0-9]
csteE = {chiffre}+
csteB = "vrai" | "faux"
char = [a-zA-Z]
charNum = {char} | {chiffre}
idf = {char}+
statut = "publique" | "privee"
type = "entier"

doubleguill = \"\"
charguill = {doubleguill} | [^\"]
chaine = \" {charguill}* \"

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaireSlashSlash = [/][/].*

%%

"+"                	{ return symbol(CodesLexicaux.PLUS); }
"-"                	{ return symbol(CodesLexicaux.MOINS); }
"*"                	{ return symbol(CodesLexicaux.MULT); }
"/"                	{ return symbol(CodesLexicaux.DIV); }
","					{ return symbol(CodesLexicaux.VIRGULE); }
";"					{ return symbol(CodesLexicaux.POINTVIRGULE); }

">"                	{ return symbol(CodesLexicaux.SUP); }
"<"                	{ return symbol(CodesLexicaux.INF); }
"=="                    { return symbol(CodesLexicaux.EGALEGAL); }
"!="                    { return symbol(CodesLexicaux.DIFF); }

"et"                	{ return symbol(CodesLexicaux.ET); }
"ou"                	{ return symbol(CodesLexicaux.OU); }
"non"                	{ return symbol(CodesLexicaux.NON); }

"="                	{ return symbol(CodesLexicaux.EGAL); }

"classe"				{ return symbol(CodesLexicaux.CLASS); }
"fin"					{ return symbol(CodesLexicaux.FIN); }
"ecrire"				{ return symbol(CodesLexicaux.ECR); }

"("                	{ return symbol(CodesLexicaux.PAROUV); }
")"                	{ return symbol(CodesLexicaux.PARFER); }

{commentaireSlashSlash} {}


{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{chaine} 				{ return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }
{statut} 				{ return symbol(CodesLexicaux.STATUT, yytext()); }
{type}					{ return symbol(CodesLexicaux.TYPE, yytext()); }
{idf}  					{ return symbol(CodesLexicaux.IDF, yytext()); }
{espace}                { }


. { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
