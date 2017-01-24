package plic.arbre.expression;

import plic.Plic;
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

		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Non Logique ----- \n");
		sb.append("# Ranger exp dans $v0 \n");
		sb.append(expression.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger 1 dans $v0 \n");
		sb.append("li $v0, 1 \n");
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("xor $v0, $t8, $v0 \n");
		sb.append("# ---- Fin non logique ---- \n\n");
		
		return sb.toString();
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
