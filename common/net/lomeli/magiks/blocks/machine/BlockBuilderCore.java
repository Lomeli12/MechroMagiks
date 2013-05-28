package net.lomeli.magiks.blocks.machine;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuilderCore extends BlockContainer
{

	protected BlockBuilderCore(int par1)
    {
	    super(par1, Material.iron);
    }

	@Override
    public TileEntity createNewTileEntity(World world)
    {
	    return null;
    }

}
