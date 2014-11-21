import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Parsing
{
	public static void parseData(SocketManager plr, String data)
	{
		try
		{
			switch(data.charAt(0))
			{
				case 'A'://Login
                                    break;
                                case 'B':
                                    switch(data.charAt(1))
                                    {
                                        case 'A':                                            
                                               if(MainScreen.currentFrame != null)
                                               {                   
                                                   MainScreen.currentFrame.charInfo.setVisible(false);
                                                   if(data.substring(2).equals("-1"))
                                                   {
                                                       DefaultListModel listModel = new DefaultListModel();                                                      
                                                       MainScreen.currentFrame.tName.setText("Nome: Time não criado.");
                                                       MainScreen.currentFrame.tWin.setVisible(false);
                                                       MainScreen.currentFrame.tLoss.setVisible(false);
                                                       MainScreen.currentFrame.tDraw.setVisible(false);
                                                       MainScreen.currentFrame.charList.setVisible(false);
                                                       MainScreen.currentFrame.charList.setModel(listModel);
                                                       return;
                                                   }
                                                   String[] spt = data.substring(2).split("\\|");
                                                   MainScreen.currentFrame.tName.setText("Nome: "+spt[0]);
                                                   String[] stats = spt[1].split("\\,");
                                                   MainScreen.currentFrame.tWin.setVisible(true);
                                                   MainScreen.currentFrame.charList.setVisible(true);
                                                   MainScreen.currentFrame.tLoss.setVisible(true);
                                                   MainScreen.currentFrame.tDraw.setVisible(true);
                                                   MainScreen.currentFrame.tWin.setText("Vitórias: "+stats[0]);
                                                   MainScreen.currentFrame.tLoss.setText("Derrotas: "+stats[1]);
                                                   MainScreen.currentFrame.tDraw.setText("Empates: "+stats[2]);
                                                   String charsData = spt[2];
                                                   DefaultListModel listModel = new DefaultListModel();
                                                   ArrayList<String> charsName = new ArrayList<String>();
                                                   for(String cha : charsData.split("\\;"))
                                                   {
                                                       listModel.addElement(cha.split("\\,")[0]);                                                      
                                                   }                                              
                                                   MainScreen.currentFrame.charList.setModel(listModel);
                                               }
                                            break;
                                        case 'B':
                                            if(data.startsWith("E"))        
                                                JOptionPane.showMessageDialog(null, data.substring(2), "Erro ao criar time", JOptionPane.ERROR_MESSAGE);        
                                            else
                                                JOptionPane.showMessageDialog(null, "Time criado com sucesso!", "Time criado", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                    }
                                    break;
			}
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao realizar parsing do pacote recebido.", e);
		}
	}
}
