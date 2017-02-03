package plic.tds;

public class Symbole {

	public static enum Type {ENTIER;};
	public static enum Statut {PRIVEE, PUBLIQUE;};
	
	protected Type type;
	protected Statut statut;
	protected int deplacement;
	
	public Symbole(Statut s, Type t) {
		this.statut = s;
		this.type = t;
		this.deplacement = 0;
	}

	public String getDeplacement() {
		return String.valueOf(deplacement);
	}
	
}
