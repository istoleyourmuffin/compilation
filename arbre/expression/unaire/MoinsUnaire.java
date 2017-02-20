package plic.arbre.expression.unaire;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;

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
	public String toMIPS() throws NonDeclarationException {
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
	public void verifier() {
		super.verifier();
		if (!expression.getType().equals("int")) {
			throw new AnalyseSyntaxiqueException ("l'expression n'est pas un entier");
		}
		
	}

	public String getType() {
		return "int";
	}
}
