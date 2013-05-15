package net.lomeli.magiks.blocks.aesthetic;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockMechroPanes extends BlockPane
{
	protected Icon blockIcons;
	protected String blockTexture;
	
	public BlockMechroPanes(int par1, String par2Str) 
	{
		super(par1, par2Str, par2Str, Material.glass, true);
		this.blockTexture = par2Str;
		this.setHardness(0.5F);
		this.setResistance(5000F);
		this.setCreativeTab(Magiks.modTab);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Strings.MOD_ID + ":" + blockTexture);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getSideTextureIndex()
	{
		return this.blockIcon;
	}
	
	@Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

}
