package plic.arbre.declaration;

import plic.exceptions.AnalyseSemantiqueException;
import plic.tds.TDS;

public class DeclarationChamp extends Declaration {

	
	
	public DeclarationChamp(String s, String t, ListeIdentifiant li, int n) {
		super(n);
		for(Identifiant i : li) {
			try {
				TDS.getInstance().ajouter(s, t, i.getNom());
			} catch (AnalyseSemantiqueException e) {
				System.err.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : Double déclaration de la variable " + i.getNom());
			}
		}
		
	}
	
	public DeclarationChamp(String t, ListeIdentifiant li, int n) {
		super(n);
		for(Identifiant i : li) {
			try {
				TDS.getInstance().ajouter(t, i.getNom());
			} catch (AnalyseSemantiqueException e) {
				System.err.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : Double déclaration de la variable " + i.getNom());
			}
		}
		
	}
	
	/* Pas besoin de toMIPS comme ce sont des déclarations, il faut juste ajouter les variables à la TDS */

}
