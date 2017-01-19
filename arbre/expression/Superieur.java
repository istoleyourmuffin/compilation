package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Superieur extends Comparaison {

    public Superieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " > ";
    }

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

	public void verifier() {
		super.verifier();
		if (!gauche.getType().equals("int")
			|| !droite.getType().equals("int")) {
			throw new AnalyseSyntaxiqueException ("l'expression n'est pas composée d'entiers");
		}
	}
	
	protected String getType() {
		return "bool";
	}
    
}
