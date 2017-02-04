package plic.arbre;

import plic.tds.TDS;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArbreAbstrait classe ;
    
    public BlocDInstructions(int n) {
        super(n) ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        classe = a ;
    }
    
    @Override
    public String toString() {
        return classe.toString() ;
    }

	@Override
	public String toMIPS() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("main :");
		sb.append("# initialiser s7 avec sp (initialisation de la base des variables)");
		sb.append("move $s7,$sp");
		int size = TDS.getInstance().getSize();
		sb.append("# réservation de l'espace pour " + size + " variables");
		sb.append("addi $sp, $sp, -" + size + "\n");
		
		sb.append(classe.toMIPS());
		
		sb.append("end :\n");
		sb.append("move $v1, $v0	# copie de v0 dans v1 pour permettre les tests de plic0\n");
		sb.append("li $v0, 10		# retour au système\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}

	@Override
	public void verifier() {
		classe.verifier();
		
	}

}
