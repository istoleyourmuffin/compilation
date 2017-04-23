package plic ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import plic.analyse.AnalyseurLexical;
import plic.analyse.AnalyseurSyntaxique;
import plic.arbre.ArbreAbstrait;
import plic.arbre.BlocDInstructions;
import plic.exceptions.AnalyseException;
import plic.tds.TDS;

/**
 * 24 mars 2015 
 * 
 * @author brigitte wrobel-dautcourt
 */

public class Plic {
	
    public Plic(String fichier, String classeRacine) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
            boolean valide = TDS.getInstance().verifierClasse(classeRacine);
            if (!valide) {
            	System.out.println("ERREUR: classe racine non trouvee");
            }

            //BlocDInstructions arbreBis = (BlocDInstructions) arbre;
            //arbreBis.setClasseRacine(classeRacine);
            valide = arbre.verifier() && valide;
            
            if (valide) {
	            /* récupération du no du fichier */
	            String[] name = fichier.split(".plic"); 
	            /* ecriture dans le fichier .mips */
	            FileWriter fw = new FileWriter(name[0] + ".mips");
	            fw.write(arbre.toMIPS());
	            fw.flush();
	            fw.close();
	            
	            System.out.println("COMPILATION OK");
            }
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + fichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Plic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar plic.jar <fichierSource.plic> <classeRacine>") ;
            System.exit(1) ;
        }
        new Plic(args[0], args[1]) ;
    }
    
}
