package net.lomeli.magiks.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.lib.RenderIDs;
import net.lomeli.magiks.lib.Strings;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockLinkingChest extends BlockContainer
{
	private String blockTexture;
	protected BlockLinkingChest(int par1, String texture) 
	{
		super(par1, Material.rock);
		this.setCreativeTab(Magiks.modTab);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		this.blockTexture = texture;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.MOD_ID + ":"
                + blockTexture);
    }

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityLinkingChest();
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
    	return RenderIDs.chestID;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) 
    {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) 
    {
        if (player.isSneaking())
            return true;
        else if (world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN))
            return true;
        else 
        {
        	if(!world.isRemote)
        	{
        		TileEntityLinkingChest tile = (TileEntityLinkingChest)world.getBlockTileEntity(x, y, z);
        		
        		if(tile != null)
        			player.openGui(Magiks.instance, GuiIDs.chest, world, x, y, z);
        	}
        	return true;
        }
    }
    
    private Random rand = new Random();
    
    private void dropInventory(World world, int x, int y, int z) 
    {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) 
    {     
        int direction = 0;
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0) {
            direction = ForgeDirection.NORTH.ordinal();
        }
        else if (facing == 1) {
            direction = ForgeDirection.EAST.ordinal();
        }
        else if (facing == 2) {
            direction = ForgeDirection.SOUTH.ordinal();
        }
        else if (facing == 3) {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);

        ((TileEntityLinkingChest) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
    }
}
