package net.lomeli.magiks.items.magik;

import net.lomeli.magiks.items.ItemGeneric;
import net.minecraft.item.ItemStack;


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
