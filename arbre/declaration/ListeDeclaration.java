package plic.arbre.declaration;

import java.util.ArrayList;

import plic.arbre.ArbreAbstrait;
import plic.tds.TDS;

public class ListeDeclaration extends ArbreAbstrait {
	
	protected ArrayList<Declaration> ald;
	
	public ListeDeclaration(int n) {
		super(n);
		this.ald = new ArrayList<Declaration>();
	}
	
	public void ajouter(Declaration d) {
		ald.add(d);
	}
	
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		for(Declaration d : ald) {
			sb.append(d.toMIPS());
		}
		
		return sb.toString();
	}

	public boolean verifier() {
		boolean valide = true;
		for(Declaration d : ald) {
			if(d instanceof DeclarationConst){
				TDS.getInstance().entreeBloc(((DeclarationConst) d).getNumBloc());
			}
			if(!d.verifier()) {
				valide = false;
			}
			if(d instanceof DeclarationConst){
				TDS.getInstance().sortieBloc();
			}
		}
		return valide;
	}
}
