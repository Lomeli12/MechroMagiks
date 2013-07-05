package net.lomeli.magiks.blocks.aesthetic;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockMecroGlass extends BlockGlass
{
    private String blockTexture;

    public BlockMecroGlass(int par1, Material par2Material, String texture)
    {
        super(par1, par2Material, true);
        this.setCreativeTab(Magiks.modTab);
        blockTexture = texture;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":"
                + blockTexture);
    }
}