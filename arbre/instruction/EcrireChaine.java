package plic.arbre.instruction;

public class EcrireChaine extends Ecrire {

	protected String chaine;
	
	public EcrireChaine(String s, int n) {
		super(n);
		chaine = s;
	}

	public String toMIPS() {
		String nom = "ecrireChaine"+getNoLigne()+(int)(Math.random()*10000);
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