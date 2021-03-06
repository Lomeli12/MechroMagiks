package net.lomeli.magiks.blocks;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockMagikStairs extends BlockStairs
{
	private String blockTexture;

	protected BlockMagikStairs(int par1, Block block, int par3, String texture) 
	{
		super(par1, block, par3);
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
