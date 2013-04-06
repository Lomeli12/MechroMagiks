package com.lomeli.magiks.core.handler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

import com.lomeli.magiks.items.ModItemsMagiks;

public class ItemDroppedHandler
{
    @ForgeSubscribe
    public void onItemTossEvent(ItemTossEvent event)
    {
        if (event.player.capabilities.isCreativeMode == false)
        {
            if (event.entityItem.getEntityItem().itemID == ModItemsMagiks.flyingRing.itemID)
            {
                event.player.capabilities.allowFlying = false;
                if (event.player.capabilities.isFlying == true)
                {

                }
            }
        } else
        {
        }
    }
}
