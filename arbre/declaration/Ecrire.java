package plic.arbre.declaration;

public abstract class Ecrire extends Declaration {

	public Ecrire(int n) {
		super(n);
	}

}


/* Reste à faire : 
   ECRIRE 	::= ECR EXPR:e POINTVIRGULE
		{: 	EcrireExpression ee= new EcrireExpression(e, eleft+1);
			RESULT = ee ; :}	
		|
			ECR CONSTANTECHAINE:c POINTVIRGULE
		{: 	EcrireChaine ec= new EcrireChaine(c, eleft+1);
			RESULT = ec ; :}
			
 */
