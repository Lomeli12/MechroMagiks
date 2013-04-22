package net.lomeli.magiks.addons.ee3;

import java.util.logging.Logger;
import java.util.logging.Level;

import cpw.mods.fml.common.Loader;

public class CheckEE3 
{
	public static void isEE3Installed(String modID, Logger log)
	{
		if (Loader.isModLoaded(modID))
        {
            try
            {
            	log.log(Level.INFO, "EE3 Installed");
            	EE3Recipes.registerTransmutations();
            }
            catch(Exception ex)
            {
            	log.log(Level.INFO, "EE3 not installed");
            }
        }
	}
}
