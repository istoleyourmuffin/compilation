package plic.arbre.declaration;

public class DeclarationChamp extends Declaration {
	
	protected boolean valide;
	
	public DeclarationChamp(int n, boolean bool) {
		super(n);
		valide = bool;
	}
	
	public boolean verifier() {
		return valide;
	}

	/* Pas besoin de toMIPS comme ce sont des déclarations, il faut juste ajouter les variables à la TDS */
	public String toMIPS() {
		return "";
	}
	

}
