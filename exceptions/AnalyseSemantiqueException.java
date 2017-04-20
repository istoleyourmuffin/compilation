package plic.exceptions;

@SuppressWarnings("serial")
public class AnalyseSemantiqueException extends AnalyseException {
 
    public AnalyseSemantiqueException(int ligne, String m) {
        super("ERREUR SEMANTIQUE : ligne "+ ligne + " : " + m) ;
    }

}
