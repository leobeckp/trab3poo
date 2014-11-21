package Server;
import Game.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Parsing
{
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
							
							Account acc = Database.accountsDb.getEntryByField("username", username);
					
							if(acc != null)
							{
								if(acc.getPassword().equals(pass))
								{
									plr.sendData("OK");
									plr.setAccount(acc);																
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
								acc = Database.accountsDb.getEntryByField("username", username);
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
								System.out.println("a");
								if(plr.getAccount().getTeamId() == -1)
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
