package plic.arbre.declaration;

import java.util.ArrayList;

public class ListeVar extends Declaration {
	
	protected ArrayList<DeclarationVar> alv;
	public ListeVar(int n) {
		super(n);
		alv = new ArrayList<>();
	}

	public void ajouter(DeclarationVar dv){
		alv.add(dv);
	}	
	
	public boolean verifier() {
		return true; //a completer ?
	}

	public String toMIPS() {
		return "";
	}

}
