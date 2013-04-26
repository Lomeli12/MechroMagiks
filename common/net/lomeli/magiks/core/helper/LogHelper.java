package net.lomeli.magiks.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import net.lomeli.magiks.lib.Strings;

public class LogHelper 
{
	private static Logger modLogger = Logger.getLogger(Strings.MOD_NAME);
	
	public static void init()
	{
		modLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level logLevel, String message)
    {
		modLogger.log(logLevel, message);
    }

}
