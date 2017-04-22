package plic.arbre.declaration;

import plic.tds.TDS;

public class DeclarationConst extends Declaration {

	ListeInstruction liste;
	int numBloc;
	boolean valide;
	String idf;
	
	public DeclarationConst(String i, int num, boolean val, ListeInstruction li, int n) {
		super(n);
		liste = li;
		numBloc = num;
		idf = i;
		valide = val;
	}
	
	public int getNumBloc(){
		return numBloc;
	}

	public boolean verifier() {
		valide = TDS.getInstance().verifConst(idf, liste.getNoLigne()) && valide;
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
