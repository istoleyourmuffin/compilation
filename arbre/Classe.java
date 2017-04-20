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
		TDS.getInstance().entreeBloc(numBloc);
		sb.append(listeDeclaration.toMIPS());
		TDS.getInstance().sortieBloc();
		sb.append(TDS.getInstance().toMipsSortie());
		return sb.toString();
	}
	

	@Override
	public boolean verifier() {
		TDS.getInstance().entreeBloc(numBloc);
		boolean valide = listeDeclaration.verifier();
		TDS.getInstance().sortieBloc();
		return valide;
	}
	
}
