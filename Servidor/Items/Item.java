/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Item.java: Contém a implementação da classe Item.					 *
*************************************************************************************************/
package Items;
import Game.*;
import Server.*;

public abstract class Item implements java.io.Serializable
{
	private String name;
	private double price;
	protected int owner;	

	public Item(String name, double price, int attackpts, int defensepts)
	{
		this.name = name;
		this.price = price;		
	}
	
	public Item(Item item)
	{
		this.name = item.name;
		this.price = item.price;
	}
	public String getName()
	{
		return name;
	}
	public abstract int getAttackPts();	
	public abstract int getDefensePts();
	
	public double getPrice()
	{
		return price;
	}
	public double getWeight()
	{
		return 0d;
	}
	public void use()
	{
		
	}
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	public GameCharacter getOwner()
	{
		return Database.charactersDb.getEntryById(owner);
	}
	public String className()
	{
		return "Item";
	}
};
