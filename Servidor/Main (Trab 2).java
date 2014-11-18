/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Main.java: Uso das classes criadas.							 *
*************************************************************************************************/
import Game.*;
import java.util.*;
import Items.*;

public class Main
{
	private static int Random(int min, int max)
	{
		Random rand = new Random();    
   		return rand.nextInt((max - min) + 1) + min;
	}

	private static char RandomVowel()
	{
		String voyelles = "aeiouy";
		return voyelles.charAt(Random(0, voyelles.length() - 1));
	}
      	private static String RandomArmorName()
	{
		String p1[] = {" de Madeira", " de Ferro", " de Couro", " de Pano"};
		return "Armadura" + p1[Random(0,3)];
	}
	private static String RandomWeaponName()
	{
		String p1[] = {"Cajado", "Adaga", "Espada", "Arco"};
		String p2[] = {" de Madeira", " de Ferro", " de Bronze", " de Ouro"};

		return p1[Random(0, 3)] + p2[Random(0,3)];
	}
	private static char RandomConsonant()
	{
		String consonnes = "bcdfghjklmnpqrstvwxz";
    		return consonnes.charAt(Random(0, consonnes.length() - 1));
	}
	private static String GenerateName()
	{
    		String name;
	           
    		int namelen = Random(5, 10);
    		name = "";

    		name += RandomConsonant();
		name = name.toUpperCase();

    		for (int i = 0; i < namelen - 1; i++)    
			name += ( i % 2 == 1 ) ? RandomConsonant() : RandomVowel();    
    
		return name;
	}
	public static void main(String[] args)
	{
		ArrayList<GameCharacter> Chars = new ArrayList<GameCharacter>();	
	
		int nChars = 8, nArmor = 0, nMana = 0, nHealth = 0;	
		System.out.println("------------------------- PERSONAGENS ---------------------------");
		for(int i = 0;i < nChars;i++)
		{
			int charClass = Random(0, 2);
			switch(charClass)
			{
				case 0:
					Chars.add(new Knight(GenerateName(), Random(10,20)));
				break;
				case 1:
					Chars.add(new Wizard(GenerateName(), Random(10,20)));
				break;
				case 2:
					Chars.add(new Thief(GenerateName(), Random(10,20)));
				break;
			}

			Chars.get(i).setSpeed(Random(15,20));
			Chars.get(i).setDexterity(Random(15,20));
			Chars.get(i).setConstitution(Random(15,20));
			Chars.get(i).setStrength(Random(35,45));

			if(nArmor < 6)
			{
				Armor armor = new Armor(RandomArmorName(),Random(20,100), Random(2,4), Random(1,20));
				Chars.get(i).addItem(armor);
				Chars.get(i).equipItem(armor);
				nArmor++;
			}

			Weapon weapon = new Weapon(RandomWeaponName(),Random(20,100), Random(8,9), 1);
			Chars.get(i).addItem(weapon);
			Chars.get(i).equipItem(weapon);		
		
			if(nMana < 3)
			{
				Chars.get(i).addItem(new ManaPotion("Mana Potion", Random(20,100), 100));
				nMana++;
			}
			if(nHealth < 5)
			{
				Chars.get(i).addItem(new HealthPotion("Health Potion",Random(20,100), 100));
				nHealth++;
			}
			Chars.get(i).addXP(Random(10,15));
			System.out.println("Personagem \"" + Chars.get(i).getName() + "\" (" + Chars.get(i).className() + ") criado!");
			System.out.println("                  ---------------------------                       ");
		}

		for(int i = 0; i < 2; i++)
		{
			Chars.get(Random(0, Chars.size() - 1)).addItem(new Weapon(RandomWeaponName(),Random(20,100), Random(7,9), 1));
		}

		Team teamA = new Team("Bonta", Color.blue);
		Team teamB = new Team("Brak", Color.red);

		System.out.println("----------------------- MONTAGEM DOS TIMES -------------------------");
		for(int i = 0; i < 4; i++)
		{
			teamA.addChar(Chars.get(i));
			System.out.println("\"" + Chars.get(i).getName() + "\" adicionado no time \"" + teamA.getName() + "\"");
		}
		System.out.println("                  ---------------------------                       ");
		for(int i = 0; i < 4; i++)
		{
			teamB.addChar(Chars.get(i + 4));
			System.out.println("\"" + Chars.get(i + 4).getName() + "\" adicionado no time \"" + teamB.getName() + "\"");
		}

	
		System.out.println("------------------------- LOG DA BATALHA ---------------------------");
		teamA.fightTeam(teamB);
		teamA.resolveBattle(teamB);
		teamB.resolveBattle(teamA);	

		System.out.println("--------------------- RESULTADO DA BATALHA -------------------------");
		System.out.println(teamA.toString() + " - " + teamA.getResults());
		System.out.println(teamB.toString() + " - " + teamB.getResults());

		System.out.println("--------------------------------------------------------------------");

		for(int i = 0;i < Chars.size(); i++)
		{
			Chars.get(i).deleteCharacter();			
		}		
	}
};
