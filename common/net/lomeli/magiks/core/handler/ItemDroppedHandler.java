package net.lomeli.magiks.core.handler;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class ItemDroppedHandler
{
    @ForgeSubscribe
    public void onItemTossEvent(ItemTossEvent event)
    {
    	if (event.entityItem.getEntityItem() != null && event.entityItem.getEntityItem().itemID == ModItemsMagiks.flyingRing.itemID)
    	{
    		EntityPlayer player = event.player;
    		if(player != null)
    		{
            	player.stepHeight = 0.5F;
    			if (player.capabilities.isCreativeMode == false)
    	        {
    				player.capabilities.allowFlying = false;
                    if (player.capabilities.isFlying == true)
                    {
                    	player.capabilities.allowFlying = false;
                    	player.capabilities.isFlying = false;
                    }
    	        }
    		}
    	}
    }
}
