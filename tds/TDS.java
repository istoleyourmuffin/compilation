package plic.tds;

import java.util.HashMap;

import plic.exceptions.AnalyseSemantiqueException;

public class TDS {
	
	private static TDS instance = new TDS();
	protected HashMap<Entree,Symbole> table;
	protected int tailleZoneDesVariables; // Pour savoir ou se trouve la variable la plus haute dans la "pile" S7
	
	
	public static TDS getInstance() {
		return instance;
	}
	
	public boolean ajouter(String statut, String type, String idf, int n) {
		Entree e = new EntreeVar(idf);
		if (table.containsKey(e)){
			System.out.println("ERREUR SEMANTIQUE : ligne " + n + " : Double déclaration de la variable " + e.getNom());
			return false;
		}
		Symbole s = new Symbole(statut, type, tailleZoneDesVariables);
		table.put(e, s);
		tailleZoneDesVariables += 4;
		return true;
	}
	
	public boolean ajouter(String type, String idf, int n) {
		Entree e = new EntreeVar(idf);
		if (table.containsKey(e)){
			System.out.println("ERREUR SEMANTIQUE : ligne " + n + " : Double déclaration de la variable " + e.getNom());
			return false;
		}
		Symbole s = new Symbole("privee", type, tailleZoneDesVariables);
		table.put(e, s);
		tailleZoneDesVariables += 4;
		return true;
	}
	
	public boolean verifierExistence(String e) {
		Entree cle = new EntreeVar(e);
		return table.containsKey(cle);
	}
	
	public Symbole identifier(String e){
		Entree cle = new EntreeVar(e);
		return table.get(cle);
	}
	
	public int getTailleZoneDesVariables() {
		return this.tailleZoneDesVariables;
	}
	
	private TDS() {
		this.tailleZoneDesVariables = 0;
		this.table = new HashMap<Entree,Symbole>();
		
	}

}
