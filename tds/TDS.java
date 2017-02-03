package plic.tds;

import java.util.HashMap;

import plic.exceptions.DoubleDeclarationException;

public class TDS {
	
	private static TDS instance = new TDS();
	protected HashMap<String,Symbole> table;
	protected int tailleZoneVariables;
	
	public static TDS getInstance() {
		return instance;
	}
	
	public void ajouter(String e, Symbole s) throws DoubleDeclarationException {
		if(table.containsKey(e)) {
			throw new DoubleDeclarationException("Double declaration");
		}
		table.put(e, s);
	}
	
	public Symbole identifier(String e) {
		return table.get(e);
	}
	
	public int getTailleZoneVariables() {
		return table.size()*4;
	}
	
	private TDS() {
		this.tailleZoneVariables = 0;
		this.table = new HashMap<String,Symbole>();
		
	}

}
