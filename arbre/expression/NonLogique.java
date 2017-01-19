package plic.arbre.expression;

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
		sb.append("# Ranger expression dans $v0 \n");
		sb.append(expression.toMIPS());
		sb.append("si : beqz $v0, sinon\n");
		sb.append("alors : li $v0, 0 \n");
		sb.append("j finsi \n");
		sb.append("sinon: li $v0, 1 \n");
		sb.append("finsi: \n");
		sb.append("# ---- Fin Non Logique ---- \n\n");
		
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
