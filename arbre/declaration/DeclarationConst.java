package plic.arbre.declaration;

import plic.tds.TDS;

public class DeclarationConst extends Declaration {

	ListeInstruction liste;
	int numBloc;
	
	public DeclarationConst(int num, ListeInstruction li, int n) {
		super(n);
		liste = li;
		numBloc = num;
	}
	
	public int getNumBloc(){
		return numBloc;
	}

	public boolean verifier() {
		boolean valide = true;//boolean valide = identifiant.equals(TDS.getInstance().getEnvironnement()); -> nom constructeur = nom classe
		return liste.verifier() && valide;
	}

	public String toMIPS() {
		TDS tds = TDS.getInstance();
		tds.entreeBloc(numBloc);
		StringBuilder sb = new StringBuilder();
		sb.append(tds.toMipsEntree());
		sb.append(liste.toMIPS());
		sb.append(tds.toMipsSortie());
		tds.sortieBloc();
		return sb.toString();
	}

}
