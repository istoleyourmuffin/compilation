package plic.arbre.expression;

import plic.exceptions.AnalyseSyntaxiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Egal extends Comparaison {

    public Egal(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " == ";
    }

	@Override
	public String toMIPS() {
		
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
		sb.append("seq $v0, $t8, $v0 \n");
		sb.append("# ---- Fin Egalité ---- \n\n");
		
		return sb.toString();
	}

	public void verifier() {
		super.verifier();
		String g = gauche.getType(), d = droite.getType();
		if (!g.equals(d)) {
			throw new AnalyseSyntaxiqueException ("l'expression ne peut pas être comparée");
		}	
	}

	protected String getType() {
		return "int";
	}
    
}
