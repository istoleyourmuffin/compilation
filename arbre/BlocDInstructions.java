package plic.arbre;

import java.util.ArrayList;

import plic.tds.TDS;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> alclasse ;
    
    public BlocDInstructions(int n) {
        super(n) ;
        alclasse = new ArrayList<ArbreAbstrait>();
    }
    
    public void ajouter(ArbreAbstrait a) {
        alclasse.add(a) ;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(ArbreAbstrait c : alclasse) {
    		sb.append(c.toString());
    	}
        return sb.toString();
    }

	@Override
	public String toMIPS() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("main : \n");
		sb.append("# Initialisation de la base des variables \n");
		sb.append("move $s7,$sp \n");
		int size = TDS.getInstance().getTailleZoneDesVariables();
		sb.append("# Réservation de l'espace pour " + size/4 + " variables \n");
		sb.append("addi $sp, $sp, -" + size + "\n");
		
		sb.append("# Mise en place des chaines vrai-faux \n");
		sb.append(".data \n");
		sb.append("boolVrai");
		sb.append(":	.asciiz ");
		sb.append("\"vrai\"\n");
		sb.append("boolFaux");
		sb.append(":	.asciiz ");
		sb.append("\"faux\"\n");
		sb.append(".text\n");
		
		for(ArbreAbstrait c : alclasse) {
    		sb.append(c.toMIPS());
    	}
		
		sb.append("end :\n");
		sb.append("move $v1, $v0	# copie de v0 dans v1 pour permettre les tests de plic0\n");
		sb.append("li $v0, 10		# retour au système\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}

	@Override
	public boolean verifier() {
		boolean valide = true;
		for(ArbreAbstrait c : alclasse) {
    		valide = valide && c.verifier();
    	}
		return valide;	
	}

}
