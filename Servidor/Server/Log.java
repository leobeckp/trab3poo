package Server;
import java.net.*;
import java.util.*;

public class Log
{
	public static void writeError(String message, Exception e)
	{
		System.out.println(message);
		System.out.println("Detalhes do erro: "+e.getMessage());
		System.out.print("STACK: ");
		e.printStackTrace();
	}
}