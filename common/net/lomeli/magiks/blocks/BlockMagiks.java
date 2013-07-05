package net.lomeli.magiks.blocks;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

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
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":"
                + blockTexture);
    }
}
