package net.lomeli.magiks.core.helper;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModInts;
import net.lomeli.magiks.lib.ModStrings;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class UpdateHelper 
{
	private static boolean isUpdated;
	private static String downloadURL;
	
	public UpdateHelper()
	{
		isUpdated = true;
	}
	
	public boolean isUpdate()
	{
		return isUpdated;
	}
	
	public void check(String updateUrl)
	{
		checkForUpdates(updateUrl);
	}
	
	public static String updateText()
	{
		return ("A new version of " + ModStrings.MOD_NAME + " is available at " + downloadURL);
	}
	
	private void checkForUpdates(String updateURL)
	{
		Object[] xml = { praseXML(updateURL, "majorBuildNumber"), praseXML(updateURL, "minorBuildNumber"),
		           praseXML(updateURL, "revisionBuildNumber") };
		int[] latestVersion = { new Integer(xml[0].toString()), 
	            new Integer(xml[1].toString()), new Integer(xml[2].toString()) };
		int[] currentVersion = { ModInts.VERSION_MAJOR, ModInts.VERSION_MINOR, ModInts.VERSION_REVISION };
		
		for(int i = 0; i < 3; i++)
		{
			if(latestVersion[i] > currentVersion[i])
			{
				isUpdated = false;
				downloadURL = String.valueOf(praseXML(updateURL, "modURL"));
				Magiks.logger.log(Level.WARNING, updateText());
			}
		}
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
