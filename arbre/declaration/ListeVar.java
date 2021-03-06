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
		boolean valide = true;
		for(DeclarationVar d : alv) {
			if(!d.verifier()) {
				valide = false;
			}
		}
		return valide;
	}

	public String toMIPS() {
		return "";
	}

}
