package Server;

import Game.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Account implements java.io.Serializable
{
	public String username;
	private String password;
	private int teamId;	
	
	
	public Account(String username, String password, int teamId)
	{
		this.username = username;
		this.password = password;
		this.teamId = teamId;
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
	public Team getTeam()
	{
		return Database.teamsDb.getEntryById(teamId);		
	}
}
