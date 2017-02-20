package plic.arbre.declaration;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeIdentifiant implements Iterable<Identifiant> {
	
	protected ArrayList<Identifiant> ali;
	
	public ListeIdentifiant() {
		ali = new ArrayList<Identifiant>();
	}
	
	public void ajouter(Identifiant s) {
		ali.add(s);
	}
	
	public int getSize() {
		return ali.size();
	}

	@Override
	public Iterator<Identifiant> iterator() {
		return ali.iterator();
	}

}
