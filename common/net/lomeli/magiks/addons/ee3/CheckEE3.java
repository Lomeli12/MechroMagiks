package net.lomeli.magiks.addons.ee3;

import java.util.logging.Level;

import net.lomeli.magiks.Magiks;

import cpw.mods.fml.common.Loader;

public class CheckEE3 
{
	public static void isEE3Installed(String modID)
	{
		if (Loader.isModLoaded(modID))
        {
            try
            {
            	Magiks.logger.log(Level.INFO, "EE3 Installed");
            	EE3Recipes.registerTransmutations();
            }
            catch(Exception ex)
            {
            	Magiks.logger.log(Level.INFO, "EE3 not installed");
            }
        }
	}
}
