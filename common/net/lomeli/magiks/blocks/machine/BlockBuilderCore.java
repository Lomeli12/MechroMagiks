package net.lomeli.magiks.blocks.machine;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.tileentity.TileEntityBuilder;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBuilderCore extends BlockContainer
{
	public static final int META_ISACTIVE = 0x00000008;
    public static final int MASK_DIR = 0x00000007;
    public static final int META_DIR_NORTH = 2;
    public static final int META_DIR_SOUTH = 3;
    public static final int META_DIR_EAST = 5;
    public static final int META_DIR_WEST = 4;
	
	public BlockBuilderCore(int par1)
    {
	    super(par1, Material.iron);
	    this.setCreativeTab(Magiks.modTab);
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;
        else
        {
        	TileEntityBuilder tileEntity = (TileEntityBuilder)world.getBlockTileEntity(x, y, z);
        	if(tileEntity != null)
        	{
        		if(tileEntity.isValidMultiBlock())
        		{
        			
        		}
        		else
        		{
        			if(tileEntity.checkIfProperlyFormed())
        				tileEntity.convertDummies();
        		}
        	}
        		//player.sendChatToPlayer(tileEntity.checkIfProperlyFormed() + "");
        	return true;
        }
    }

	@Override
    public TileEntity createNewTileEntity(World world)
    {
	    return new TileEntityBuilder();
    }
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLiving entity, ItemStack itemStack)
    {
        int metadata = 0;
        int facing = META_DIR_WEST;

        int dir = MathHelper.floor_double(entity.rotationYaw * 4F / 360F + 0.5) & 3;
        if (dir == 0)
        {
            facing = META_DIR_NORTH;
        }
        if (dir == 1)
        {
            facing = META_DIR_EAST;
        }
        if (dir == 2)
        {
            facing = META_DIR_SOUTH;
        }
        if (dir == 3)
        {
            facing = META_DIR_WEST;
        }

        metadata |= facing;
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }
}
