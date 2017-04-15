package plic.tds;

import java.util.ArrayList;
import java.util.HashMap;

public class TDSLocale {
	
	protected TDSLocale pere; //Pointe vers le pere
	protected ArrayList<TDSLocale> fils; //Liste des fils 
	protected int numFils; //Pour savoir sur quel fils on pointe
	protected int numBloc; //Numéro du bloc
	protected HashMap<Entree,Symbole> table; 
	protected int tailleZoneDesVariables;
	
	public TDSLocale () {
		this.pere = null;
		this.fils = new ArrayList<TDSLocale>();
		this.numFils = 0; // Pour savoir dans quel fils on est. Pas encore utilisé
		this.numBloc = 0; // Notre numéro de bloc
		this.table = new HashMap<Entree,Symbole>(); // table locale
		this.tailleZoneDesVariables = 12; //Taille zone des varaibles locale
	}
	
	private TDSLocale(TDSLocale pere, int num) {
		this.pere = pere;
		this.numBloc = num;
		this.fils = new ArrayList<TDSLocale>();
		this.table = new HashMap<Entree, Symbole>();
	}

	public TDSLocale ajouterFils(int num) {
		TDSLocale tdsl = new TDSLocale(this, num);
		this.fils.add(tdsl);
		return tdsl;
	}

	public void ajouter(Entree e, Symbole s) {
		table.put(e, s);
		tailleZoneDesVariables += 4;
	}

	public TDSLocale getPere() {
		this.numFils = 0;
		return this.pere;
	}
	
	public HashMap<Entree,Symbole> getTable() {
		return this.table;
	}
}
