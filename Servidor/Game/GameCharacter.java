/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Character.java: Contém a implementação da classe Character.	 			 *
*************************************************************************************************/
package Game;
import Items.*;
import java.util.*;


public abstract class GameCharacter implements java.io.Serializable
{
	private String alias;
	private	Inventory myitems;
	private	int HP;
	private int MP;
	private int id;
	protected int XP;
	protected int strength;
	protected int speed;
	protected int dexterity;
	protected int constitution;
	protected int accuracy;	

	protected double RandomDouble(double min, double max)
	{
		Random rand = new Random();
		return min + (max - min) * rand.nextDouble();
	}
	protected int RandomInt(int min, int max)
	{
		Random rand = new Random();    
   		return rand.nextInt((max - min) + 1) + min;    
	}	
	public GameCharacter(String alias)
	{
		this.alias = alias;
		this.XP = 1;
		this.HP = 100;
		this.MP = 100;
		this.myitems = new Inventory();
		this.speed = 0;
		this.strength = 0;
		this.dexterity = 0;
		this.constitution = 0;
	}
	protected int getDefensePoints()
	{
		return (int)((((int)(constitution * 0.5 + dexterity * 0.3 + getSpeed() * 0.2)) + myitems.getItemsDefensePoints()) * (XP/2));
	}
	protected int getAttackPoints()
	{
		return (int)((((int)(strength * 0.5 + dexterity * 0.3+ getSpeed() * 0.2)) + myitems.getItemsAttackPoints()) * (XP/3));
	}
	public String getName()
	{
		return alias;
	}
	public abstract ArrayList<String> attack(GameCharacter target);
	
	public void addXP(int amount)
	{
		if(amount <= 0)
		{
			System.out.println("Valor invalido de XP adicionado em \"" + getName() + "\"!");			
			return;
		}
	
		XP += amount;
	
		if(XP > 100)
			XP = 100;
	}
	public void setStrength(int amount)
	{
		int others = speed+dexterity+constitution;
	
		if(others >= 100)
			return;
		
		if(amount > (100 - others))
			amount = (100 - others);
		
		strength = amount;
		System.out.println("Strength de \"" + getName() + "\" alterada para " + strength);		
	}
	public void setSpeed(int amount)
	{
		int others = strength+dexterity+constitution;
	
		if(others >= 100)
			return;
		
		if(amount > (100 - others))
			amount = (100 - others);
		
		speed = amount;
		System.out.println("Speed de \"" + getName() + "\" alterada para " + speed );		
	}
	public void setDexterity(int amount)
	{
		int others = strength+speed+constitution;
	
		if(others >= 100)
			return;
		
		if(amount > (100 - others))
			amount = (100 - others);
		
		dexterity = amount;
		System.out.println("Dexterity de \"" + getName() + "\" alterada para " + dexterity);		
	}
	public void setConstitution(int amount)
	{
		int others = strength+speed+dexterity;
	
		if(others >= 100)
			return;
		
		if(amount > (100 - others))
			amount = (100 - others);
		
		constitution = amount;
		System.out.println("Constitution de \"" + getName() + "\" alterada para " + constitution);		
	}
	protected ArrayList<String> inflictDamage(int amount)
	{
		ArrayList<String> data = new ArrayList<String>();
		
		if(HP <= 0)
		{
			data.add("O personagem \""+getName()+"\" ja esta morto!");
			return data;
		}
	
		data.add("O personagem \"" + getName() + "\"sofreu " + amount + " de dano.");		
	
		HP -= amount;
	
		if(HP <= 0)
		{
			HP = 0;
			data.add("O personagem \""+ alias + "\" foi morto!");			
			return data;
		}
		
		return data;
	}
	public void setAccuracy(int amount)
	{
		if(amount > 100)
			amount = 100;
		if(amount < 30)
			amount = 30;
		
		accuracy = amount;
	
		System.out.println("Accuracy de \"" + getName() + "\" alterada para " + accuracy);		
	}
	public void addItem(Item item)
	{
		myitems.insertItem(item);
		item.setOwner(id);
		System.out.println("Item \""+item.getName()+"\" adicionado em \"" + getName());		
	}
	public void deleteItem(Item item)
	{
		myitems.removeItem(item.getName());
		System.out.println("Item \""+item.getName()+"\" removido de \"" + getName());		
	}
	public int getHP()
	{
		return HP;
	}
	public void deleteCharacter()
	{
		myitems.deleteInventory();
		System.out.println("Personagem " + getName() + " apagado com sucesso!");
	}
	public void addHP(int amount)
	{
		HP += amount;
		if(HP < 0)
			HP = 0;
		if(HP > 100)
			HP = 100;
	}
	public void addMP(int amount)
	{
		MP += amount;		
	}
	public Inventory getInventory()
	{
		return myitems;
	}
	public int getSpeed()
	{
		return (int)(speed * myitems.getArmorWeight());
	}
	public void equipItem(Item item)
	{
		myitems.equipItem(item);
	}	
	public String className()
	{
		return "Character";
	}
};
