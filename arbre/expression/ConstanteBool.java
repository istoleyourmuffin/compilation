package plic.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
    }

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

	public void verifier() {
	}

	protected String getType() {
		return "bool";
	}

}
