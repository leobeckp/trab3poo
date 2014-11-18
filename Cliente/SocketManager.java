import java.net.*;
import java.io.*;

public class SocketManager
{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Thread recvThread;
	private Boolean isRunning;
	public static SocketManager mainSocket;
	private String host;
	private int port;
	private Boolean authenticated;
	
	public SocketManager(String host, int port)
	{
		this.host = host;
		this.port = port;
		this.authenticated = false;
	}
	public BufferedReader getIn()
	{
		return in;
	}
	public void sendData(String data)
	{
		out.println(data);
	}
	public String sendDataAndWaitResponse(String data)
	{
		try
		{
			out.println(data);		
			data = in.readLine();		
			return data;
		}
		catch(Exception e)
		{
			//Log.writeError("Erro ao enviar dados para receber resposta.", e);
			return "-1";
		}
	}
	public void startGame()
	{
		StreamReceiver recv_c = new StreamReceiver(this);
		recv_c.isRunning = true;
		recvThread = new Thread(recv_c);
		recvThread.start();
	}
	public Boolean connect()
	{
		try
		{
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return true;
		}
		catch (Exception e)
		{
			//Log.writeError("Erro ao conectar socket.", e);
			return false;
		}	
	}
	public void setAuhenticated(Boolean value)
	{
		authenticated = value;
	}
	public Boolean getAuthenticated()
	{
		return authenticated;
	}		
};
class StreamReceiver implements Runnable
{
	private SocketManager client;
	public Boolean isRunning;
	public StreamReceiver(SocketManager client)
	{
		this.client = client;
	}
	public void run()
	{
		String data = "";
		try
		{
			while ((data = client.getIn().readLine()) != null)
			{
				Parsing.parseData(client, data);
			}
		}
		catch(Exception e)
		{
			
		}
		isRunning = false;
	}
};
