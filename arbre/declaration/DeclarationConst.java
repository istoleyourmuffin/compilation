package plic.arbre.declaration;

import plic.tds.TDS;

public class DeclarationConst extends Declaration {

	ListeInstruction liste;
	int numBloc;
	String idf;
	
	public DeclarationConst(String i, int num, ListeInstruction li, int n) {
		super(n);
		liste = li;
		numBloc = num;
		idf = i;
	}
	
	public int getNumBloc(){
		return numBloc;
	}

	public boolean verifier() {
		boolean valide = TDS.getInstance().verifConst(idf, liste.getNoLigne());
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
