package net.lomeli.magiks.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemHelper
{
    public static int getSlotContainingItem(int itemID, ItemStack[] inventory)
    {
        for (int j = 0; j < inventory.length; j++)
        {
            if (inventory[j] != null && inventory[j].itemID == itemID)
                return j;
        }
        return -1;
    }

    public static ItemStack getItem(EntityPlayer player, int slot)
    {
        return player.inventory.getStackInSlot(slot);
    }
}
