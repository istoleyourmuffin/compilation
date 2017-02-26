package plic.arbre.declaration;

import plic.tds.TDS;

public class DeclarationChamp extends Declaration {
	
	protected boolean valide;
	
	public DeclarationChamp(String s, String t, ListeIdentifiant li, int n) {
		super(n);
		valide = true;
		for(Identifiant i : li) {
			if (!TDS.getInstance().verifierExistence(i.getNom())) {
				TDS.getInstance().ajouter(s, t, i.getNom());
			} else {
				System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : Double déclaration de la variable " + i.getNom());
				valide = false;
			}
		}
	}
	
	public DeclarationChamp(String t, ListeIdentifiant li, int n) {
		super(n);
		valide = true;
		for(Identifiant i : li) {
			if (!TDS.getInstance().verifierExistence(i.getNom())) {
				TDS.getInstance().ajouter(t, i.getNom());
			} else {
				System.out.println("ERREUR SEMANTIQUE : ligne " + getNoLigne() + " : Double déclaration de la variable " + i.getNom());
				System.out.println("passage2");
				valide = false;
			}
		}
		
	}
	
	public boolean verifier() {
		return valide;
	}
	
	/* Pas besoin de toMIPS comme ce sont des déclarations, il faut juste ajouter les variables à la TDS */

}
