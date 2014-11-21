import java.net.*;
import java.util.*;

public class Log
{
	private static Boolean logPacket = true;
	public static void writeError(String message, Exception e)
	{
		System.out.println(message);
		System.out.println("Detalhes do erro: "+e.getMessage());
		System.out.print("STACK: ");
		e.printStackTrace();
	}
	public static void logPacket(String message)
	{
		if(logPacket)
			System.out.println(message);
	}
}