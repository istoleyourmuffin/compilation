package plic.arbre.expression.unaire;

import plic.arbre.expression.Expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class MoinsUnaire extends Unaire {
    
    public MoinsUnaire(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return "- " ;
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Soustraction ----- \n");
		sb.append("# Ranger 0 dans $v0 \n");
		sb.append("li $v0, 0 \n");
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(expression.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("sub $v0, $t8, $v0 \n");
		sb.append("# ---- Fin soustraction ---- \n\n");
		
		return sb.toString();
	}

	@Override
	public boolean verifier() {
		boolean valide = super.verifier();
		if (!expression.getType().equals("entier")) {
			System.err.println("ERREUR SEMANTIQUE : ligne " + expression.getNoLigne() + " : l'expression n'est pas un entier");
			valide = false;
		}
		return valide;
	}

	public String getType() {
		return "entier";
	}
}
