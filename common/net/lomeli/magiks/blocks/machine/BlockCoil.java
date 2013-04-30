package net.lomeli.magiks.blocks.machine;

import net.lomeli.magiks.blocks.BlockMagiks;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntityCoil;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCoil extends BlockMagiks{

	public BlockCoil(int par1, Material par2Material, String texture) {
		super(par1, par2Material, texture);
	}

    @Override
    public TileEntity createTileEntity(World world, int meta) 
    {
        return new TileEntityCoil();
    }

    @Override
    public boolean renderAsNormalBlock() 
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube() 
    {
        return false;
    }
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    @Override
    public int getRenderType()
    {
        return RenderIDs.coilID;
    }
}
