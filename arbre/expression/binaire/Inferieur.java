package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exceptions.AnalyseSemantiqueException;
import plic.exceptions.AnalyseSyntaxiqueException;
import plic.exceptions.NonDeclarationException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Inferieur extends Comparaison {

    public Inferieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " < ";
    }

	@Override
	public String toMIPS() throws NonDeclarationException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# ----- Inférieur ----- \n");
		sb.append("# Ranger operande gauche dans $v0 \n");
		sb.append(gauche.toMIPS());
		sb.append("sw $v0, 0($sp) \n");
		sb.append("add $sp, $sp, -4 \n");
		sb.append("# Ranger operande droite dans $v0 \n");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4 \n");
		sb.append("lw $t8, ($sp) \n");
		sb.append("slt $v0, $t8, $v0 \n");
		sb.append("# ---- Fin Inférieur ---- \n\n");
		
		return sb.toString();
	}

	public void verifier() {
		super.verifier();
		if (!gauche.getType().equals("entier")
			|| !droite.getType().equals("entier")) {
			throw new AnalyseSemantiqueException(droite.getNoLigne(),"l'expression n'est pas composée d'entiers");
		}	
	}
	
	public String getType() {
		return "booleen";
	}
    
}
