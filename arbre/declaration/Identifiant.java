package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.tds.Symbole;
import plic.tds.TDS;

public class Identifiant extends Expression {

	protected String identifiant;
	protected boolean valide;
	
	public Identifiant(String idf, int n) {
		super(n);
		this.identifiant = idf;
		this.valide = true;
	}
	
	public String getNom() {
		return this.identifiant;
	}

	@Override
	public String getType() {
		if (TDS.getInstance().verifierVar(identifiant)) {
			return TDS.getInstance().identifier(identifiant).getType();
		} else {
			System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : " + identifiant + " n'est pas déclaré");
			valide = false;
		}
		return "";
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# On stocke " + identifiant + " dans $v0 \n");
		Symbole s = TDS.getInstance().identifier(identifiant);
		String decalage = s.getDeplacement();
		sb.append("lw, $v0, -" + decalage + "($s7) \n");
		sb.append("# Fin du chargement de " + identifiant + "\n");
		return sb.toString();
	}

	@Override
	public boolean verifier() {
		return valide;
	}

}
