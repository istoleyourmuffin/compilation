package plic.arbre.declaration;

import java.util.ArrayList;

import plic.arbre.ArbreAbstrait;
import plic.exceptions.AnalyseSemantiqueException;

public class ListeDeclaration extends ArbreAbstrait {
	
	protected ArrayList<Declaration> ald;
	
	public ListeDeclaration(int n) {
		super(n);
		this.ald = new ArrayList<Declaration>();
	}
	
	public void ajouter(Declaration d) {
		ald.add(d);
	}
	
	@Override
	public String toMIPS() throws AnalyseSemantiqueException {
		StringBuilder sb = new StringBuilder();
		
		for(Declaration d : ald) {
			sb.append(d.toMIPS());
		}
		
		return sb.toString();
	}

	@Override
	public void verifier() throws AnalyseSemantiqueException {
		for(Declaration d : ald) {
			d.verifier();
		}
	}
}
