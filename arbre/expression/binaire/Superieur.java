package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;

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
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Supérieur ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("sgt $v0, $t8, $v0 \n");
		sb.append("# ---- Fin Supérieur ---- \n\n");
		
		return sb.toString();
	}

	public boolean verifier() {
		boolean valide = super.verifier();
		if (!gauche.getType().equals("entier")
			|| !droite.getType().equals("entier")) {
			System.err.println("ERREUR SEMANTIQUE : ligne " + droite.getNoLigne() + " : l'expression n'est pas composée d'entiers");
			valide = false;
		}
		return valide;
	}
	
	public String getType() {
		return "booleen";
	}
    
}
