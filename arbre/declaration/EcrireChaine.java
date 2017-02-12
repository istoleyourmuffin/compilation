package plic.arbre.declaration;

public class EcrireChaine extends Ecrire {

	String chaine;
	
	public EcrireChaine(String s, int n) {
		super(n);
		chaine = s;
	}

	/* Ne fonctionne peut-etre pas si deux ecrire sont declares sur la meme ligne */
	public String toMIPS() {
		String nom = "ecrireChaine"+getNoLigne();
		StringBuilder sb = new StringBuilder();
		sb.append("# ----- EcrireChaine ----- \n");
		sb.append("#On insert la chaine dans les donnees\n");
		sb.append(".data \n");
		sb.append(nom);
		sb.append(":	.asciiz ");
		sb.append('"'+chaine+'"');
		sb.append("\n#On ecrit maintenant la chaine\n");
		sb.append(".text\n");
		sb.append("li $v0, 4 # $v0 <- code du print\n");
		sb.append("la $a0, ");
		sb.append(nom);
		sb.append("# $a0 <- adresse de la chaine à ecrire\n");
		sb.append("syscall 	# afficher\n");
		return sb.toString();
	}
}