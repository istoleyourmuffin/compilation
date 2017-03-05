package plic.arbre.instruction;

import plic.arbre.Instruction;
import plic.arbre.declaration.ListeDeclaration;
import plic.arbre.expression.Expression;

public class Condition extends Instruction {

	protected Expression cond;
	protected ListeDeclaration liste1, liste2;
	protected boolean existeSinon;
	
	public Condition(Expression c, ListeDeclaration l, int n) {
		super(n);
		cond = c;
		liste1 = l;
		existeSinon = false;
	}
	
	public Condition (Expression c, ListeDeclaration l1, ListeDeclaration l2, int n) {
		super(n);
		cond = c;
		liste1 = l1;
		liste2 = l2;
		existeSinon = true;
	}

	public boolean verifier() {
		boolean valide = cond.verifier();
		if (!cond.getType().equals("booleen")) {
			valide = false;
			System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : l'expression n'est pas composée de booléens");
		}
		valide = valide && liste1.verifier();
		if (existeSinon){
			valide = valide && liste2.verifier();
		}
		return valide;
	}

	public String toMIPS() {
		int indice = getNoLigne()+(int)(Math.random()*10000);
		String si = "si"+indice;
		String alors = "alors"+indice;
		String sinon = "sinon"+indice;
		String fsi = "fsi"+indice;
		StringBuilder sb = new StringBuilder();
		sb.append(si);
		sb.append(": beqz $v0, ");
		if(existeSinon) {
			sb.append(sinon);
		} else {
			sb.append(fsi);
		}
		sb.append("\n");
		sb.append(alors);
		sb.append(":\n");
		sb.append(liste1.toMIPS());
		if (existeSinon) {
			sb.append("b ");
			sb.append(fsi);
			sb.append("\n");
			sb.append(sinon);
			sb.append(": \n");
			sb.append(liste2.toMIPS());
		}
		sb.append(fsi);
		sb.append("#Fini");
		sb.append("\n");
		
		return sb.toString();
	}

	
	

}
