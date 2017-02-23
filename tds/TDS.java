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
	
	public void ajouter(String statut, String type, String idf) throws AnalyseSemantiqueException {
		if(table.containsKey(idf)) {
			throw new AnalyseSemantiqueException(-1, " Double declaration de la variable " + idf);
		}
		Symbole s = new Symbole(type, statut, tailleZoneDesVariables);
		table.put(idf, s);
		tailleZoneDesVariables += 4;
	}
	
	public void ajouter(String type, String idf) throws AnalyseSemantiqueException {
		if(table.containsKey(idf)) {
			throw new AnalyseSemantiqueException(-1, " Double declaration de la variable " + idf);
		}
		Symbole s = new Symbole("privee", type, tailleZoneDesVariables);
		table.put(idf, s);
		tailleZoneDesVariables += 4;
	}
	
	public Symbole identifier(String e) throws AnalyseSemantiqueException {
		if(!table.containsKey(e)) {
			throw new AnalyseSemantiqueException(-1, e +" n'est pas déclaré"); 
		}
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
