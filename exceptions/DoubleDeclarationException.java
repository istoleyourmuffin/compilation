package plic.exceptions;

public class DoubleDeclarationException extends Exception {
 
    public DoubleDeclarationException(String m) {
    	/* j'ai mis extends exception et pas runtime exception pour ne pas que la compilation s'arrete */
        System.out.println("ERREUR SEMANTIQUE :" + m + " \n") ;
    }
}