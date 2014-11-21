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

public class RegisterScreen extends javax.swing.JFrame
{
	private JTextField usuario;
	private JPasswordField senha, csenha;
	private JButton cadastrar, limpa;
	private JLabel user, pass, cpass;
	
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
	public RegisterScreen()
	{
		super("Cadastro");
		
		setLayout(new FlowLayout());
		
		user = new JLabel("Usuário: ");
		add(user);
		
		usuario = new JTextField(15);
		add(usuario);
		
		pass = new JLabel("Senha:   ");
		add(pass);
		
		senha = new JPasswordField(15);
		add(senha);
		
		cpass = new JLabel("Repita a senha:   ");
		add(cpass);
		
		csenha = new JPasswordField(15);
		add(csenha);
		
		Window thisWindow = this;
		
		cadastrar = new JButton("Cadastrar");
		cadastrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evento)
			{
				if(evento.getSource() == cadastrar)
				{
					String pass = getMD5(new String(senha.getPassword()));
					String cpass = getMD5(new String(csenha.getPassword()));
					if(!SocketManager.mainSocket.connect())
					{
						JOptionPane.showMessageDialog(null, "Impossível de conectar-se ao servidor.", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String resp = SocketManager.mainSocket.sendDataAndWaitResponse("AB"+usuario.getText()+"|"+pass+"|"+cpass);
					
					if(resp.equals("K"))
					{
						JOptionPane.showMessageDialog(null, "Seu cadastro foi feito com sucesso!", "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE);
                                                thisWindow.dispatchEvent(new WindowEvent(thisWindow, WindowEvent.WINDOW_CLOSING));
					}
					else
					{
						if(resp.equals("-1"))
							JOptionPane.showMessageDialog(null, "Impossível de conectar-se ao servidor.", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, resp.substring(1), "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		}
		);
		add(cadastrar);
		
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
	}	
}