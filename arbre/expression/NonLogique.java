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

		Plic.incrementSi();
		int compteurActuel = Plic.getCompteurSi();
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Non Logique ----- \n");
		sb.append("# Ranger expression dans $v0 \n");
		sb.append(expression.toMIPS());
		sb.append("si"+compteurActuel+" : beqz $v0, sinon"+compteurActuel+"\n");
		sb.append("alors"+compteurActuel+" : li $v0, 0 \n");
		sb.append("j finsi"+compteurActuel+" \n");
		sb.append("sinon"+compteurActuel+": li $v0, 1 \n");
		sb.append("finsi"+compteurActuel+": \n");
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
