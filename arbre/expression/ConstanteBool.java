package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

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
		StringBuilder sb = new StringBuilder();
		sb.append("#Chargement de " + this.cste + " dans $v0\n");
		if (this.cste.equals("vrai")){
			sb.append("li $v0, 1\n");
		} else {
			sb.append("li $v0, 0\n");
		}
		return sb.toString();
	}

	@Override
	public void verifier() {
		if (!(this.cste.equals("vrai")) || !(this.cste.equals("faux"))){
			throw new AnalyseSyntaxiqueException("Paramètres incorrects (constante booleene)");
		}
		
	}

}
