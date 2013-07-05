package net.lomeli.magiks.blocks.machine.parts;

import java.util.Random;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityBuilder;
import net.lomeli.magiks.tileentity.TileEntityBuilderDummy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBuilderDummy extends BlockContainer
{
	public BlockBuilderDummy(int par1)
    {
	    super(par1, Material.iron);
	    setUnlocalizedName("blockBuilderDummy");
        setStepSound(Block.soundStoneFootstep);
        setHardness(3.5f);
    }

	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModBlocksMagiks.builderBlock.blockID;
    }
	
	@Override
    public TileEntity createNewTileEntity(World world)
    {
		return new TileEntityBuilderDummy();
    }
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
	@Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":burningstone");
    }
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
		TileEntityBuilderDummy dummy = (TileEntityBuilderDummy) world
                .getBlockTileEntity(x, y, z);

		if (dummy != null && dummy.getCore() != null)
        {
			
        }
		
		super.breakBlock(world, x, y, z, par5, par6);
    }
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;

        TileEntityBuilderDummy dummy = (TileEntityBuilderDummy) world
                .getBlockTileEntity(x, y, z);

        if (dummy != null && dummy.getCore() != null)
        {
        	TileEntityBuilder core = dummy.getCore();
            if(core.isValidMultiBlock())
            	return core.getBlockType().onBlockActivated(world, core.xCoord,
                    core.yCoord, core.zCoord, player, par6, par7, par8, par9);
        }

        return true;
    }
}
