/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121					   		 *
* Arquivo HealthPotion.java: Contém a implementação da classe HealthPotion.			 *
*************************************************************************************************/
package Items;

public class HealthPotion extends Potion
{
	public HealthPotion(String name, double price, int restorepts)
	{
		super(name,price,restorepts);				
	}
	
	@Override
	public void use()
	{
		owner.addHP(getRestorePts());
		owner.deleteItem(this);
	}
};
