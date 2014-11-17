/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Inventory.java: Contém a implementação da classe Inventory.	 			 *
*************************************************************************************************/

package Items;
import java.util.*;
import java.lang.Math.*;
import Items.Item;

public class Inventory
{
	private int spaces;
	private double gold;
	private ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> items;

	public Inventory()
	{
		gold = 0;
		spaces = 10;
		items = new ArrayList<AbstractMap.SimpleEntry<Item,Boolean>>();
	}
	public double getTotalGold()
	{
		return gold;
	}
	public int getAvailableSpace()
	{
		return spaces - items.size();
	}
	public void spendGold(double amount)
	{
		if(amount <= 0)//Valor inválido
		{
			System.out.println("Valor gasto de gold invalido!!!");			
			return;
		}

		if(amount > gold)
			amount = gold;

		gold -= amount;
	}
	public void earnGold(double amount)
	{
		if(amount <= 0)//Valor inválido
		{
			System.out.println("Valor ganho de gold invalido!!!");
			return;
		}
		gold += amount;
	}
	public void setSpaces(int spaces)
	{
		this.spaces = spaces;
	}
	public Item searchItem(String name)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getKey().getName() == name)
			return items.get(i).getKey();
		}
	
		return null;
	}
	public Item searchItem(int pos)
	{
		if(pos > (items.size() - 1) || pos < 0)
			return null;
	
		return items.get(pos).getKey();
	}
	public void insertItem(Item item)
	{
		if(getAvailableSpace() <= 0)
		{
			System.out.println("Inventario cheio! Item nao adicionado!!!");			
			return;
		}
		items.add(new AbstractMap.SimpleEntry<Item,Boolean>(item, false));
	}
	public void removeItem(String name)
	{
		int pos = -1;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getKey().getName() == name)
				pos = i;
		}
		if(pos > 0)
			items.remove(pos);
	}
	public void removeItem(int pos)
	{
		if(pos > (items.size() - 1) || pos < 0)
			return;
		items.remove(pos);
	}
	public int getItemsDefensePoints()
	{
		int amount = 0;
	
		for(int i = 0; i < items.size(); i++)
		{	
			if(items.get(i).getValue())
				amount += items.get(i).getKey().getDefensePts();
		}
	
		return amount;
	}
	public int getItemsAttackPoints()
	{
		int amount = 0;
	
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getValue())	
				amount += items.get(i).getKey().getAttackPts();
		}
	
		return amount;
	}
	public void deleteInventory()
	{
		for(int i = 0;i < items.size();i++)
		{
			items.remove(i);
		}
	}
	public double getArmorWeight()
	{
		double amount = 0.0;
	
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getValue())
				amount += Math.exp(-items.get(i).getKey().getWeight() * items.get(i).getKey().getWeight());
		}
		return amount;	
	}
	public void equipItem(Item item)
	{
		if(item.className() == "Armor" || item.className() == "Weapon")
		{
			int eArmor = 0, eWeapon = 0;
			int pos = -1;
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getKey().className() == "Armor")
				{
					if(eArmor > 0)				
						items.get(i).setValue(false);				
					else				
						eArmor++;				
				}
				if(items.get(i).getKey().className() == "Weapon")
				{
					if(eWeapon > 1)				
						items.get(i).setValue(false);				
					else			
						eWeapon++;				
				}
				if(items.get(i).getKey() == item)
				{
					pos = i;
				}
			}
			if(pos != -1)
			{
				items.get(pos).setValue(true);
				System.out.println("Item \"" + items.get(pos).getKey().getName() + "\" equipado em \"" + items.get(pos).getKey().getOwner().getName() + "\"");
			}
		
		}
	}
};
