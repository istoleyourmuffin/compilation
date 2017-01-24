package plic.arbre.expression;

import plic.Plic;
import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class OuLogique extends BinaireLogique {

    public OuLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " ou " ;
    }

	@Override
	public String toMIPS() {
		
		Plic.incrementSi();
		int compteurActuel = Plic.getCompteurSi();
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Ou Logique ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("si"+compteurActuel+" : beqz $v0, sinon1 \n");
		sb.append("alors"+compteurActuel+" : li $v0, 1 \n");
		sb.append("j fin"+compteurActuel+" \n");
		sb.append("sinon"+compteurActuel+" : \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		
		Plic.incrementSi();
		compteurActuel = Plic.getCompteurSi();
		
		sb.append("si"+compteurActuel+" : beqz $v0, sinon2 \n");
		sb.append("alors"+compteurActuel+" : li $v0, 1 \n");
		sb.append("j fin"+compteurActuel+" \n");
		sb.append("sinon"+compteurActuel+" : li $v0, 0 \n");
		sb.append("fin"+compteurActuel+" : \n");
		sb.append("# ---- Fin Ou Logique ---- \n\n");
		
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
