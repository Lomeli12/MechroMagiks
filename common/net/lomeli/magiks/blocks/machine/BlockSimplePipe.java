package net.lomeli.magiks.blocks.machine;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.tileentity.TileEntitySimplePipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSimplePipe extends BlockContainer
{

	public BlockSimplePipe(int par1)
    {
	    super(par1, Material.rock);
	    this.setCreativeTab(Magiks.modTab);
	    this.setBlockBounds(0F, 0.25F, 0F, 1F, 0.5F, 1F);
    }

	@Override
    public TileEntity createNewTileEntity(World world)
    {
	    return new TileEntitySimplePipe();
    }
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

	@Override
    public int getRenderType()
    {
        return RenderIDs.simplePipeID;
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
}
