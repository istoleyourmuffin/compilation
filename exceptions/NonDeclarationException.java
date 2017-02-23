package plic.exceptions;

public class NonDeclarationException extends Exception {
 
    public NonDeclarationException(String m) {
    	/* j'ai mis extends exception et pas runtime exception pour ne pas que la compilation s'arrete */
        System.err.println("ERREUR SEMANTIQUE :" + m + " \n") ;
    }
}