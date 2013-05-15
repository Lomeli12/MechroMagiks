package net.lomeli.magiks.core.handler;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerInteractHandler
{
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        if (player != null)
        {
            if (player.capabilities.isCreativeMode == false)
            {
                if (player.inventory.hasItem(ModItemsMagiks.flyingRing.itemID))
                {
                } else
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                    player.capabilities.setPlayerWalkSpeed(0.1F);
                    player.stepHeight = 0.5F;
                }
            } else
            {
            	player.capabilities.setPlayerWalkSpeed(0.1F);
            	player.stepHeight = 0.5F;
                if (player.capabilities.allowFlying == false)
                {
                    player.capabilities.allowFlying = true;
                } else
                {
                }
            }
        }
    }

}
