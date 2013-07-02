package net.lomeli.magiks.blocks.machine;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMancerWorkTable extends BlockContainer
{
    private String blockTexture;

	public BlockMancerWorkTable(int par1, Material par2Material, String texture) 
	{
		super(par1, par2Material);
		blockTexture = texture;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.87F, 1.0F);
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.MOD_ID + ":"
                + blockTexture);
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
    public int getRenderType()
    {
        return RenderIDs.mancerWTID;
    }
    
	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityMancerWorkTable();
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
            	TileEntityMancerWorkTable tileEntity = (TileEntityMancerWorkTable) world
            		.getBlockTileEntity(x, y, z);
            	if(tileEntity != null)
            		player.openGui(Magiks.instance, GuiIDs.mancerWT, world, x, y, z);
            }
        }
        return true;
    }
}
