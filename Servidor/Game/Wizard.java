/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Wizard.java: Contém a implementação da classe Wizard.	 			 	 *
*************************************************************************************************/
package Game;
import Items.*;
import java.util.*;

public class Wizard extends GameCharacter implements java.io.Serializable
{
	protected int wisdom;	

	public Wizard(String alias, int wisdom)
	{
		super(alias);
		this.wisdom = wisdom;
	}
	@Override
	protected int getDefensePoints()
	{
		return super.getDefensePoints() + wisdom/2;
	}
	@Override
	protected int getAttackPoints()
	{
		return super.getAttackPoints();
	}
	@Override
	public ArrayList<String> attack(GameCharacter target)
	{
		ArrayList<String> data = new ArrayList<String>();
		
		data.add("\""+getName() + "\" ataca \"" + target.getName()+"\"");
		//Calcula MISS
		double missChance = 0.1/XP;
		double roll = RandomDouble(0,1);
	
		if(roll <= missChance)
		{
			data.add("MISS: O alvo \""+ target.getName() +"\" esquivou-se do ataque!");			
			return data;
		}
		//Cacula dano
		int damage = (int)(getAttackPoints() - target.getDefensePoints()) + RandomInt(-5, 5);
		
		if(damage <= 0)
			damage = 1;

		//Calcula CS
		double criticalChance = 0.02 * (XP/2);
		roll = RandomDouble(0,1);
	
		if(roll <= criticalChance)
		{
			data.add("CRITICAL STRIKE: O dano de \"" + getName() + "\" foi multiplicado por 2!");			
			damage *= 2;
		}
	
		data.addAll(target.inflictDamage(damage));
		return data;
	}
	
	public void addWisdom(int amount)
	{
		wisdom += amount;
		System.out.println("Wisdom de \"" + getName() + "\" alterada para " + wisdom);		
	}
	
	@Override
	public String className()
	{
		return "Wizard";
	}	
};
