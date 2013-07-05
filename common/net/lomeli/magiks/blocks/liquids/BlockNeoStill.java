package net.lomeli.magiks.blocks.liquids;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

import net.minecraftforge.liquids.ILiquid;

public class BlockNeoStill extends BlockStationary implements ILiquid 
{

	public BlockNeoStill(int par1)
    {
	    super(par1, Material.water);
	    
	    this.setHardness(100F);
		this.setLightValue(1F);
		this.setUnlocalizedName("neoStill");
    }
	
	@Override
	public int getRenderType() 
	{
		return RenderIDs.liquidNeoStillID;
	}
	
	@Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":liquids/neo");
    }

	@Override
    public int stillLiquidId()
    {
	    return ModBlocksMagiks.neoStill.blockID;
    }

	@Override
    public boolean isMetaSensitive()
    {
	    return false;
    }

	@Override
    public int stillLiquidMeta()
    {
	    return 0;
    }
	
	@Override
	public boolean isBlockReplaceable(World world, int i, int j, int k) 
	{
		return true;
	}

}
