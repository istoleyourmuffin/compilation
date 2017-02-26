package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;
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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		try {
		Symbole s = TDS.getInstance().identifier(identifiant);
		decalage = s.getDeplacement();
		
		sb.append("# Affectation de " + expression + " dans " + identifiant +"\n");
		sb.append(expression.toMIPS());
		sb.append("sw $v0, -" + decalage + "($s7)\n");
		sb.append("# Fin Affectation dans "  + identifiant + "\n");
		} catch (AnalyseSemantiqueException ae) {
			System.err.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : " + identifiant + " n'est pas déclaré");
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean verifier() {
		boolean valide = true;
		if (!TDS.getInstance().verifierExistence(identifiant)) {
			System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : " + identifiant + " n'est pas déclaré");
			valide = false;
		}
		return expression.verifier() && valide;
	}

}
