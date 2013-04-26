package net.lomeli.magiks.addons.ee3;

import java.util.logging.Level;

import net.lomeli.magiks.core.helper.LogHelper;

import cpw.mods.fml.common.Loader;

public class CheckEE3 
{
	public static void isEE3Installed(String modID)
	{
		if (Loader.isModLoaded(modID))
        {
            try
            {
            	LogHelper.log(Level.INFO, "EE3 Installed");
            	EE3Recipes.registerTransmutations();
            }
            catch(Exception ex)
            {
            	LogHelper.log(Level.INFO, "EE3 not installed");
            }
        }
	}
}
