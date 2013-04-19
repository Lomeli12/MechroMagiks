package net.lomeli.magiks.core.handler;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;


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
