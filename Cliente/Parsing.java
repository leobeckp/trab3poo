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
                    if(data.substring(2).startsWith("E"))
						JOptionPane.showMessageDialog(null, data.substring(3), "Erro ao criar time", JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Time criado com sucesso!", "Time criado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    case 'C':
                    MainScreen.currentFrame.charInfo.setVisible(true);
                    String hp = data.substring(2).split("\\|")[0];
                    String cla = data.substring(2).split("\\|")[1];
                    MainScreen.currentFrame.jLabel2.setText("Informações de "+MainScreen.currentFrame.charList.getSelectedValue().toString());
                    MainScreen.currentFrame.charHp.setText("HP: "+hp);
                    MainScreen.currentFrame.charClass.setText("Classe: "+cla);
                    break;
                }
                break;
                case 'C':
                switch(data.charAt(1))
                {
                    case 'A':
                    char state = data.charAt(2);
                    switch(state)
                    {
                        case '0':
                        MainScreen.currentFrame.jButton3.setText("Procurar batalha");
                        break;
                        case '1':
                        MainScreen.currentFrame.jButton3.setText("Sair da fila");
                        break;
                        case 'E':
                        JOptionPane.showMessageDialog(null, data.substring(3), "Erro ao entrar na fila de batalhas", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    break;
                    case 'B':
                    String[] infos = data.substring(2).split("\\|");
                    MainScreen.currentFrame.content.removeAll();
                    MainScreen.currentFrame.content.add(MainScreen.currentFrame.fightResults);
                    MainScreen.currentFrame.content.repaint();
                    MainScreen.currentFrame.content.revalidate();
                    MainScreen.currentFrame.jTextArea1.setText("");
                    for(int i = 0; i < infos.length;i++)
                    {
                        MainScreen.currentFrame.jTextArea1.setText(MainScreen.currentFrame.jTextArea1.getText()+infos[i]+"\r\n");
                    }
                    break;
                }
                break;
                case 'D':
                switch(data.charAt(1))
                {
                    case 'A':
                    String[] infos = data.substring(2).split("\\|");
                    MainScreen.currentFrame.invData.setText("");
                    MainScreen.currentFrame.invName.setText("Inventário de "+MainScreen.currentFrame.charList.getSelectedValue().toString());
                    for(int i = 0; i < infos.length;i++)
                    {
                        MainScreen.currentFrame.invData.setText(MainScreen.currentFrame.invData.getText()+infos[i]+"\r\n");
                    }
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