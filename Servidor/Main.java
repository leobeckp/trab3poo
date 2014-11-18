import java.net.*;
import java.util.*;

public class Main
{
	private static int port = 4000;
	private static String bindIp = "0.0.0.0";
	private static int backlog = 50;
	private static ServerSocket server;
	private static ArrayList<Player> playerList;
	private static Thread mainThread;
	private static Boolean isRunning;
	
	public static void main(String[] args)
	{
		System.out.print("Iniciando servidor...");
		playerList = new ArrayList<Player>();
		String line = "";
		try
		{
			server = new ServerSocket(port, backlog, InetAddress.getByName(bindIp));
			MainThread main_c = new MainThread(server);
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
		while(!line.contains("exit"))
		{
			try
			{
				switch(line)
				{
					case "conns":
						System.out.println("Jogadores conectados: "+playerList.size());
					break;
				}
				line = System.console().readLine();				
			}
			catch(Exception e)
			{
				Log.writeError("Erro ao realizar comando.",e);			
			}			
		}
		shutdown();
	}
	public static Boolean getIsRunning()
	{
		return isRunning;
	}
	public static void shutdown()
	{
		try
		{
			isRunning = false;
			for(Player plr : playerList)
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
			System.exit(0);
		}
		catch(Exception e)
		{
			
		}
	}
	public static void onPlayerDisconnect(Player plr)
	{
		playerList.remove(plr);
		System.out.println("Jogador desconectado. IP: "+plr.getIp());
	}
	public static void onPlayerConnect(Player plr)
	{
		playerList.add(plr);
		System.out.println("Jogador conectado. IP: "+plr.getIp());
	}
}
class MainThread implements Runnable
{
	private ServerSocket server;	
	public MainThread(ServerSocket server)
	{
		this.server = server;		
	}
	public void run()
	{
		while(Main.getIsRunning())
		{
			try
			{
				Socket newPlayer = server.accept();				
				Player plr = new Player(newPlayer);
				Main.onPlayerConnect(plr);
				plr.getThread().start();
			}
			catch(Exception e)
			{
				Log.writeError("Erro na Thread Principal do servidor.", e);				
			}
			
		}
	}
}