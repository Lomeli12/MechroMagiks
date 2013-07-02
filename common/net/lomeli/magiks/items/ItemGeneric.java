package net.lomeli.magiks.items;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Strings.MOD_ID + ":" + itemTexture);
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return isSpecial;
    }
    
    
}
