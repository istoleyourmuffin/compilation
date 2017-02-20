package plic.arbre.expression.binaire;

import plic.Plic;
import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;

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
	public String toMIPS() throws NonDeclarationException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Egalité ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("or $v0, $t8, $v0 \n");
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
	
	public String getType() {
		return "bool";
	}

}
