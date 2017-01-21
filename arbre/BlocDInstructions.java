package plic.arbre;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArbreAbstrait expr ;
    
    public BlocDInstructions(int n) {
        super(n) ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        expr = a ;
    }
    
    @Override
    public String toString() {
        return expr.toString() ;
    }

	@Override
	public String toMIPS() {
		return expr.toMIPS() 
				+ "end :\n"
				+ "move $v1, $v0	# copie de v0 dans v1 pour permettre les tests de plic0\n"
				+ "li $v0, 10		# retour au système\n"
				+ "syscall\n";
	}

	@Override
	public void verifier() {
		expr.verifier();
		
	}

}
