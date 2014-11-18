/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121					   		 *
* Arquivo ManaPotion.java: Contém a implementação da classe ManaPotion.				 *
*************************************************************************************************/
package Items;

public class ManaPotion extends Potion
{
	public ManaPotion(String name, double price, int restorepts)
	{
		super(name,price,restorepts);				
	}
	
	@Override
	public void use()
	{
		owner.addMP(getRestorePts());
		owner.deleteItem(this);
	}
};
