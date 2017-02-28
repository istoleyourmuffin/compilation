package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;

public class Classe extends ArbreAbstrait {
	
	protected ListeDeclaration listeDeclaration;
	protected String identifiant;
	
	public Classe(ListeDeclaration ld, String idf, int n) {
		super(n);
		listeDeclaration = ld;
		identifiant = idf;
	}
	
	public Classe(String idf, int n) {
		super(n);
		identifiant = idf;
		listeDeclaration = new ListeDeclaration(n);
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(listeDeclaration.toMIPS());
		return sb.toString();
	}
	

	@Override
	public boolean verifier() {
		return listeDeclaration.verifier();
	}
	
}
