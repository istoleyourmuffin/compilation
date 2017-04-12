package plic.tds;

public abstract class Entree {
	
	protected String nom;
	
	public Entree(String s){
		nom = s;
	}
	
	public String getNom(){
		return nom;
	}

	public int hashCode() {
		return nom.hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entree other = (Entree) obj;
		if (getNom() == null) {
			if (other.getNom() != null)
				return false;
		} else if (!getNom().equals(other.getNom()))
			return false;
		return true;
	}
	
	
	

}
