package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseLexicaleException;
import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Plus extends BinaireArithmetique {

    public Plus(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " + " ;
    }

	@Override
	public String toMIPS() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Somme ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("add $v0, $t8, $v0 \n");
		sb.append("# ---- Fin Somme ---- \n\n");
		
		return sb.toString();
	}
	
	public String getType() {
		return "int";
	}

}
