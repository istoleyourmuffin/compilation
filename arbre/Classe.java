package plic.arbre;

import plic.arbre.declaration.ListeDeclaration;
import plic.tds.TDS;

public class Classe extends ArbreAbstrait {
	
	protected ListeDeclaration listeDeclaration;
	protected int numBloc;
	protected String idf;
	
	public Classe(String i, int num, ListeDeclaration ld, int n) {
		super(n);
		listeDeclaration = ld;
		numBloc = num;
		idf = i;
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
	
	public String getIdentifiant() {
		return idf;
	}

	@Override
	public boolean verifier() {
		TDS.getInstance().entreeBloc(numBloc);
		boolean valide = listeDeclaration.verifier();
		TDS.getInstance().sortieBloc();
		return valide;
	}
	
}
