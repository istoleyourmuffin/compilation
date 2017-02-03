package plic.arbre.declaration;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeIdentifiant implements Iterable<String> {
	
	protected ArrayList<String> ali;
	
	public ListeIdentifiant() {
		ali = new ArrayList<String>();
	}
	
	public void ajouter(String s) {
		ali.add(s);
	}
	
	public int getSize() {
		return ali.size();
	}

	@Override
	public Iterator<String> iterator() {
		return ali.iterator();
	}
}
