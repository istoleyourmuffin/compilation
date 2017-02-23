package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;
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
		return TDS.getInstance().identifier(identifiant).getType();
	}
	
	@Override
	public String toMIPS() throws AnalyseSemantiqueException {
		StringBuilder sb = new StringBuilder();
		sb.append("# On stocke " + identifiant + " dans $v0 \n");
		try {
			Symbole s = TDS.getInstance().identifier(identifiant);
			String decalage = s.getDeplacement();
			sb.append("lw, $v0, -" + decalage + "($s7) \n");
			sb.append("# Fin du chargement de " + identifiant + "\n");
		} catch (AnalyseSemantiqueException e) {
			throw new AnalyseSemantiqueException(getNoLigne(), identifiant + " n'est pas déclaré");
		}
		return sb.toString();
	}

	@Override
	public void verifier() throws AnalyseSemantiqueException {
		
	}

}
