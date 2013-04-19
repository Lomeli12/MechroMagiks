package net.lomeli.magiks.items.tools;

import net.minecraft.item.ItemStack;

public interface IMagik
{
    public abstract int getMagik(ItemStack itemStack);

    public abstract int setMagik(ItemStack itemStack, int magik);

    public abstract int addMagik(ItemStack itemStack);

    public abstract int loseMagik(ItemStack itemStack);
}
