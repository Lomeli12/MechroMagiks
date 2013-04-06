package com.lomeli.magiks.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.items.ModItemsMagiks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabSIW extends CreativeTabs
{
    private static String tabName;

    public CreativeTabSIW(int par1, String par2Str)
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
