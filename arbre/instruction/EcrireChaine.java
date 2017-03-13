package plic.arbre.instruction;

public class EcrireChaine extends Ecrire {

	protected String chaine;
	
	public EcrireChaine(String s, int n) {
		super(n);
		chaine = s;
	}

	public String toMIPS() {
		String indice = getNoLigne()+ "" + (int)(10000 + (Math.random() * (100000 - 10000))); // Nombre aléatoire entre 10000 et 99999
		String nom = "ecrireChaine"+ indice;
		StringBuilder sb = new StringBuilder();
		sb.append("# ----- EcrireChaine ----- \n");
		sb.append("#On insere la chaine dans les donnees\n");
		sb.append(".data \n");
		sb.append(nom);
		sb.append(":	.asciiz ");
		sb.append(chaine);
		sb.append("\n#On ecrit maintenant la chaine\n");
		sb.append(".text\n");
		sb.append("li $v0, 4 # $v0 <- code du print\n");
		sb.append("la $a0, ");
		sb.append(nom);
		sb.append(" # $a0 <- adresse de la chaine � ecrire\n");
		sb.append("syscall 	# afficher\n");
		return sb.toString();
	}

	public boolean verifier() {
		return true;
	}
}