/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Thief.java: Contém a implementação da classe Thief.	 			 	 *
*************************************************************************************************/
package Game;
import Items.*;
import java.util.*;

public class Thief extends GameCharacter
{
	protected int stealth;	

	public Thief(String alias, int stealth)
	{
		super(alias);
		this.stealth = stealth;
	}
	@Override
	protected int getDefensePoints()
	{
		return super.getDefensePoints();
	}
	@Override
	protected int getAttackPoints()
	{
		return super.getAttackPoints() + stealth;
	}
	@Override
	public void attack(GameCharacter target)
	{
		System.out.println("\""+getName() + "\" ataca \"" + target.getName()+"\"");
		//Calcula MISS
		double missChance = 0.1/XP;
		double roll = RandomDouble(0,1);
	
		if(roll <= missChance)
		{
			System.out.println("MISS: O alvo \""+ target.getName() +"\" esquivou-se do ataque!");			
			return;
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
			System.out.println("CRITICAL STRIKE: O dano de \"" + getName() + "\" foi multiplicado por 2!");			
			damage *= 2;
		}
	
		target.inflictDamage(damage);
	}
	
	public void addStealth(int amount)
	{
		stealth += amount;
		System.out.println("Stealth de \"" + getName() + "\" alterada para " + stealth);		
	}
	
	@Override
	public String className()
	{
		return "Knight";
	}	
};
