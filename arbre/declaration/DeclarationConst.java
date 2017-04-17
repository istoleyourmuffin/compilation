package plic.arbre.declaration;

import plic.tds.TDS;

public class DeclarationConst extends Declaration {

	String identifiant;
	ListeInstruction liste;
	
	public DeclarationConst(String idf, ListeInstruction li, int n) {
		super(n);
		identifiant = idf;
		liste = li;
	}

	public boolean verifier() {
		boolean valide = true;//boolean valide = identifiant.equals(TDS.getInstance().getEnvironnement());
		return liste.verifier() && valide;
	}

	public String toMIPS() {
		return liste.toMIPS();
	}

}
