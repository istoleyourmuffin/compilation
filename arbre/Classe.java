package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;

public class Classe {
	
	protected ListeDeclaration listeDeclaration;
	protected String identifiant;
	
	public Classe(ListeDeclaration ld, String idf) {
		listeDeclaration = ld;
		identifiant = idf;
	}
	
	public String toMips() {
		StringBuilder sb = new StringBuilder();
		sb.append(listeDeclaration.toMIPS());
		return sb.toString();
	}
	
}
