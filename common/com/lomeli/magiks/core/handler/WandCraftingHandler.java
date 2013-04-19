package com.lomeli.magiks.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.api.libs.MagiksArrays;

import cpw.mods.fml.common.ICraftingHandler;

public class WandCraftingHandler implements ICraftingHandler
{

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory inv)
    {
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            if (inv.getStackInSlot(i) != null)
            {
                ItemStack j = inv.getStackInSlot(i);
                for (ItemStack itemD : MagiksArrays.damageOnCraft)
                {
                    if (j.getItem() != null && j.getItem() == itemD.getItem())
                    {
                        ItemStack k = new ItemStack(itemD.getItem(), 2,
                                j.getItemDamage() + 1);
                        inv.setInventorySlotContents(i, k);
                    }
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {
    }

}
