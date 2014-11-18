import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main
{
	private static String ip = "127.0.0.1";
	private static int port = 4000;
	private static JFrame currentFrame;
	public static void main(String[] args)
	{
		SocketManager.mainSocket = new SocketManager(ip, port);
		
		LoginScreen login = new LoginScreen();
		currentFrame = login;	
		
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		login.setSize(230,205);		
		login.setCenterScreen();
		login.addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent ev) 
			{
				if(SocketManager.mainSocket.getAuthenticated())
				{
					MainScreen screen = new MainScreen();
					screen.setSize(640,480);
					screen.setCenterScreen();
					currentFrame = screen; 					
					currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
					currentFrame.setVisible(true);
				}
            }
        });		
		login.setVisible(true);		
	}
};
