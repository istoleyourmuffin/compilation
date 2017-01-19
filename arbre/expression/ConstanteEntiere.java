package plic.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Chargement de " + this.cste + " dans $v0\n");
		sb.append("li $v0, " + this.cste + "\n");
		return sb.toString();
	}

	public void verifier() {
	}

	protected String getType() {
		return "int";
	}

}
