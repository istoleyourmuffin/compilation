package plic.tds;

public class Symbole {
	
	protected String type;
	protected String statut;
	protected int deplacement;
	
	public Symbole(String s, String t, int d) {
		this.type = t;
		this.statut = s;
		this.deplacement = d;
	}

	public String getDeplacement() {
		return String.valueOf(deplacement);
	}
	
	public String getType() {
		return type;
	}
	
	public String getStatut() {
		return statut;
	}
}
