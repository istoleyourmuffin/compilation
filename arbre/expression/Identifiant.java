package plic.arbre.expression;

import plic.exceptions.AnalyseSemantiqueException;
import plic.exceptions.AnalyseSyntaxiqueException;
import tds.Symbole;
import tds.TDS;

public class Identifiant extends Expression {

	protected String identifiant;
	protected Symbole symbole;
	
	protected Identifiant(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# chargement de '" + identifiant + "' dans $v0\n");
		sb.append("lw $v0, (-"+ symbole.getDeplacement() + ")$s7 \n");
		
		return sb.toString();
	}

	@Override
	public void verifier() throws AnalyseSyntaxiqueException {
		symbole = TDS.getInstance().identifier(this);
		if(symbole == null) {
			throw new AnalyseSemantiqueException(identifiant + "non déclaré \n");
		}
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
