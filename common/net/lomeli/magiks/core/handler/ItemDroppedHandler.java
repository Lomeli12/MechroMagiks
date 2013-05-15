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
    		System.out.println(1);
    		EntityPlayer player = event.player;
    		if(player != null)
    		{
    			System.out.println(2);
    			player.capabilities.setPlayerWalkSpeed(0.1F);
            	player.stepHeight = 0.5F;
    			if (player.capabilities.isCreativeMode == false)
    	        {
    				System.out.println(3);
    				player.capabilities.allowFlying = false;
                    if (player.capabilities.isFlying == true)
                    {
                    	System.out.println(4);
                    	player.capabilities.allowFlying = false;
                    	player.capabilities.isFlying = false;
                    }
    	        }
    		}
    	}
    }
}
