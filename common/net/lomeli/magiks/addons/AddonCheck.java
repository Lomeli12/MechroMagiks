package net.lomeli.magiks.addons;

import java.util.logging.Logger;

import net.lomeli.magiks.addons.ee3.CheckEE3;
import net.lomeli.magiks.lib.Strings;

public class AddonCheck 
{
	private static Logger modLogger = Logger.getLogger(Strings.modName);
	
	public static final String EE3_ID = "EE3";
	public static final String IC2_ID = "IC2";
	public static final String BUILDCRAFT_ID = "BuildCraft|Transport";
	
	public static void checkAddons()
	{
		CheckEE3.isEE3Installed(EE3_ID, modLogger);
	}

}
