import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class SocketManager
{
	private Socket socket;	
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
	public void sendData(String data)
	{
            try
            {
                data += "\r\n";
                Log.logPacket("SEND >> "+data);
                byte[] bData = data.getBytes("UTF-8");
                socket.getOutputStream().write(bData);
                socket.getOutputStream().flush();		
            }
            catch(Exception e)
            {
                
            }                
	}
	public String sendDataAndWaitResponse(String data)
	{
		try
		{
                    data += "\r\n";
                    Log.logPacket("SEND >> "+data);
                    byte[] bData = data.getBytes("UTF-8");
                    socket.getOutputStream().write(bData);
                    socket.getOutputStream().flush();		
		    byte[] buffer = new byte[2048]; 
                    int read = socket.getInputStream().read(buffer, 0, 2048);
                    if(read > 0)
                    {
                        String recv = new String(buffer, 0, read, "UTF-8");
                        recv = recv.split("\\\r\n")[0];
                        Log.logPacket("RECV << "+recv);
                        return recv;
                    }
                    else                            
                        return "EErro ao comunicar-se com o servidor";
		}
		catch(Exception e)
		{
			//Log.writeError("Erro ao enviar dados para receber resposta.", e);
			return "EErro ao comunicar-se com o servidor";
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
			return true;
		}
		catch(ConnectException e)
		{
			return false;
		}
		catch (Exception e)
		{
			Log.writeError("Erro ao conectar socket.", e);
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
        public Socket getSocket()
        {
            return socket;
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
			InputStream input  = client.getSocket().getInputStream();
			OutputStream output = client.getSocket().getOutputStream();			
			byte[] buffer = new byte[1024];
			int read = 0;
			while((read = input.read(buffer,0,1024)) > 0) 
			{
				data = new String(buffer, 0, read, "UTF-8");
				for(String s : data.split("\\\r\n"))
				{
					Log.logPacket("RECV << " + s);
					Parsing.parseData(this.client, s);
				}				
			}		
		}
		catch(Exception e)
		{
                    Log.writeError("Erro ao receber pacote.", e);
		}
                JOptionPane.showMessageDialog(null, "A conexão com o servidor foi encerrada.", "Conexão perdida", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
		isRunning = false;
	}
};
