package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
    }
    
    public void verifier() {
    	super.verifier();
		if (!gauche.getType().equals("int")
			|| !droite.getType().equals("int")) {
			throw new AnalyseSyntaxiqueException ("l'expression n'est pas composée d'entiers");
		}
    }
}
