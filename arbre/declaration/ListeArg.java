package plic.arbre.declaration;

import java.util.ArrayList;

public class ListeArg extends Declaration {

	protected ArrayList<DeclarationArg> ala;
	
	public ListeArg(int n) {
		super(n);
		ala = new ArrayList<DeclarationArg>();
	}

	public boolean verifier() {
		boolean valide = true;
		for(DeclarationArg d : ala) {
			if(!d.verifier()) {
				valide = false;
			}
		}
		return valide;
	}

	public String toMIPS() {
		return "";
	}

	public void ajouter(DeclarationArg d) {
		ala.add(d);
	}

}
