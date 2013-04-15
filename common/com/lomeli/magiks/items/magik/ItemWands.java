package com.lomeli.magiks.items.magik;

import net.minecraft.item.ItemStack;

import com.lomeli.magiks.items.ItemGeneric;

public class ItemWands extends ItemGeneric
{
    public ItemWands(int par1, String Texture, boolean special, int damage)
    {
        super(par1, Texture, special);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack) 
    {
        return true;
    }
    
    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack)
    {
        itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        return itemStack;
    }
}
