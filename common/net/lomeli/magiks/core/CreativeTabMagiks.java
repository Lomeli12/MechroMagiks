package net.lomeli.magiks.core;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabMagiks extends CreativeTabs
{
    private static String tabName;

    public CreativeTabMagiks(int par1, String par2Str)
    {
        super(par1, par2Str);
        tabName = par2Str;
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return new ItemStack(ModItemsMagiks.flyingRing);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return tabName;
    }

}
