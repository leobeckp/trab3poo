import java.net.*;
import java.util.*;
import Server.*;
import Items.*;

public class Main
{
	public static void main(String[] args)
	{
		String line = "";
		Server.start();
		Database.initDatabases();
		while(!line.contains("exit"))
		{
			try
			{
				switch(line)
				{
					case "conns":
						System.out.println("Jogadores conectados: "+Server.getPlayersList().size());
					break;
				}
				line = System.console().readLine();				
			}
			catch(Exception e)
			{
				Log.writeError("Erro ao realizar comando.",e);			
			}			
		}
		Server.shutdown();
		System.exit(0);
	}	
}
