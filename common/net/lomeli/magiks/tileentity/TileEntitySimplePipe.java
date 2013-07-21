package net.lomeli.magiks.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntitySimplePipe extends TileEntity 
	implements IInventory
{
	private ItemStack[] inventory;
	
	private boolean[] sides;

	public TileEntitySimplePipe()
	{
		inventory = new ItemStack[1];
		sides = new boolean[4];
	}
	
	public boolean getSides(int i){
		return sides[i];
	}
	
	@Override
    public void updateEntity()
    {
		super.updateEntity();
		
		if(this.worldObj != null && this.worldObj.isRemote)
		{
			/* Just trying this to work, then I'll tweak it to work the way I want it. 
			 * Lot of code copied from vanilla hopper, because I'm lazy
			 * It's almost working, but the pipe isn't updating the modified inventories
			 */
			IInventory leftInventory = getInventoryAtLocation(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord);
			if(leftInventory != null)
			{
				leftInventory.onInventoryChanged();
				this.suckItems(leftInventory);
			}
			IInventory rightInventory = getInventoryAtLocation(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord);
			if(rightInventory != null)
			{
				rightInventory.onInventoryChanged();
				if(this.inventory[0] != null)
					this.insertItemToInventory(rightInventory);
			}
		}
    }
	
	private boolean insertItemToInventory(IInventory inventoryOut)
    {
        IInventory iinventory = inventoryOut;

        if (iinventory == null)
        {
            return false;
        }
        else
        {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                if (this.getStackInSlot(i) != null)
                {
                    ItemStack itemstack = this.getStackInSlot(i).copy();
                    ItemStack itemstack1 = insertStack(iinventory, this.decrStackSize(i, 1), Facing.oppositeSide[BlockHopper.getDirectionFromMetadata(this.getBlockMetadata())]);

                    if (itemstack1 == null || itemstack1.stackSize == 0)
                    {
                        iinventory.onInventoryChanged();
                        return true;
                    }

                    this.setInventorySlotContents(i, itemstack);
                }
            }

            return false;
        }
    }
	
	private boolean func_102012_a(IInventory inventory, IInventory par1IInventory, int par2, int par3)
    {
        ItemStack itemstack = par1IInventory.getStackInSlot(par2);

        if (itemstack != null && canExtractItemFromInventory(par1IInventory, itemstack, par2, par3))
        {
            ItemStack itemstack1 = itemstack.copy();
            ItemStack itemstack2 = insertStack(inventory, par1IInventory.decrStackSize(par2, 1), -1);

            if (itemstack2 == null || itemstack2.stackSize == 0)
            {
                par1IInventory.onInventoryChanged();
                return true;
            }

            par1IInventory.setInventorySlotContents(par2, itemstack1);
        }

        return false;
    }

	private boolean suckItems(IInventory iinventory)
	{
		 if (iinventory != null)
	        {
	            byte b0 = 0;

	            if (iinventory instanceof ISidedInventory && b0 > -1)
	            {
	                ISidedInventory isidedinventory = (ISidedInventory)iinventory;
	                int[] aint = isidedinventory.getAccessibleSlotsFromSide(b0);

	                for (int i = 0; i < aint.length; ++i)
	                {
	                    if (func_102012_a(this, iinventory, aint[i], b0))
	                    {
	                        return true;
	                    }
	                }
	            }
	            else
	            {
	                int j = iinventory.getSizeInventory();

	                for (int k = 0; k < j; ++k)
	                {
	                    if (func_102012_a(this, iinventory, k, b0))
	                    {
	                        return true;
	                    }
	                }
	            }
	            iinventory.onInventoryChanged();
	        }

	        return false;
	}
	
	public ItemStack insertStack(IInventory par0IInventory, ItemStack par1ItemStack, int par2)
    {
        if (par0IInventory instanceof ISidedInventory && par2 > -1)
        {
            ISidedInventory isidedinventory = (ISidedInventory)par0IInventory;
            int[] aint = isidedinventory.getAccessibleSlotsFromSide(par2);

            for (int j = 0; j < aint.length && par1ItemStack != null && par1ItemStack.stackSize > 0; ++j)
            {
                par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, aint[j], par2);
            }
        }
        else
        {
            int k = par0IInventory.getSizeInventory();

            for (int l = 0; l < k && par1ItemStack != null && par1ItemStack.stackSize > 0; ++l)
            {
                par1ItemStack = func_102014_c(par0IInventory, par1ItemStack, l, par2);
            }
        }

        if (par1ItemStack != null && par1ItemStack.stackSize == 0)
        {
            par1ItemStack = null;
        }

        return par1ItemStack;
    }
	
	private ItemStack func_102014_c(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3)
    {
        ItemStack itemstack1 = par0IInventory.getStackInSlot(par2);

        if (canInsertItemToInventory(par0IInventory, par1ItemStack, par2, par3))
        {
            boolean flag = false;

            if (itemstack1 == null)
            {
                par0IInventory.setInventorySlotContents(par2, par1ItemStack);
                par1ItemStack = null;
                flag = true;
            }
            else if (areItemStacksEqualItem(itemstack1, par1ItemStack))
            {
                int k = par1ItemStack.getMaxStackSize() - itemstack1.stackSize;
                int l = Math.min(par1ItemStack.stackSize, k);
                par1ItemStack.stackSize -= l;
                itemstack1.stackSize += l;
                flag = l > 0;
            }

            if (flag)
            {
                if (par0IInventory instanceof TileEntityHopper)
                {
                    ((TileEntityHopper)par0IInventory).setTransferCooldown(8);
                    par0IInventory.onInventoryChanged();
                }

                par0IInventory.onInventoryChanged();
            }
        }

        return par1ItemStack;
    }
	
	private boolean canInsertItemToInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3)
    {
        return !par0IInventory.isItemValidForSlot(par2, par1ItemStack) ? false : !(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canInsertItem(par2, par1ItemStack, par3);
    }
	
	private boolean areItemStacksEqualItem(ItemStack par0ItemStack, ItemStack par1ItemStack)
    {
        return par0ItemStack.itemID != par1ItemStack.itemID ? false : (par0ItemStack.getItemDamage() != par1ItemStack.getItemDamage() ? false : (par0ItemStack.stackSize > par0ItemStack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(par0ItemStack, par1ItemStack)));
    }
	
	private boolean canExtractItemFromInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3)
    {
        return !(par0IInventory instanceof ISidedInventory) || ((ISidedInventory)par0IInventory).canExtractItem(par2, par1ItemStack, par3);
    }
	
	@SuppressWarnings("rawtypes")
    public IInventory getInventoryAtLocation(World world, double x, double y, double z)
    {
        IInventory iinventory = null;
        int i = MathHelper.floor_double(x);
        int j = MathHelper.floor_double(y);
        int k = MathHelper.floor_double(z);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof IInventory)
        {
            iinventory = (IInventory)tileentity;

            if (iinventory instanceof TileEntityChest)
            {
                int l = world.getBlockId(i, j, k);
                Block block = Block.blocksList[l];

                if (block instanceof BlockChest)
                {
                    iinventory = ((BlockChest)block).getInventory(world, i, j, k);
                }
            }
        }

        if (iinventory == null)
        {
            List list = world.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), IEntitySelector.selectInventories);

            if (list != null && list.size() > 0)
            {
                iinventory = (IInventory)list.get(world.rand.nextInt(list.size()));
            }
        }

        return iinventory;
    }
	
	@Override
    public int getSizeInventory()
    {
	    return inventory.length;
    }

	@Override
    public ItemStack getStackInSlot(int i)
    {
	    return inventory[i];
    }

	@Override
    public ItemStack decrStackSize(int slot, int amount)
    {
		ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
        	if(itemStack.stackSize <= amount)
        		setInventorySlotContents(slot, null);
        	else
        	{
        		itemStack.splitStack(amount);
        		if (itemStack.stackSize == 0)
        			setInventorySlotContents(slot, null);
        	}
        }

        return itemStack;
    }

	@Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

	@Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

	@Override
    public String getInvName(){
	    return null;
    }

	@Override
    public boolean isInvNameLocalized(){
	    return false;
    }

	@Override
    public int getInventoryStackLimit(){
	    return 64;
    }

	@Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer){
	    return true;
    }

	@Override
    public void openChest(){}

	@Override
    public void closeChest(){}

	@Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
		if(getStackInSlot(i) == null || (getStackInSlot(i).isItemEqual(itemstack) && 
			getStackInSlot(i).stackSize <= getStackInSlot(i).getMaxDamage()))
			return true;
		else
			return false;
    }

}
