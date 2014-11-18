package Server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Player implements Runnable
{
	private Socket socket;
	private String username;
	private String password;
	private int teamId;
	private Thread thread;
	private String ip;
	
	public Player(Socket socket)
	{
		this.socket = socket;
		this.ip = socket.getInetAddress().toString();
		this.thread = new Thread(this);
	}
	public String getUsername()
	{
		return username;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public String getPassword()
	{
		return password;
	}
	public int getTeamId()
	{
		return teamId;
	}
	public Thread getThread()
	{
		return thread;
	}
	public void setUsername(String value)
	{
		username = value;
	}
	public void setPassword(String value)
	{
		password = value;
	}
	public void setTeamId(int value)
	{
		teamId = value;
	}
	public String getIp()
	{
		return ip;
	}
	public void disconnect()
	{
		try
		{
			socket.close();
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
			OutputStream output = socket.getOutputStream();
			String data;
			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = input.read(buffer,0,1024)) > 0) 
			{
				data = new String(buffer, 0, read, "UTF-8");
				Parsing.parseData(this, data);
			}			
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
