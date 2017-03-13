package plic.arbre.instruction;

import plic.arbre.declaration.ListeDeclaration;
import plic.arbre.expression.Expression;

public class TantQue extends Iteration {

	protected Expression cond;
	protected ListeDeclaration liste;
	
	public TantQue(Expression e, ListeDeclaration ld, int n) {
		super(n);
		this.cond = e;
		this.liste = ld;
	}


	@Override
	public String toMIPS() {
		String indice = getNoLigne()+ "" + (int)(10000 + (Math.random() * (100000 - 10000))); // Nombre aléatoire entre 10000 et 99999
		String tq = "tantque" + indice;
		String finTq = "fintantque" + indice;
		String repeter = "repeter" + indice;
		
		StringBuilder sb = new StringBuilder();
		sb.append(cond.toMIPS());
		sb.append(tq);
		sb.append(": beqz $v0, ");
		sb.append(finTq);
		sb.append("\n");
		sb.append(repeter);
		sb.append(":\n");
		sb.append(liste.toMIPS());
		sb.append(cond.toMIPS());
		sb.append("b ");
		sb.append(tq);
		sb.append("\n");
		sb.append(finTq);
		sb.append(": \n");
		
		return sb.toString();
	}

	@Override
	public boolean verifier() {
		boolean valide = cond.verifier();
		if (!cond.getType().equals("booleen")) {
			valide = false;
			System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : l'expression n'est pas composée de booléens");
		}
		valide = valide && liste.verifier();
		
		return valide;
	}
}
