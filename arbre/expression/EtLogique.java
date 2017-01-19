package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class EtLogique extends BinaireLogique {

    public EtLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " et " ;
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Et Logique ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("si1 : beqz $v0, sinon2 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("si2 : beqz $v0, sinon2 \n");
		sb.append("alors2 : li $v0, 1 \n");
		sb.append("j fin \n");
		sb.append("sinon2 : li $v0, 0 \n");
		sb.append("fin : \n");
		sb.append("# ---- Fin Et Logique ---- \n\n");
		
		return sb.toString();
	}

	@Override
	public void verifier() {
		super.verifier();
		if (!gauche.getType().equals("bool")
			|| !droite.getType().equals("bool")) {
			throw new AnalyseSyntaxiqueException ("l'expression n'est pas composée de booléens");
		}
	}
	
	protected String getType() {
		return "bool";
	}

}
