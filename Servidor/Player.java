import java.net.*;
import java.util.*;

public class Player implements Runnable
{
	private Socket socket;
	private String username;
	private String password;
	private int teamId;
	
	public Player(Socket socket)
	{
		this.socket = socket;		
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
	public void run()
	{
		
	}
}
