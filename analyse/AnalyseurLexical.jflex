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

csteE = [0-9]+
csteB = "vrai" | "faux"
idf = [a-zA-Z]+
statut = "publique" | "privee"

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

<YYINITIAL> "=="                    { return symbol(CodesLexicaux.EGALEGAL); }
<YYINITIAL> "!="                    { return symbol(CodesLexicaux.DIFF); }
<YYINITIAL> "<"                	{ return symbol(CodesLexicaux.INF); }
<YYINITIAL> ">"                	{ return symbol(CodesLexicaux.SUP); }
<YYINITIAL> "="                	{ return symbol(CodesLexicaux.EGAL); }

<YYINITIAL> "et"                	{ return symbol(CodesLexicaux.ET); }
<YYINITIAL> "ou"                	{ return symbol(CodesLexicaux.OU); }
<YYINITIAL> "non"                	{ return symbol(CodesLexicaux.NON); }

<YYINITIAL> "classe"				{ return symbol(CodesLexicaux.CLASS); }
<YYINITIAL> "entier"                { return symbol(CodesLexicaux.ENTIER); }
<YYINITIAL> "fin"					{ return symbol(CodesLexicaux.FIN); }

<YYINITIAL> "("                	{ return symbol(CodesLexicaux.PAROUV); }
<YYINITIAL> ")"                	{ return symbol(CodesLexicaux.PARFER); }

<YYINITIAL> {commentaireSlashSlash} {}

<YYINITIAL> {commentaireSlashEtoile} { yybegin(commentaire) ; }

<YYINITIAL> {csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
<YYINITIAL> {csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
<YYINITIAL> {class}					{ return symbol(CodesLexicaux.CLASS, yytext()); }
<YYINITIAL> {idf}  					{ return symbol(CodesLexicaux.IDF, yytext()); }
<YYINITIAL> {fin} 					{ return symbol(CodesLexicaux.FFIN, yytext()); }
<YYINITIAL> {statut} 				{ return symbol(CodesLexicaux.STATUT, yytext()); }
<YYINITIAL> {espace}                { }

<commentaire> {commentaireEtoileSlash} { yybegin(YYINITIAL) ; }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
