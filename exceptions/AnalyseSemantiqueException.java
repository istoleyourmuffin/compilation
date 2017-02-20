package plic.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {
 
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE : ligne "+ ligne + " : " + m) ;
    }

}
