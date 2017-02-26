package plic.arbre.declaration;

import plic.arbre.expression.Expression;

public class EcrireExpression extends Ecrire {
	
	protected Expression expr;
	
	public EcrireExpression(Expression e, int n) {
		super(n);
		expr = e;
	}

	
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# ----- EcrireExpression ----- \n");
		sb.append("#On calcule la valeur de l'expression\n");
		sb.append(expr.toMIPS());
		sb.append("#On transfere la valeur de $v0 vers $a0\n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("lw $a0, ($sp)\n");
		sb.append("#On affiche l'entier\n");
		sb.append("li $v0, 1\n");
		sb.append("syscall 	# afficher\n");
		return sb.toString();
	}
	
	public boolean verifier() {
		return expr.verifier();
	}
}
