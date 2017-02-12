package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;
import plic.exceptions.AnalyseSyntaxiqueException;

public class Classe extends ArbreAbstrait {
	
	protected ListeDeclaration listeDeclaration;
	protected String identifiant;
	
	public Classe(ListeDeclaration ld, String idf, int n) {
		super(n);
		listeDeclaration = ld;
		identifiant = idf;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(listeDeclaration.toMIPS());
		return sb.toString();
	}
	

	@Override
	public void verifier() throws AnalyseSyntaxiqueException {
		// TODO Auto-generated method stub
	}
	
}