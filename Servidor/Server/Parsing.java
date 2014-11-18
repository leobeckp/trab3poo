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
				case 'A'://Login
				break;
			}
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao realizar parsing do pacote recebido.", e);
		}
	}
}
