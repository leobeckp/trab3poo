import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.security.*;
import java.math.*;

public class MainScreen extends JFrame
{
	public MainScreen()
	{
		super("MainScreen");
	}
	public void setCenterScreen()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}