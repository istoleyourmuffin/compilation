package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;
import plic.tds.TDS;

public class Classe extends ArbreAbstrait {
	
	protected ListeDeclaration listeDeclaration;
	protected int numBloc;
	
	public Classe(int num, ListeDeclaration ld, int n) {
		super(n);
		listeDeclaration = ld;
		numBloc = num;
	}
	
	public Classe(int num, int n) {
		super(n);
		listeDeclaration = new ListeDeclaration(n);
		numBloc = num;
	}

	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(TDS.getInstance().toMipsEntree());
		sb.append(listeDeclaration.toMIPS());
		sb.append(TDS.getInstance().toMipsSortie());
		return sb.toString();
	}
	

	@Override
	public boolean verifier() {
		return listeDeclaration.verifier();
	}
	
}
