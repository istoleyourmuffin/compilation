package plic.arbre;

import java.util.ArrayList;

import plic.arbre.declaration.Instruction;

public class Bloc {
	
	protected ArrayList<Instruction> ali;
	
	public Bloc() {
		ali = new ArrayList<Instruction>();
	}
	
	public void ajouter(Instruction i) {
		ali.add(i);
	}
	
	public boolean verifier() {
		boolean valide = true;
		for(Instruction i : ali) {
			if(!i.verifier()) {
				valide = false;
			}
		}
		return valide;
	}
	
	public String toMips() {
		StringBuilder sb = new StringBuilder();
		
		for(Instruction i : ali){
			sb.append(i.toMIPS());
		}
		
		return sb.toString();
}

}
