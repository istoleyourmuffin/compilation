package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;
import plic.tds.Symbole;
import plic.tds.TDS;

public class Affectation extends Declaration {

	protected String identifiant;
	protected Expression expression;
	protected String decalage;
	
	public Affectation(String idf, Expression exp, int n) {
		super(n);
		this.identifiant = idf;
		this.expression = exp;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		try {
			Symbole s = TDS.getInstance().identifier(identifiant);
			decalage = s.getDeplacement();
		} catch (NonDeclarationException e) {}
		
		sb.append("# Affectation de " + expression + " dans " + identifiant);
		sb.append(expression.toMIPS());
		sb.append("sw $v0, (-" + decalage + ")$s7\n");
		sb.append("# Fin Affectation dans "  + identifiant);
		
		return sb.toString();
	}
	
	@Override
	public void verifier() throws AnalyseSyntaxiqueException {
		
	}

}
