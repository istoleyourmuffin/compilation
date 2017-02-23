package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;

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
		if (!gauche.getType().equals("entier")
			|| !droite.getType().equals("entier")) {
			System.err.println("ERREUR SEMANTIQUE : ligne " + droite.getNoLigne() + " : l'expression n'est pas composée d'entiers");
		}
    }
}
