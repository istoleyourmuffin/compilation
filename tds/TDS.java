package plic.tds;

public class TDS {
	
	private static TDS instance = new TDS();
	protected int tailleZoneDesVariables; // Pour savoir ou se trouve la variable la plus haute dans la "pile" S7
	protected TDSLocale bloc; // bloc courant	
	protected int dernierBloc;
	
	public static TDS getInstance() {
		return instance;
	}
	
	public boolean ajouter(String statut, String type, String idf, int n) {
		Entree e = new EntreeVar(idf);
		if (bloc.getTable().containsKey(e)){
			System.out.println("ERREUR SEMANTIQUE : ligne " + n + " : Double déclaration de la variable " + e.getNom());
			return false;
		}
		Symbole s = new Symbole(statut, type, tailleZoneDesVariables);
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
		bloc.ajouter(e, s);
		return true;
	}
	
	public boolean verifierExistence(String e) {
		Entree cle = new EntreeVar(e);
		return getBloc().verifierExistence(cle);
	}
	
	public Symbole identifier(String e){
		Entree cle = new EntreeVar(e);
		return getBloc().identifier(cle);
	}
	
	public int getTailleZoneDesVariables() {
		return this.tailleZoneDesVariables;
	}
	
	public void entreeBloc() {
		this.dernierBloc += 1;
		this.bloc = getBloc().ajouterFils(getDernierBloc());
		System.out.println("Entrée dans bloc " + getNumBloc());
	}
	
	public void entreeBloc(int numBloc){
		this.bloc = getBloc().getFils(numBloc);
		System.out.println("Entrée dans bloc " + getNumBloc());
	}
	
	public void sortieBloc() {
		System.out.println("Sortie du bloc " + getNumBloc());
		this.bloc = getBloc().getPere();
	}
	
	public String toMipsEntree() {
		return getBloc().toMipsEntree();
	}
	
	public String toMipsSortie() {
		return getBloc().toMipsSortie();
	}
	
	public TDSLocale getBloc() {
		return this.bloc;
	}
	
	public int getNumBloc() {
		return getBloc().getNumBloc();
	}
	
	public int getDernierBloc() {
		return this.dernierBloc;
	}
	
	private TDS() {
		this.tailleZoneDesVariables = 12;
		this.bloc = new TDSLocale(0);
		this.dernierBloc = 0;
	}

}
