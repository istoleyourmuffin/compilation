package plic.arbre.declaration;

public class DeclarationArg extends Declaration {

	protected boolean valide;
	
	public DeclarationArg(int n, boolean val) {
		super(n);
		this.valide = val;
	}

	@Override
	public boolean verifier() {
		return valide;
	}

	@Override
	public String toMIPS() {
		return "";
	}

}
