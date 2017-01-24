package plic.arbre.expression;

import plic.Plic;
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
		
		/*Plic.incrementSi();
		int compteurActuel1 = Plic.getCompteurSi();
		Plic.incrementSi();
		int compteurActuel2 = Plic.getCompteurSi();*/
		
		StringBuilder sb = new StringBuilder();
		
		/*sb.append("# ----- Et Logique ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("si"+compteurActuel1+" : beqz $v0, sinon"+compteurActuel2+" \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		
		sb.append("si"+compteurActuel2+" : beqz $v0, sinon"+compteurActuel2+" \n");
		sb.append("alors"+compteurActuel2+" : li $v0, 1 \n");
		sb.append("j fin"+compteurActuel1+" \n");
		sb.append("sinon"+compteurActuel2+" : li $v0, 0 \n");
		sb.append("fin"+compteurActuel1+" : \n");
		sb.append("# ---- Fin Et Logique ---- \n\n");*/
		
		sb.append("# ----- Egalité ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("and $v0, $t8, $v0 \n");
		sb.append("# ---- Fin Egalité ---- \n\n");
		
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
