package Server;
import java.util.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.*;
import java.util.Map.Entry;
import java.nio.charset.Charset;

public class Database<T>
{
	private Map<Integer, T> data;
	public int lastId = 0;	
	private Class<T> persistentClass;
	
	//métodos static
	public static Database<Account> accountsDb;
	
	public static void initDatabases()
	{
		accountsDb = new Database<Account>(Account.class);
		accountsDb.loadDatabase();
	}
	
    public Database(Class<T> persistentClass) 
	{
       	this.persistentClass = persistentClass;	
		data = new HashMap<Integer, T>();
    }
    
	public void loadDatabase()
	{
		try
		{
			FileInputStream fis = new FileInputStream(persistentClass.getName()+".db");
            ObjectInputStream ois = new ObjectInputStream(fis);
			data = (HashMap<Integer, T>)ois.readObject();
			fis.close();
            ois.close();
		}
		catch(EOFException e)
		{
		}
		catch(FileNotFoundException e)
		{
		}
		catch (Exception x)
		{
			Log.writeError("Erro ao carregar dados da classe "+persistentClass.getName(), x);
		}
	}
	public void saveDatabase()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(persistentClass.getName()+".db");
            ObjectOutputStream oos = new ObjectOutputStream(fos);           
			oos.writeObject(data);
			fos.close();
			oos.close();
		}
		catch(Exception e)
		{
			Log.writeError("Erro ao salvar dados da classe " + persistentClass.getName(), e);
		}
	}
	public T getEntryByField(String field, Object search)
	{
		Field f = null;
		try
		{
			f = persistentClass.getField(field);
		}
		catch(Exception e)
		{
			return null;
		}
		for(Entry<Integer,T> entry: data.entrySet())
		{
			try
			{
				if(f.get(entry.getValue()).equals(search))
				{
					return entry.getValue();
				}
			}
			catch(Exception e)
			{
			}
		}
		return null;
	}
	public T getEntryById(int i)
	{
		if(!data.containsKey(i))
			return null;
			
		return data.get(i);
	}
	public void insertEntry(T entry)
	{
		data.put(lastId, entry);
		lastId++;
	}
	public void insertEntry(int id, T entry)
	{
		data.put(id, entry);
		if(lastId <= id)		
			lastId = id + 1;		
	}
}