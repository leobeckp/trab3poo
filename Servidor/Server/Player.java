package Server;

import java.net.*;
import java.util.*;
import java.io.*;
import Game.*;

public class Player implements Runnable
{
	private Socket socket;
	private Account account;
	private Thread thread;
	private String ip;
	private boolean ready;
	
	public Player(Socket socket)
	{
		this.socket = socket;
		this.ip = socket.getInetAddress().toString();
		this.thread = new Thread(this);
		this.ready = false;
	}
	public Account getAccount()
	{
		return account;
	}
	public Socket getSocket()
	{
		return socket;
	}	
	public Thread getThread()
	{
		return thread;
	}	
	public String getIp()
	{
		return ip;
	}
	public void setAccount(Account value)
	{
		account = value;
	}
	public void setReady(boolean value)
	{
		this.ready = value;
	}
	public boolean isReady()
	{
		if(this.ready)
			return true;
			else
				return false;
	}
	public void fightPlayer(Player opo)
	{
		int win = Integer.parseInt(getAccount().getTeam().getResultsPattern().split("\\,")[0]);
		int draw = Integer.parseInt(getAccount().getTeam().getResultsPattern().split("\\,")[2]);
		
		for(GameCharacter i : getAccount().getTeam().getCharacters())
		{
			i.addHP(100);
		}
		for(GameCharacter i : opo.getAccount().getTeam().getCharacters())
		{
			i.addHP(100);
		}
		ArrayList<String> fightData = getAccount().getTeam().fightTeam(opo.getAccount().getTeam());
		getAccount().getTeam().resolveBattle(opo.getAccount().getTeam());
		opo.getAccount().getTeam().resolveBattle(getAccount().getTeam());
		
		fightData.add("Pontos do time \""+getAccount().getTeam().getName()+"\": "+getAccount().getTeam().getPoints());
		fightData.add("Pontos do time \""+opo.getAccount().getTeam().getName()+"\": "+opo.getAccount().getTeam().getPoints());
		
		int cwin = Integer.parseInt(getAccount().getTeam().getResultsPattern().split("\\,")[0]);
		int cdraw = Integer.parseInt(getAccount().getTeam().getResultsPattern().split("\\,")[2]);
		
		if(cwin > win)
		{
			fightData.add("O time \""+getAccount().getTeam().getName()+"\" foi o vencedor do combate!");
		}
		else
		{
			if(cdraw > draw)
			{
				fightData.add("O combate terminou empatado!");
			}
			else
			{
				fightData.add("O time \""+opo.getAccount().getTeam().getName()+"\" foi o vencedor do combate!");
			}
		}
		sendData("CB"+String.join("|", fightData));
		opo.sendData("CB"+String.join("|", fightData));
		Parsing.parseData(this,"BA" );
		Parsing.parseData(opo,"BA");
		setReady(false);
		opo.setReady(false);
		opo.sendData("CA0");
		sendData("CA0");
		Database.teamsDb.saveDatabase();
	}
	public void sendData(String data)
	{
		try
		{
			Log.logPacket("SEND >> "+data);
			data += "\r\n";			
			byte[] bData = data.getBytes("UTF-8");
			socket.getOutputStream().write(bData);
			socket.getOutputStream().flush();
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao enviar pacote.", e);
		}
	}
	public void disconnect()
	{
		try
		{
			socket.close();
			thread.interrupt();
		}
		catch(Exception e)
		{
		}		
	}
	public void run()
	{
		try
		{
			InputStream input  = socket.getInputStream();			
			String data;
			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = input.read(buffer,0,1024)) > 0) 
			{
				data = new String(buffer, 0, read, "UTF-8");
				for(String s : data.split("\\\r\n"))
				{
					Log.logPacket("RECV << " + s);
					Parsing.parseData(this, s);
				}				
			}			
		}
		catch(SocketException e)
		{
		
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao receber dados.", e);
		}
		Server.onPlayerDisconnect(this);
		try
		{
			socket.close();
		}
		catch(IOException f)
		{
		}
	}
}
