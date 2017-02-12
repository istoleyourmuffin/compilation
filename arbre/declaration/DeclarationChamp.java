package plic.arbre.declaration;

import plic.exceptions.DoubleDeclarationException;
import plic.tds.Symbole;
import plic.tds.TDS;

public class DeclarationChamp extends Declaration {

	
	
	public DeclarationChamp(String s, String t, ListeIdentifiant li, int n) {
		super(n);
		for(String i : li) {
			try {
				TDS.getInstance().ajouter(s, t, i);
			} catch (DoubleDeclarationException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/* Pas besoin de toMIPS comme ce sont des déclarations, il faut juste ajouter les variables àla TDS */

}