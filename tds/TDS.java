package plic.tds;

import java.util.HashMap;

import plic.exceptions.AnalyseSemantiqueException;

public class TDS {
	
	private static TDS instance = new TDS();
	protected HashMap<String,Symbole> table;
	protected int tailleZoneDesVariables; // Pour savoir ou se trouve la variable la plus haute dans la "pile" S7
	
	
	public static TDS getInstance() {
		return instance;
	}
	
	public void ajouter(String statut, String type, String idf) {
		Symbole s = new Symbole(statut, type, tailleZoneDesVariables);
		table.put(idf, s);
		tailleZoneDesVariables += 4;
	}
	
	public void ajouter(String type, String idf) {
		Symbole s = new Symbole("privee", type, tailleZoneDesVariables);
		table.put(idf, s);
		tailleZoneDesVariables += 4;
	}
	
	public boolean verifierExistence(String e) {
		return table.containsKey(e);
	}
	
	public Symbole identifier(String e){
		return table.get(e);
	}
	
	public int getTailleZoneDesVariables() {
		return this.tailleZoneDesVariables;
	}
	
	private TDS() {
		this.tailleZoneDesVariables = 0;
		this.table = new HashMap<String,Symbole>();
		
	}

}
