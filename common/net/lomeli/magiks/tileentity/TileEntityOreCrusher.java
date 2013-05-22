package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.machines.OreCrusherManager;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityOreCrusher extends TileEntityMagiks implements
	IInventory
{
	private ItemStack[] inventory;
	
	private int mistLevel, maxMistLevel; 
	public int processingTime;
	
	public TileEntityOreCrusher()
	{
		inventory = new ItemStack[4];
		this.maxMistLevel = 3000;
		this.mistLevel = 3000;
	}
	
	@Override
    public void updateEntity()
    {
		if(this.worldObj != null && !this.worldObj.isRemote)
		{
			if(canCrushOre())
			{
				if(inventory[2] == null || getStackInSlot(2).stackSize < 64)
				{
					processingTime++;
					if(processingTime >= 270)
					{
						addToStack();
						decrStackSize(0, 1);
						processingTime = 0;
					}
					mistLevel--;
				}
			}
			else
				processingTime = 0;
		}
    }
	
	public ItemStack addToStack()
	{
		ItemStack itemStack = OreCrusherManager.getInstance().getCrushResult(this.inventory[0]);
		if(itemStack != null)
		{
			if(inventory[2] == null)
				inventory[2] = itemStack.copy();
			else if(inventory[2].isItemEqual(itemStack) && inventory[2].stackSize <= 64)
				inventory[2].stackSize += itemStack.stackSize;
			else if(inventory[3] == null)
				inventory[3] = itemStack.copy();
			else if(inventory[3].isItemEqual(itemStack) && inventory[3].stackSize <= 64)
				inventory[3].stackSize += itemStack.stackSize;
		}
		
		return itemStack;
	}
	
	public boolean canCrushOre()
	{
		if(this.inventory[0] == null)
			return false;
		else
		{
			ItemStack item = OreCrusherManager.getInstance().getCrushResult(this.inventory[0]);
			if(item == null) { return false; }
			if(this.mistLevel > 300) { return true; }
			if(!this.inventory[2].isItemEqual(item)) { return false; }
			int result = this.inventory[2].stackSize + item.stackSize;
			return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());
		}
	}
	
	@Override
	public int getMistLevel()
	{
		return this.mistLevel;
	}
	
	@Override
	public void setMistLevel(int value)
	{
		this.mistLevel = value;
	}
	
	@Override
	public void addToMistLevel(int value)
	{
		this.mistLevel += value;
	}
	
	@Override
	public int getMaxMistLevel()
	{
		return this.maxMistLevel;
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
            if (slot >= 0 && slot < 4)
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
        }
    }
	
	public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagCompound tagCompound = new NBTTagCompound();
		
		NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < 4; ++currentIndex)
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
		return true;
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
	    return Strings.containerOreCrusher;
    }
}
