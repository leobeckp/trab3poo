/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Inventory.java: Contém a implementação da classe Inventory.	 			 *
*************************************************************************************************/

package Items;
import java.util.*;
import java.lang.Math.*;
import Items.Item;
import Server.*;

public class Inventory implements java.io.Serializable
{
	private int spaces;
	private double gold;
	private ArrayList<AbstractMap.SimpleEntry<Integer,Boolean>> items;

	public Inventory()
	{
		gold = 0;
		spaces = 10;
		items = new ArrayList<AbstractMap.SimpleEntry<Integer,Boolean>>();
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
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		for(int i = 0; i < items.size(); i++)
		{
			if(ritems.get(i).getKey().getName() == name)
			return ritems.get(i).getKey();
		}
	
		return null;
	}
	public Item searchItem(int pos)
	{
		if(pos > (items.size() - 1) || pos < 0)
			return null;
			
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		
		return ritems.get(pos).getKey();
	}
	public void insertItem(Item item)
	{
		if(getAvailableSpace() <= 0)
		{
			System.out.println("Inventario cheio! Item nao adicionado!!!");			
			return;
		}
		int itemId = Database.itemsDb.insertEntry(item);
		Database.itemsDb.saveDatabase();
		
		items.add(new AbstractMap.SimpleEntry<Integer,Boolean>(itemId, false));
	}
	public void removeItem(String name)
	{
		int pos = -1;
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		for(int i = 0; i < items.size(); i++)
		{
			if(ritems.get(i).getKey().getName() == name)
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
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		for(int i = 0; i < items.size(); i++)
		{	
			if(items.get(i).getValue())
				amount += ritems.get(i).getKey().getDefensePts();
		}
	
		return amount;
	}
	public int getItemsAttackPoints()
	{
		int amount = 0;
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getValue())	
				amount += ritems.get(i).getKey().getAttackPts();
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
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		for(int i = 0; i < items.size(); i++)
		{
			if(ritems.get(i).getValue())
				amount += Math.exp(-ritems.get(i).getKey().getWeight() * ritems.get(i).getKey().getWeight());
		}
		return amount;	
	}
	public ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> getItems()
	{
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = new ArrayList<AbstractMap.SimpleEntry<Item,Boolean>>();
		for(AbstractMap.SimpleEntry<Integer,Boolean> entry : items)
		{
			ritems.add(new AbstractMap.SimpleEntry<Item,Boolean>(Database.itemsDb.getEntryById(entry.getKey()), entry.getValue()));
		}
		return ritems;
	}
	public void equipItem(Item item)
	{
		ArrayList<AbstractMap.SimpleEntry<Item,Boolean>> ritems = getItems();
		if(item.className() == "Armor" || item.className() == "Weapon")
		{
			int eArmor = 0, eWeapon = 0;
			int pos = -1;
			for(int i = 0; i < items.size(); i++)
			{
				if(ritems.get(i).getKey().className() == "Armor")
				{
					if(eArmor > 0)				
						items.get(i).setValue(false);				
					else				
						eArmor++;				
				}
				if(ritems.get(i).getKey().className() == "Weapon")
				{
					if(eWeapon > 1)				
						items.get(i).setValue(false);				
					else			
						eWeapon++;				
				}
				if(ritems.get(i).getKey() == item)
				{
					pos = i;
				}
			}
			if(pos != -1)
			{
				items.get(pos).setValue(true);
				System.out.println("Item \"" + ritems.get(pos).getKey().getName() + "\" equipado em \"" + ritems.get(pos).getKey().getOwner().getName() + "\"");
			}
		
		}
	}
};
