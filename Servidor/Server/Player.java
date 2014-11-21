package Server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Player implements Runnable
{
	private Socket socket;
	private Account account;
	private Thread thread;
	private String ip;
	private PrintWriter out;
	
	public Player(Socket socket)
	{
		this.socket = socket;
		this.ip = socket.getInetAddress().toString();
		this.thread = new Thread(this);
		try
		{
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch(Exception e)
		{
		}
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
	public void sendData(String data)
	{
		out.println(data);
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
			OutputStream output = socket.getOutputStream();
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
