package plic.analyse ;

import java_cup.runtime.*;
import plic.exceptions.AnalyseLexicaleException;
      
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

%state commentaire


chiffre = [0-9]
csteE = {chiffre}+
csteB = "vrai" | "faux"
char = [a-zA-Z]
charNum = {char} | {chiffre}
idf = {char}{charNum}*
statut = "publique" | "privee"
chaine = ["].*["]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaireSlashSlash = [/][/].*
commentaireSlashEtoile = [/][*]
commentaireEtoileSlash = [*][/]


%%

<YYINITIAL> "+"                	{ return symbol(CodesLexicaux.PLUS); }
<YYINITIAL> "-"                	{ return symbol(CodesLexicaux.MOINS); }
<YYINITIAL> "*"                	{ return symbol(CodesLexicaux.MULT); }
<YYINITIAL> "/"                	{ return symbol(CodesLexicaux.DIV); }
<YYINITIAL> ","					{ return symbol(CodesLexicaux.VIRGULE); }
<YYINITIAL> ";"					{ return symbol(CodesLexicaux.POINTVIRGULE); }

<YYINITIAL> ">"                	{ return symbol(CodesLexicaux.SUP); }
<YYINITIAL> "<"                	{ return symbol(CodesLexicaux.INF); }
<YYINITIAL> "=="                    { return symbol(CodesLexicaux.EGALEGAL); }
<YYINITIAL> "!="                    { return symbol(CodesLexicaux.DIFF); }

<YYINITIAL> "et"                	{ return symbol(CodesLexicaux.ET); }
<YYINITIAL> "ou"                	{ return symbol(CodesLexicaux.OU); }
<YYINITIAL> "non"                	{ return symbol(CodesLexicaux.NON); }

<YYINITIAL> "="                	{ return symbol(CodesLexicaux.EGAL); }

<YYINITIAL> "classe"				{ return symbol(CodesLexicaux.CLASS); }
<YYINITIAL> "entier"                { return symbol(CodesLexicaux.ENTIER); }
<YYINITIAL> "fin"					{ return symbol(CodesLexicaux.FIN); }
<YYINITIAL> "ecrire"				{ return symbol(CodesLexicaux.ECR); }

<YYINITIAL> "("                	{ return symbol(CodesLexicaux.PAROUV); }
<YYINITIAL> ")"                	{ return symbol(CodesLexicaux.PARFER); }

<YYINITIAL> {commentaireSlashSlash} {}

<YYINITIAL> {commentaireSlashEtoile} { yybegin(commentaire) ; }

<YYINITIAL> {csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
<YYINITIAL> {csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
<YYINITIAL> {chaine} 				{ return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }
<YYINITIAL> {statut} 				{ return symbol(CodesLexicaux.STATUT, yytext()); }
<YYINITIAL> {idf}  					{ return symbol(CodesLexicaux.IDF, yytext()); }
<YYINITIAL> {espace}                { }

<commentaire> {commentaireEtoileSlash} { yybegin(YYINITIAL) ; }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
