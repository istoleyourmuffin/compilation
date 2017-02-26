package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
    }
    
    public boolean verifier() {
    	boolean valide = super.verifier();
		if (!gauche.getType().equals("entier")
			|| !droite.getType().equals("entier")) {
			System.out.println("ERREUR SEMANTIQUE : ligne " + droite.getNoLigne() + " : l'expression n'est pas composée d'entiers");
			valide = false;
		}
		return valide;
    }
}
