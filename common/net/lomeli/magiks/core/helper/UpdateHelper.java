package net.lomeli.magiks.core.helper;

import java.io.IOException;
import java.net.URL;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;

public class UpdateHelper 
{	
	public static UpdateHelper instance = new UpdateHelper();
	
	public static void execute(String textURL) throws IOException
	{
		new Thread(run(textURL));
	}
	
	public static Runnable run(String textURL) throws IOException
	{
		int trys = 0;
		while(trys < 5)
		{
			if(instance.isUpdated(textURL))
				break;
			else
				Magiks.logger.log(Level.INFO, "Failed to update, trying again. Attempt:" + (trys + 1));
			
			trys++;
		}
		return null;
	}
	
	public boolean isUpdated(String textURL) throws IOException
	{
		boolean bool = false;
		try
        {
			InetAddress iAdd = InetAddress.getByName("127.0.0.1");
	        URL url = new URL(textURL);
	        if(!iAdd.isReachable(10))
	        {
	        	@SuppressWarnings("resource")
                Scanner scan = new Scanner(url.openStream());
	        
	        	double currentVersion = Double.parseDouble(Strings.VERSION_AS_DOUBLE);
	        	double latestVersion = scan.nextDouble();
	        
	        	if(latestVersion > currentVersion)
	        		Magiks.logger.log(Level.WARNING, "A new version of MechroMagiks (version " + 
	        			scan.next() + ") is avaliable: " + scan.next());
	        	else
	        		Magiks.logger.log(Level.INFO, "Using latest version.");
	        	
	        	bool = true;
	        }
	        else
	        {
	        	Magiks.logger.log(Level.WARNING, "Could not connect to update server.");
	        	bool = false;
	        }

        } 
		catch (IOException e)
        {
	        e.printStackTrace();
        }
		
		return bool;
	}
}
