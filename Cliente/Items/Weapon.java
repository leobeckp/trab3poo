/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Weapon.java: Contém a implementação da classe Weapon.					 *
*************************************************************************************************/
package Items;

public class Weapon extends Item
{
	protected int attackpts;
	protected double range;	

	public Weapon(String name, double price, int attackpts, double range)
	{
		super(name,price,attackpts,0);
		this.attackpts = attackpts;
		this.range = range;		
	}
	public Weapon(Weapon weapon)
	{
		super(weapon);
		this.attackpts = weapon.attackpts;
		this.range = weapon.range;
	}
	@Override
	public int getAttackPts()
	{
		return attackpts;
	}
	@Override
	public int getDefensePts()
	{
		return 0;
	}	
	
	public double getRange()
	{
		return range;
	}
	@Override
	public String className()
	{
		return "Weapon";
	}
};
