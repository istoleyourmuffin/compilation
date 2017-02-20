package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;
import plic.tds.Symbole;
import plic.tds.TDS;

public class Identifiant extends Expression {

	protected String identifiant;
	
	public Identifiant(String idf, int n) {
		super(n);
		this.identifiant = idf;
	}
	
	public String getNom() {
		return this.identifiant;
	}

	@Override
	public String getType() {
		try {
			return TDS.getInstance().identifier(identifiant).getType();
		} catch (NonDeclarationException e) {
			throw new AnalyseSemantiqueException(getNoLigne(), identifiant + "non déclaré");
		}
	}
	
	@Override
	public String toMIPS() throws NonDeclarationException {
		StringBuilder sb = new StringBuilder();
		sb.append("# On stocke " + identifiant + " dans $v0 \n");
		Symbole s = TDS.getInstance().identifier(identifiant);
		String decalage = s.getDeplacement();
		sb.append("lw, $v0, -" + decalage + "($s7) \n");
		sb.append("# Fin du chargement de " + identifiant + "\n");
		return sb.toString();
	}

	@Override
	public void verifier() throws AnalyseSyntaxiqueException {
		
	}

}
