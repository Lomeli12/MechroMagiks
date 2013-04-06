package com.lomeli.magiks.blocks;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockMagiks extends Block
{
    private String blockTexture;
    
    public BlockMagiks(int par1, Material par2Material, String texture)
    {
        super(par1, par2Material);
        this.blockTexture = texture;
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.modID + ":"
                + blockTexture);
    }
}
