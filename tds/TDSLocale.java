package plic.tds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class TDSLocale {
	
	protected TDSLocale pere; //Pointe vers le pere
	protected ArrayList<TDSLocale> fils; //Liste des fils 
	protected int numBloc; //Numéro du bloc
	protected String nomBloc;
	protected HashMap<Entree,Symbole> table; 
	protected int tailleZoneDesVariables;
	
	public TDSLocale (int num) {
		this.pere = null;
		this.fils = new ArrayList<TDSLocale>();
		this.numBloc = num; // Notre numéro de bloc
		this.table = new HashMap<Entree,Symbole>(); // table locale
		this.tailleZoneDesVariables = 12; //Taille zone des variables locales
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
	
	public String toMipsEntree() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Entrée dans bloc " + numBloc + "\n");
		sb.append("sw $s7, 0($sp) \n");
		sb.append("# Initialisation de la base des variables \n");
		sb.append("move $s7,$sp \n");
		sb.append("# Réservation de l'espace pour " + tailleZoneDesVariables/4 + " variables \n");
		sb.append("addi $sp, $sp, -" + tailleZoneDesVariables + "\n");
		sb.append("# On rentre le num de bloc à sa place\n");
		sb.append("li $v0, " + numBloc + "\n");
		sb.append("sw $v0, -8($s7)\n\n");
		
		return sb.toString();
	}
	
	public String toMipsSortie() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Sortie du bloc " + numBloc + "\n");
		sb.append("# Ajustement sommet de la pile \n");
		sb.append("move $sp, $s7\n");
		sb.append("# Ajustement de la base des variables \n");
		sb.append("sw $s7, ($sp)\n");
		
		return sb.toString();
	}
	
	public boolean verifierExistence(Entree e) {
		if(getPere() == null) {
			return table.containsKey(e) ;
		}
		return table.containsKey(e) || getPere().verifierExistence(e);
	}
	
	public Symbole identifier(Entree e) {
		if(table.containsKey(e)) {
			return table.get(e);
		} else if(pere != null) {
			return pere.identifier(e);
		} else {
			return null;
		}
	}

	public TDSLocale getPere() {
		return this.pere;
	}

	public TDSLocale getFils(int numBloc) {
		TDSLocale res = null;
		for(TDSLocale temp: fils){
			if(temp.getNumBloc() == numBloc){
				res = temp;
			}
		}
		return res;
	}
	
	public String getNomBloc() {
		return this.nomBloc;
	}
	
	public void setNomBloc(String nom) {
		this.nomBloc = nom;
	}
	
	public int getNumBloc() {
		return this.numBloc;
	}

	public HashMap<Entree,Symbole> getTable() {
		return this.table;
	}

}
