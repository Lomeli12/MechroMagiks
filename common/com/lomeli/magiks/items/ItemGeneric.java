package com.lomeli.magiks.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGeneric extends Item
{
    private String itemTexture;
    private boolean isSpecial;

    public ItemGeneric(int par1, String Texture, boolean special)
    {
        super(par1);
        itemTexture = Texture;
        isSpecial = special;
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        iconIndex = iconRegister
                .registerIcon(Strings.modID + ":" + itemTexture);
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return isSpecial;
    }
}