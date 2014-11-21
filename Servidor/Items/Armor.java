/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Armor.java: Contém a implementação da classe Armor.					 *
*************************************************************************************************/
package Items;

public class Armor extends Item implements java.io.Serializable
{
	protected int defensepts;
	protected double weight;	

	public Armor(String name, double price, int defensepts, double weight)
	{
		super(name,price,0,defensepts);
		this.defensepts = defensepts;
		this.weight = weight;		
	}
	public Armor(Armor armor)
	{
		super(armor);
		this.defensepts = armor.defensepts;
		this.weight = armor.weight;
	}
	@Override
	public int getAttackPts()
	{
		return 0;
	}
	@Override
	public int getDefensePts()
	{
		return defensepts;
	}	
	
	public double getWeigth()
	{
		return weight;
	}
	@Override
	public String className()
	{
		return "Armor";
	}
};
