import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main
{
	private static String ip = "127.0.0.1";
	private static int port = 4000;
	
	public static void main(String[] args)
	{
		SocketManager.mainSocket = new SocketManager(ip, port);
		
		LoginScreen login = new LoginScreen();			
		
		login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		login.setSize(230,205);
		login.setResizable(false);
		login.setCenterScreen();
		login.addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent ev) 
			{
				if(SocketManager.mainSocket.getAuthenticated())
				{
					MainScreen.currentFrame = new MainScreen();
					MainScreen.currentFrame.setSize(640,480);
					MainScreen.currentFrame.setCenterScreen();
					MainScreen.currentFrame.setResizable(false);					 					
					MainScreen.currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					
					MainScreen.currentFrame.setVisible(true);
				}
            }
        });		
		login.setVisible(true);		
	}
};
