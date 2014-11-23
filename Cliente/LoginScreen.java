import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.security.*;
import java.math.*;

public class LoginScreen extends javax.swing.JFrame
{
	private JTextField usuario;
	private JPasswordField senha;
	private JButton login, limpa, cadastrar;
	private JLabel user, pass;
	
	public String getMD5(String string)
	{
		MessageDigest m;
		try
		{
			m = MessageDigest.getInstance("MD5");
			m.update(string.getBytes(),0,string.length());
			BigInteger i = new BigInteger(1, m.digest());
			
			string = String.format("%1$032X", i);

			return string;
		}
		catch (NoSuchAlgorithmException e)
		{
			return "";
		}
	}
	
	public void setCenterScreen()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public LoginScreen()
	{
		super("Autenticação");
		
		setLayout(new FlowLayout());
		
		user = new JLabel("Usuário: ");
		add(user);
		
		usuario = new JTextField(15);
		add(usuario);
		usuario.setText("leokod");
                
		pass = new JLabel("Senha:   ");
		add(pass);
		
		senha = new JPasswordField(15);
		add(senha);
		senha.setText("123456");
		final Window loginWindow = this;
		
		login = new JButton("Entrar");
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evento)
			{
				if(evento.getSource() == login)
				{
					String pass = getMD5(new String(senha.getPassword()));
					if(!SocketManager.mainSocket.connect())
					{
						JOptionPane.showMessageDialog(null, "Impossível de conectar-se ao servidor.", "Erro na autenticação", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String resp = SocketManager.mainSocket.sendDataAndWaitResponse("AA"+usuario.getText()+"|"+pass);
					
					if(resp.equals("OK"))
					{
						SocketManager.mainSocket.setAuhenticated(true);
                                                SocketManager.mainSocket.startGame();                                                
						loginWindow.dispatchEvent(new WindowEvent(loginWindow, WindowEvent.WINDOW_CLOSING));
					}
					else
					{
						if(resp.equals("-1"))
							JOptionPane.showMessageDialog(null, "Impossível de conectar-se ao servidor.", "Erro na autenticação", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos", "Erro na autenticação", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		}
		);
		add(login);
		
		limpa = new JButton("Limpar");
		limpa.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evento)
			{
				if(evento.getSource() == limpa)
				{
					usuario.setText("");
					senha.setText("");
				}
			}
		 }
		);
		add(limpa);
		
		cadastrar = new JButton("Cadastrar");
		cadastrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evento)
			{
				if(evento.getSource() == cadastrar)
				{
					RegisterScreen reg = new RegisterScreen();		
		
					reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					reg.setSize(230,215);
					reg.setResizable(false);					
					reg.setCenterScreen();		
					reg.setVisible(true);		
				}
			}
		}
		);
		add(cadastrar);
		
	}	
}