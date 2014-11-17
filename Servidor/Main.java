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
			MainThread main_c = new MainThread(server, playerList);
			isRunning = true;
			mainThread = new Thread(main_c);
			mainThread.start();
			System.out.println("OK!");
		}
		catch(Exception e)
		{
			System.out.println("ERRO!");
			System.out.println("Mensagem: "+e.getMessage());
			System.out.print("STACK: ");
			e.printStackTrace();
		}
		while(line != "exit\n")
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
				System.out.println("ERRO!");
				System.out.println("Mensagem: "+e.getMessage());
				System.out.print("STACK: ");
				e.printStackTrace();
			}
			
		}
		isRunning = false;
	}
	public static Boolean getIsRunning()
	{
		return isRunning;
	}
}
class MainThread implements Runnable
{
	private ServerSocket server;
	private ArrayList<Player> playerList;
	public MainThread(ServerSocket server, ArrayList<Player> playerList)
	{
		this.server = server;
		this.playerList = playerList;
	}
	public void run()
	{
		while(Main.getIsRunning())
		{
			try
			{
				Socket newPlayer = server.accept();
				System.out.println("Jogador conectado. IP: "+newPlayer.getInetAddress().toString());
				Player plr = new Player(newPlayer);
			}
			catch(Exception e)
			{
				System.out.println("Erro na Thread Principal do servidor. Mensagem: "+e.getMessage());
				System.out.print("STACK: ");
				e.printStackTrace();
			}
			
		}
	}
}