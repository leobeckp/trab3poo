package Server;
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
							String[] sptData = data.substring(2).split("|");
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
							sptData = data.substring(2).split("|");
							username = sptData[0];
							System.out.println("user: "+username);
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
									acc = new Account(username,pass);
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
				
			}
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao realizar parsing do pacote recebido.", e);
		}
	}
}
