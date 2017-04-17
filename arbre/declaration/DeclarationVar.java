package plic.arbre.declaration;

public class DeclarationVar extends Declaration {
	
	protected boolean valide;
	
	public DeclarationVar(int n, boolean bool) {
		super(n);
		valide = bool;
	}

	public boolean verifier() {
		return valide;
	}

	public String toMIPS() {
		return "";
	}

}
