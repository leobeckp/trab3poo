package Server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server
{
	private static int port = 4000;
	private static String bindIp = "0.0.0.0";
	private static int backlog = 50;
	private static ServerSocket server;
	private static ArrayList<Player> playersList = new ArrayList<Player>();
	private static Thread mainThread;
	private static Boolean isRunning;	
	
	public static void start()
	{
		start(port, bindIp, backlog);
	}
	public static void start(int port)
	{
		Server.port = port;
		start(port, bindIp, backlog);
	}
	public static void start(int port, String bindIp)
	{
		Server.port = port;
		Server.bindIp = bindIp;
		start(port, bindIp, backlog);
	}
	public Player searchOpponent(){
		for(Player i:playersList)
			if(i.isReady())
				return i;
	}
	public static void start(int port, String bindIp, int backlog)
	{
		Server.port = port;
		Server.bindIp = bindIp;
		Server.backlog = backlog;
		
		try
		{
			System.out.print("Iniciando servidor...");
			server = new ServerSocket(port, backlog, InetAddress.getByName(bindIp));
			ServerMainThread main_c = new ServerMainThread();
			isRunning = true;
			mainThread = new Thread(main_c);
			mainThread.start();
			System.out.println("OK!");
		}
		catch(Exception e)
		{
			System.out.println("ERRO!");
			Log.writeError("Erro ao iniciar servidor.", e);
		}
	}
	public static void shutdown()
	{
		try
		{
			isRunning = false;
			for(Player plr : playersList)
			{
				plr.disconnect();
			}
			try
			{
				mainThread.interrupt();
			}	
			catch(Exception f)
			{
			}			
		}
		catch(Exception e)
		{
			
		}
	}
	public static Boolean getIsRunning()
	{
		return isRunning;
	}
	public static ServerSocket getSocket()
	{
		return server;
	}
	public static ArrayList<Player> getPlayersList()
	{
		return playersList;
	}
	public static void onPlayerDisconnect(Player plr)
	{
		playersList.remove(plr);
		System.out.println("Jogador desconectado. IP: "+plr.getIp());
	}
	public static void onPlayerConnect(Player plr)
	{
		playersList.add(plr);
		System.out.println("Jogador conectado. IP: "+plr.getIp());
	}
}
class ServerMainThread implements Runnable
{
	public void run()
	{
		while(Server.getIsRunning())
		{
			try
			{
				Socket newPlayer = Server.getSocket().accept();				
				Player plr = new Player(newPlayer);
				Server.onPlayerConnect(plr);
				plr.getThread().start();
			}
			catch(Exception e)
			{
				Log.writeError("Erro na Thread Principal do servidor.", e);				
			}
			
		}
	}
}
