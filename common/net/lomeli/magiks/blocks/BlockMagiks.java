package net.lomeli.magiks.blocks;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagiks extends Block
{
    private String blockTexture;

    public BlockMagiks(int par1, Material par2Material, String texture)
    {
        super(par1, par2Material);
        blockTexture = texture;
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.MOD_ID + ":"
                + blockTexture);
    }
}