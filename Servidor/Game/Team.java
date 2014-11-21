/*************************************************************************************************
* Autor: Leonardo Beck Prates, Nº USP: 7962121							 *
* Arquivo Team.java: Contém a implementação da classe Team.	 				 *
*************************************************************************************************/
package Game;
import java.util.*;
import Server.*;

public class Team implements java.io.Serializable
{
	private String name;
	private Color color;
	private int win;
	private int lose;
	private int draw;
	private ArrayList<Integer> characters;
	public Team(String name, Color color)
	{
		this.name = name;
		this.color = color; 
		this.win = 0;
		this.lose = 0;
		this.draw = 0;
		this.characters = new ArrayList<Integer>();
	}

	public String getName()
	{
		return name;
	}
	public String getResults()
	{
		return "Win: " + win + " " + "Lose: " + lose + " " + "Draw: " + draw;		
	}
	public String getResultsPattern()
	{
		return win + "," + lose + ","+ draw;
	}
	public String getCharsPattern()
	{
		ArrayList<GameCharacter> chars = getCharacters();
		String s = "";
		for(GameCharacter cha : chars)
		{
			if(!s.equals(""))
				s += ";";
			s += cha.getName()+","+cha.className();			
		}
		return s;
	}
	public String toString()
	{
		return "Name: " + name + ", " + "Color: " + color;	
	}
	public void fightTeam(Team team)
	{
		int posA = 0;
		int posB = 0;	
		ArrayList<GameCharacter> chars = getCharacters();
		for(int i = 0; i < 8; i++)
		{
			if(i%2 == 0)
			{
				GameCharacter attacker = chars.get(posA);
				if(attacker.getHP() <= 0)
					continue;
				GameCharacter attacked = team.getCharacters().get(posA);
				attacker.attack(attacked);
				posA++;
			}
			else
			{
				GameCharacter attacker = team.getCharacters().get(posB);
				if(attacker.getHP() <= 0)
					continue;
				GameCharacter attacked = chars.get(posB);
				attacker.attack(attacked);
				posB++;
			}
			System.out.println("");
		}
	}
	public void resolveBattle(Team team)
	{
		if(getPoints() > team.getPoints())
		{
			win++;
		}
		else if(getPoints() < team.getPoints())
		{
			lose++;
		}
		else
		{
			draw++;
		}
	}
	public void addChar(int charac)
	{
		characters.add(charac);
	}
	public void removeChar(int pos)
	{
		if(pos < 0 || pos >= characters.size())
			return;

		characters.remove(pos);	

	}
	public void removeChar(GameCharacter charac)
	{
		int pos = -1;
		ArrayList<GameCharacter> chars = getCharacters();
		for(int i = 0; i < chars.size();i++)
		{
			if(charac.getName() == chars.get(i).getName())
			pos = i;
		}
		if(pos != -1)
		{
			removeChar(pos);
		}
	}
	public GameCharacter searchChar(String name)
	{
		ArrayList<GameCharacter> chars = getCharacters();
		for(int i = 0; i < chars.size();i++)
		{
			if(name == chars.get(i).getName())
				return chars.get(i);
		}

		return null;	
	}
	public double getPoints()
	{
		double hp = 0;
		for(int i = 0; i < characters.size();i++)
		{
			hp += getCharacters().get(i).getHP();
		}

		hp = hp/characters.size();

		return hp;
	}
	public ArrayList<GameCharacter> getCharacters()
	{
		ArrayList<GameCharacter> chars = new  ArrayList<GameCharacter>();
		
		for(int i : characters)
		{
			chars.add(Database.charactersDb.getEntryById(i));
		}
		
		return chars;
	}
};
