package plic.arbre.declaration;

import plic.exceptions.DoubleDeclarationException;
import plic.tds.Symbole;
import plic.tds.TDS;

public class DeclarationChamp extends Declaration {

	
	
	public DeclarationChamp(String s, String t, ListeIdentifiant li) {
		super(0); // ????
		Symbole s2;
		if(s.equals("publique")) {
			s2 = new Symbole(Symbole.Statut.PUBLIQUE, Symbole.Type.ENTIER);
		} else {
			s2 = new Symbole(Symbole.Statut.PRIVEE, Symbole.Type.ENTIER);
		}
		for(String i : li) {
			try {
				TDS.getInstance().ajouter(i, s2);
			} catch (DoubleDeclarationException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String toMIPS() {
		return "";
	}

}
