package Server;

import java.net.*;
import java.util.*;
import java.io.*;

public class Account
{
	private String username;
	private String password;
	private int teamId;
	
	
	public Account(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public String getUsername()
	{
		return username;
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
}
