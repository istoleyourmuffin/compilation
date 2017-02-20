package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;
import plic.tds.Symbole;
import plic.tds.TDS;

public class Affectation extends DeclarationConstantes {

	protected String identifiant;
	protected Expression expression;
	protected String decalage;
	
	public Affectation(String idf, Expression exp, int n) {
		super(n);
		this.identifiant = idf;
		this.expression = exp;
	}

	@Override
	public String toMIPS() throws NonDeclarationException {
		StringBuilder sb = new StringBuilder();
		Symbole s = TDS.getInstance().identifier(identifiant);
		decalage = s.getDeplacement();
		
		sb.append("# Affectation de " + expression + " dans " + identifiant);
		sb.append(expression.toMIPS());
		sb.append("sw $v0, (-" + decalage + ")$s7\n");
		sb.append("# Fin Affectation dans "  + identifiant);
		
		return sb.toString();
	}
	
	@Override
	public void verifier() throws AnalyseSemantiqueException {
		
	}

}
