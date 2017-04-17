package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;
import plic.tds.TDS;

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
		TDS.getInstance().entreeBloc();
		sb.append(TDS.getInstance().toMipsEntree());
		sb.append(listeDeclaration.toMIPS());
		sb.append(TDS.getInstance().toMipsSortie());
		TDS.getInstance().sortieBloc();
		return sb.toString();
	}
	

	@Override
	public boolean verifier() {
		return listeDeclaration.verifier();
	}
	
}
