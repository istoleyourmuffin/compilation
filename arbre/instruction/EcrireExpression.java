package plic.arbre.instruction;

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
		if (expr.getType().equals("booleen")){
			sb.append("#On teste la valeur de v0\n");
			String indice = getNoLigne()+ "" + (int)(10000 + (Math.random() * (100000 - 10000))); // Nombre aléatoire entre 10000 et 99999
			String si = "si"+indice;
			String alors = "alors"+indice;
			String sinon = "sinon"+indice;
			String fsi = "fsi"+indice;
			sb.append(si);
			sb.append(": beqz $v0, ");
			sb.append(sinon);
			sb.append("\n");
			sb.append(alors);
			sb.append(":\n");
			sb.append("la $a0, boolVrai\n");
			sb.append("b ");
			sb.append(fsi);
			sb.append("\n");
			sb.append(sinon);
			sb.append(": \n");
			sb.append("la $a0, boolFaux\n");
			sb.append(fsi);
			sb.append(": \n");
			sb.append("li $v0, 4 # $v0 <- code du print\n");			
		} else {
			sb.append("#On transfere la valeur de $v0 vers $a0\n");
			sb.append("move $a0, $v0\n");
			sb.append("#On affiche le resultat de l'expression\n");
			sb.append("li $v0, 1\n");
		}
		sb.append("syscall 	# afficher\n");
		return sb.toString();
	}
	
	public boolean verifier() {
		return expr.verifier();
	}
}
