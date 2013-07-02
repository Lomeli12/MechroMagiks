package net.lomeli.magiks.blocks.machine;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityOreCrusher;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOreCrusher extends BlockContainer
{
	public static final int MASK_DIR = 0x00000007;
    public static final int META_DIR_NORTH = 2;
    public static final int META_DIR_SOUTH = 3;
    public static final int META_DIR_EAST = 5;
    public static final int META_DIR_WEST = 4;
    
	private Icon topSide;
	private Icon frontSide;
	private Icon defaultSide;
	
	public BlockOreCrusher(int par1)
    {
	    super(par1, Material.iron);
	    this.setHardness(2F);
	    this.setResistance(5F);
	    this.setCreativeTab(Magiks.modTab);
    }
	
	@Override
    public void registerIcons(IconRegister iconRegister)
    {
		defaultSide = iconRegister.registerIcon(Strings.MOD_ID + ":orecrusher");
		frontSide = iconRegister.registerIcon(Strings.MOD_ID + ":orecrusherfront");
		topSide = iconRegister.registerIcon(Strings.MOD_ID + ":orecrushertop");
        blockIcon = defaultSide;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t)
    {
        if (player.isSneaking())
            return false;
        else
        {
            if (!world.isRemote)
            {
            	TileEntityOreCrusher tileEntity = (TileEntityOreCrusher)world
            		.getBlockTileEntity(x, y, z);
            	if(tileEntity != null)
            		player.openGui(Magiks.instance, GuiIDs.crusher, world, x, y, z);
            }
        }
        return true;
    }

	@Override
    public TileEntity createNewTileEntity(World world)
    {
	    return new TileEntityOreCrusher();
    }
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

	@Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    private Random rand = new Random();

    private void dropInventory(World world, int x, int y, int z)
    {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z
                        + dZ, new ItemStack(itemStack.itemID,
                        itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound(
                            (NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
    
    @SuppressWarnings("unused")
	private static int getSideFromFacing(int facing)
    {
        switch (facing)
        {
            case META_DIR_WEST:
                return 4;

            case META_DIR_EAST:
                return 3;

            case META_DIR_NORTH:
                return 2;

            case META_DIR_SOUTH:
                return 5;

            default:
                return 4;
        }
    }
    
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
    
    @Override
    public Icon getIcon(int side, int metadata)
    {
    	return side == 0 ? defaultSide : side == 1 ? topSide : side != metadata ? defaultSide : frontSide;
    }
}
