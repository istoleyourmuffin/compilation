package plic.tds;

import java.util.HashMap;

import plic.exceptions.DoubleDeclarationException;
import plic.exceptions.NonDeclarationException;

public class TDS {
	
	private static TDS instance = new TDS();
	protected HashMap<String,Symbole> table;
	protected int tailleZone; // Pour savoir ou se trouve la variable la plus haute dans la "pile" S7
	
	
	public static TDS getInstance() {
		return instance;
	}
	
	public void ajouter(String statut, String type, String idf) throws DoubleDeclarationException {
		if(table.containsKey(idf)) {
			throw new DoubleDeclarationException(" Double declaration de " + idf);
		}
		Symbole s = new Symbole(type, statut, tailleZone);
		table.put(idf, s);
		tailleZone += 4;
	}
	
	public void ajouter(String type, String idf) throws DoubleDeclarationException {
		if(table.containsKey(idf)) {
			throw new DoubleDeclarationException(" Double declaration" + idf);
		}
		Symbole s = new Symbole("privee", type, tailleZone);
		table.put(idf, s);
		tailleZone += 4;
	}
	
	public Symbole identifier(String e) throws NonDeclarationException {
		if(!table.containsKey(e)) {
			throw new NonDeclarationException(e +" n'est pas déclaré"); 
		}
		return table.get(e);
	}
	
	public int getSize() {
		return table.size();
	}
	
	private TDS() {
		this.tailleZone = 0;
		this.table = new HashMap<String,Symbole>();
		
	}

}
