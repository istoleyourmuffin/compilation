package plic.tds;

import java.util.HashMap;

import plic.exceptions.AnalyseSemantiqueException;

public class TDS {
	
	private static TDS instance = new TDS();
	protected int tailleZoneDesVariables; // Pour savoir ou se trouve la variable la plus haute dans la "pile" S7
	protected Entree entreeEnvironnement;
	protected Symbole symboleEnvironnement;
	protected TDSLocale bloc; // bloc courant
	protected int numBloc; // numero du bloc courant
	
	
	public static TDS getInstance() {
		return instance;
	}
	
	public void setEnvironnement(String statut, String type, String idf){
		entreeEnvironnement = new EntreeClass(idf);
		Symbole s = new Symbole(statut, type, tailleZoneDesVariables); // A quoi ça sert ?
	}
	
	public String getEnvironnement(){
		return entreeEnvironnement.getNom();
	}
	
	public boolean ajouter(String statut, String type, String idf, int n) {
		Entree e = new EntreeVar(idf);
		if (bloc.getTable().containsKey(e)){
			System.out.println("ERREUR SEMANTIQUE : ligne " + n + " : Double déclaration de la variable " + e.getNom());
			return false;
		}
		Symbole s = new Symbole(statut, type, tailleZoneDesVariables);
		//table.put(e, s);
		bloc.ajouter(e, s);
		tailleZoneDesVariables += 4;
		return true;
	}
	
	public boolean ajouter(String type, String idf, int n) {
		Entree e = type.equals("const") ? new EntreeConst(idf) : new EntreeVar(idf); // idf = (condition) ? valeur si vrai : valeur si faux;
		if (bloc.getTable().containsKey(e) && idf.equals(bloc.getTable().get(e).getType())){
			System.out.println("ERREUR SEMANTIQUE : ligne " + n + " : Double déclaration de l'identifiant " + e.getNom());
			return false;
		}
		Symbole s = type.equals("const") ? new Symbole("publique", type, tailleZoneDesVariables)
										 : new Symbole("privee", type, tailleZoneDesVariables);
		tailleZoneDesVariables += 4;
		//table.put(e, s);
		bloc.ajouter(e, s);
		return true;
	}
	
	public boolean verifierExistence(String e) {
		Entree cle = new EntreeVar(e);
		return this.bloc.getTable().containsKey(cle);
	}
	
	public Symbole identifier(String e){
		Entree cle = new EntreeVar(e);
		return this.bloc.getTable().get(cle);
	}
	
	public int getTailleZoneDesVariables() {
		return this.tailleZoneDesVariables;
	}
	
	public void entreeBloc() {
		this.bloc = bloc.ajouterFils(++numBloc);
	}
	
	public void sortieBloc() {
		this.bloc = bloc.getPere();
	}
	
	public TDSLocale getBloc() {
		return this.bloc;
	}
	
	private TDS() {
		this.tailleZoneDesVariables = 12;
		this.bloc = new TDSLocale();
	}

}
