/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121													 *
* Arquivo Potion.java: Contém a implementação da classe Potion.					 					 *
*************************************************************************************************/
package Items;

public class Potion extends Item
{
	private int restorepts;	

	public Potion(String name, double price, int restorepts)
	{
		super(name,price,0,0);
		this.restorepts = restorepts;			
	}
	public Potion(Potion potion)
	{
		super(potion);
		this.restorepts = potion.restorepts;	
	}
	public int getRestorePts()
	{
		return restorepts;
	}
	@Override
	public int getAttackPts()
	{
		return 0;
	}
	@Override
	public int getDefensePts()
	{
		return 0;
	}	
	@Override
	public void use()
	{
		
	}
};
