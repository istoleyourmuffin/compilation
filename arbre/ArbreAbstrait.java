package plic.arbre;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class ArbreAbstrait {
    
	protected int noLigne;
	
    protected ArbreAbstrait(int no) {
    	noLigne = no;
    }
    
    public int getNoLigne() {
        return noLigne;
}
    
    public abstract boolean verifier();
    public abstract String toMIPS(); 

}
