package com.lomeli.magiks.items.tools;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEmpoweredPick extends ItemPickaxe
{
    private String itemTexture;

    public ItemEmpoweredPick(int par1, EnumToolMaterial par3EnumToolMaterial,
            String texture)
    {
        super(par1, par3EnumToolMaterial);
        this.setMaxDamage(-1);
        itemTexture = texture;
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        iconIndex = iconRegister
                .registerIcon(Strings.modID + ":" + itemTexture);
    }

    @SideOnly(Side.CLIENT)
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack,
            String keyBinding)
    {
    }

}
