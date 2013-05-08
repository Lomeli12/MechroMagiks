package net.lomeli.magiks.core.handler;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class MiscHandler 
{
	public static boolean doAdditionalInfo() 
	{
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) 
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                return true;
        }
        return false;
    }
	
	public static String additionalInfoInstructions() 
	{
        String message = "Press SHIFT for more info.";
        return "\u00a76\u00a7o" + message;
    }
}