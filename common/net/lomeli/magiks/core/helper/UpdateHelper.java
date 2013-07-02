package net.lomeli.magiks.core.helper;

import java.net.SocketException;
import java.net.URL;
import java.util.logging.Level;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Ints;
import net.lomeli.magiks.lib.Strings;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UpdateHelper 
{
	private static boolean isUpdated;
	
	public UpdateHelper()
	{
		isUpdated = false;
	}
	
	public boolean isUpdate()
	{
		return isUpdated;
	}
	
	public static void execute(String textURL) throws SocketException
	{
		new Thread(run(textURL));
	}
	
	private static Runnable run(String textURL) throws SocketException
	{
		int trys = 0;
		while(trys < 5)
		{
			if(checkForUpdates(textURL))
			{
				isUpdated = true;
				Magiks.logger.log(Level.WARNING, updateText(textURL));
			}
			else
				Magiks.logger.log(Level.INFO, "Using the Latest Version");
			
			trys++;
		}
		if(trys == 5 && isUpdated == false)
			Magiks.logger.log(Level.WARNING, "Failed to check for updates!");
			
		return null;
	}
	
	public static String updateText(String updateURL)
	{
		String download = String.valueOf(praseXML(updateURL, "modURL"));
		return ("A new version of " + Strings.MOD_NAME + " is available at " + download);
	}
	
	private static boolean checkForUpdates(String updateURL)
	{
		boolean results = false;
		Object[] xml = { praseXML(updateURL, "majorBuildNumber"), praseXML(updateURL, "minorBuildNumber"),
		           praseXML(updateURL, "revisionBuildNumber") };
		int[] latestVersion = { new Integer(xml[0].toString()), 
	            new Integer(xml[1].toString()), new Integer(xml[2].toString()) };
		int[] currentVersion = { Ints.VERSION_MAJOR, Ints.VERSION_MINOR, Ints.VERSION_REVISION };
		
		for(int i = 0; i < 3; i++)
		{
			if(latestVersion[i] > currentVersion[i])
				results = true;
		}
		
		return results;
	}
	
	private static Object praseXML(String URLLoc, String nodeName)
	{
        Object var1 = new Object();
        try
        {
            URL xmlURL = new URL(URLLoc);
            InputStream xml = xmlURL.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            
            doc.getDocumentElement().normalize();
            
            NodeList node = doc.getElementsByTagName(nodeName);
            
            var1 = node.item(0).getTextContent();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return var1;
	}
}
