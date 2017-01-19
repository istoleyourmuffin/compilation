package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class NonLogique extends Unaire {
    
    public NonLogique(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return " non " ;
    }

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifier() {
		super.verifier();
		if (!expression.getType().equals("bool")) {
			throw new AnalyseSyntaxiqueException ("l'expression n'est pas un booléen");
		}
	}
	
	protected String getType() {
		return "bool";
	}

}
