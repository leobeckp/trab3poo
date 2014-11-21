/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121					   		 *
* Arquivo HealthPotion.java: Contém a implementação da classe HealthPotion.			 *
*************************************************************************************************/
package Items;

public class HealthPotion extends Potion implements java.io.Serializable
{
	public HealthPotion(String name, double price, int restorepts)
	{
		super(name,price,restorepts);				
	}
	
	@Override
	public void use()
	{
		getOwner().addHP(getRestorePts());
		getOwner().deleteItem(this);
	}
};
