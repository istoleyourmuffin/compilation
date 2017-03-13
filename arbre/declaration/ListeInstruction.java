package plic.arbre.declaration;

import java.util.ArrayList;

import plic.arbre.ArbreAbstrait;

public class ListeInstruction extends ArbreAbstrait {

	protected ArrayList<Instruction> ali;

	
	public ListeInstruction(int n) {
		super(n);
		this.ali = new ArrayList<Instruction>();
	}

	public void ajouter(Instruction d) {
		ali.add(d);
	}

	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		for(Declaration d : ali) {
			sb.append(d.toMIPS());
		}
		
		return sb.toString();
	}
	
	public boolean verifier() {
		boolean valide = true;
		for(Declaration d : ali) {
			if(!d.verifier()) {
				valide = false;
			}
		}
		return valide;
	}
	

}
