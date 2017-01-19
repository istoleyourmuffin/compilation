package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Different extends Comparaison {

    public Different(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " != ";
    }

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

	public void verifier() {
		super.verifier();
		String g = gauche.getType(), d = droite.getType();
		if (!g.equals(d)) {
			throw new AnalyseSyntaxiqueException ("l'expression ne peut pas être comparée");
		}	
	}

	protected String getType() {
		return "bool";
	}
  
}
