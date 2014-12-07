package Server;
import Game.*;
import Items.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Parsing
{
	private static int Random(int min, int max)
	{
		Random rand = new Random();    
   		return rand.nextInt((max - min) + 1) + min;
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
	public static void parseData(Player plr, String data)
	{
		try
		{
			switch(data.charAt(0))
			{
				case 'A'://Pacotes relativos ao login e cadastro
					switch(data.charAt(1))
					{
						case 'A'://Login		
							String[] sptData = data.substring(2).split("\\|");
							
							String username = sptData[0];							
							String pass = sptData[1];							
							
							Account acc = Database.accountsDb.getEntryByField("username", username);//Procura se existe uma conta com o usuário fornecido.
					
							if(acc != null)
							{
								if(acc.getPassword().equals(pass))
								{
									Player logged = null;
									for(Player i: Server.getPlayersList())
									{
										if(i.getAccount() == acc)
											logged = i;
									}
									if(logged != null)
									{
										plr.sendData("LOGGED");
										plr.disconnect();
									}
									else
									{
										plr.sendData("OK");
										plr.setAccount(acc);
									}																									
								}
								else
								{
									plr.sendData("INVALID");
									plr.disconnect();
								}
							}
							else
							{
								plr.sendData("INVALID");
								plr.disconnect();
							}
						break;
						case 'B': //Registro
							sptData = data.substring(2).split("\\|");
							username = sptData[0];							
							pass = sptData[1];
							String cpass = sptData[2];
							
							if(username.length() == 0 || pass.length() == 0 || cpass.length() == 0)
							{
								plr.sendData("APreencha todos os campos!");
								plr.disconnect();
								return;
							}
							if(username.length() < 4 || username.length() > 16)
							{
								plr.sendData("AO usuário deve conter entre 4 e 16 letras!");
								plr.disconnect();
								return;
							}
							if(pass.equals(cpass))
							{
								acc = Database.accountsDb.getEntryByField("username", username);//Procura se existe uma conta com o usuário fornecido.
								if(acc == null)
								{
									acc = new Account(username, pass, -1);
									Database.accountsDb.insertEntry(acc);									
									Database.accountsDb.saveDatabase();
									plr.sendData("K");	
									plr.disconnect();									
								}
								else
								{
									plr.sendData("ELogin já existente!");
									plr.disconnect();
								}
							}
							else
							{
								plr.sendData("AAs senhas não correspondem!");
								plr.disconnect();
							}
							
						break;
					}					
				break;
				case 'B': //Pacotes relativos ao jogo.
					switch(data.charAt(1))
					{
						case 'A'://Enviar informações do time
							if(plr.getAccount() != null)
							{
								if(plr.getAccount().getTeamId() == -1)//Sem time criado
								{
									plr.sendData("BA-1");
								}
								else
								{
									Team team = plr.getAccount().getTeam();
									plr.sendData("BA"+team.getName()+"|"+team.getResultsPattern()+"|"+team.getCharsPattern());
								}
							}
						break;
						case 'B'://Criar time
							if(plr.getAccount() != null)
							{
								if(plr.getAccount().getTeamId() == -1)
								{
									String[] sptData = data.substring(2).split("\\|");
									String teamName = sptData[0];
									Team team = new Team(teamName, Color.red);
									
									int teamId = Database.teamsDb.insertEntry(team);
									
									plr.getAccount().setTeamId(teamId);
									Database.accountsDb.saveDatabase();
									
									
									for(String s : sptData[1].split("\\;"))
									{
										String[] charData = s.split("\\,");
										String name = charData[0];
										String race = charData[1];
										GameCharacter cha = null;
										switch(race)
										{
											case "Knight":
												cha = new Knight(name, 0);
											break;
											case "Thief":
												cha = new Thief(name, 0);
											break;
											case "Wizard":
												cha = new Wizard(name, 0);
											break;
										}
										if(cha != null)
										{
											int id = Database.charactersDb.insertEntry(cha);
											Database.charactersDb.saveDatabase();
											team.addChar(id);
											
											Armor armor = new Armor(RandomArmorName(),Random(20,100), Random(2,4), Random(1,20));
											cha.addItem(armor);
											cha.equipItem(armor);
											Database.itemsDb.insertEntry(armor);
											
											Weapon weapon = new Weapon(RandomWeaponName(),Random(20,100), Random(8,9), 1);
											cha.addItem(weapon);
											cha.equipItem(weapon);
											Database.itemsDb.insertEntry(weapon);
											
											Potion hp = new HealthPotion("Health Potion",Random(20,100), 100);
											
											cha.addItem(hp);
											Database.itemsDb.insertEntry(hp);
											Database.itemsDb.saveDatabase();
										}
										else
										{
											plr.sendData("BBEDados inválidos para criar time!");
											return;
										}
									}
									Database.teamsDb.saveDatabase();
									plr.sendData("BBK");
									parseData(plr, "BA");
								}
								else
								{
									plr.sendData("BBEVocê já possui um time!");
								}
							}
						break;
						case 'C'://Informações de personagem
							if(plr.getAccount() != null)
							{
								String name = data.substring(2);
								Team team = plr.getAccount().getTeam();
								GameCharacter cha = team.searchChar(name);
								if(cha != null)
								{
									plr.sendData("BC"+cha.getHP()+"|"+cha.className());
								}
							}
						break;						
					}
				break;
				case 'C'://Pacotes relativos a batalha
					switch(data.charAt(1))
					{
						case 'A'://Definir pronto ou não para batalhas.
							if(plr.getAccount() != null)
							{
								if(plr.getAccount().getTeamId() == -1)
								{
									plr.sendData("CAEVocê não possui um time para batalhar.");
									return;
								}
								plr.setReady(data.substring(2).equals("1"));
								Player opo = Server.searchOpponent(plr);
								if(opo != null)
								{
									plr.fightPlayer(opo);
								}
								else
								{
									plr.sendData("CA"+(plr.isReady() == true?"1":"0"));
								}
							}	
						break;						
					}					
				break;
				case 'D'://Pacotes relacionados ao inventário.
					switch(data.charAt(1))
					{
						case 'A'://Listar itens do jogador
							if(plr.getAccount() != null)
							{
								String name = data.substring(2);
								Team team = plr.getAccount().getTeam();
								GameCharacter cha = team.searchChar(name);
								if(cha != null)
								{
									String itens = "";
									for(AbstractMap.SimpleEntry<Item,Boolean> i : cha.getInventory().getItems())
									{
										if(!itens.equals(""))
											itens += "|";
										itens += i.getKey().getName();
									}
									plr.sendData("DA"+itens);
								}
							}
						break;
					}
				break;
			}
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao realizar parsing do pacote recebido.", e);
			plr.sendData("EErro ao processar comando.");
		}
	}
}
