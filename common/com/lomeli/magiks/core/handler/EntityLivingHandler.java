package com.lomeli.magiks.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.lomeli.magiks.items.ModItemsMagiks;

public class EntityLivingHandler
{
    @ForgeSubscribe
    public void onEntityLivingUpdate(LivingUpdateEvent event)
    {

    }

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event)
    {
        if (event != null)
        {
            if (event.source.getDamageType().equals("player"))
            {
                if (event.source.getEntity() instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) event.source
                            .getEntity();
                    if (player.inventory.getCurrentItem().itemID == ModItemsMagiks.levelingSword.itemID)
                    {
                        player.setFire(1000000);
                    }
                }
            }
        }
    }

}
