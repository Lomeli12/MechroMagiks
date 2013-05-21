package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityOreCrusher extends TileEntityMagiks implements
	IInventory
{
	private ItemStack[] inventory;
	
	private int mistLevel, maxMistLevel, processingTime;
	
	public TileEntityOreCrusher()
	{
		inventory = new ItemStack[4];
	}
	
	@Override
    public void updateEntity()
    {
		if(this.worldObj != null)
		{
			ItemStack crushedOre = getStackInSlot(0);
			if(crushedOre != null)
			{
				
			}
		}
    }
	
	public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        
        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            mistLevel = tagCompound.getInteger("Mist");
            
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < 2)
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
        }
    }
	
	public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagCompound tagCompound = new NBTTagCompound();
		
		NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
        	tagCompound.setInteger("Mist", mistLevel);
            if (inventory[currentIndex] != null)
            {
                tagCompound.setByte("Slot", (byte) currentIndex);

                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Inventory", tagList);
    }

	@Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }
    
    @Override
	public int getSizeInventory() 
	{
		return this.inventory.length;
	}

	@Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
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
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) 
	{
		ItemStack slotItem = this.inventory[i];
		if(slotItem == null)
			return true;
		else
		{
			if(slotItem != itemstack)
				return false;
			else
				return true;
		}
	}

	@Override
    public String getInvName()
    {
	    return null;
    }
}
