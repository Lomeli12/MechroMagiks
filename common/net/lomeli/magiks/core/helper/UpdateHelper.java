package net.lomeli.magiks.core.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.*;

import net.lomeli.magiks.lib.Strings;

import org.xml.sax.*;
import org.w3c.dom.*;

public class UpdateHelper 
{
	private static Properties xmlProperties = new Properties();
	
	public static void isUpdated(String xml)
	{
		double installedVersion = Double.parseDouble(Strings.VERSION);
		String updatedVersion = null;
		
		LogHelper.log(Level.INFO, "Checking for newer versions.");
		try {
			URL xmlURL = new URL(xml);
			InputStream xmlStream = xmlURL.openStream();
			xmlProperties.loadFromXML(xmlStream);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			dbf.setValidating(false);
			dbf.setFeature("http://xml.org/sax/features/namespaces", false);
			dbf.setFeature("http://xml.org/sax/features/validation", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = null;
			try
			{
				doc = db.parse(xmlStream);
			}catch (ConnectException ce)
			{
				LogHelper.log(Level.INFO, "");
			}
			
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName("version");
			updatedVersion = nodeList.toString();
			
			if(updatedVersion != null)
			{
				if(installedVersion >= Double.parseDouble(updatedVersion))
				{
					LogHelper.log(Level.INFO, Strings.MOD_NAME + " is up-to-date.");
				}
				else
				{
					LogHelper.log(Level.INFO, " A new version of " + Strings.MOD_ID + ", " +
						updatedVersion + " has been found. Download it at: bit.ly/ZIj6I9");
				}
			}
			xmlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
	}
}
