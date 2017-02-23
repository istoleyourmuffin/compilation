package plic.arbre;

import plic.exceptions.AnalyseSemantiqueException;

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
        return noLigne ;
}
    
    public abstract void verifier() throws AnalyseSemantiqueException ;
    public abstract String toMIPS() throws AnalyseSemantiqueException ; 

}
