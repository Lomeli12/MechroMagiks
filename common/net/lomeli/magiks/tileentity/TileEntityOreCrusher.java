package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.machines.OreCrusherManager;
import net.lomeli.magiks.api.magiks.EnumMagiksType;
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
	
	private int mistLevel, maxMistLevel = 3000; 
	
	public int processingTime;
	
	private EnumMagiksType type;
	
	public TileEntityOreCrusher()
	{
		inventory = new ItemStack[4];
		type = EnumMagiksType.MACHINE;
	}
	
	public int getProgress()
	{
		return processingTime / 100;
	}
	
	@Override
    public void updateEntity()
    {
		if(this.worldObj != null && !this.worldObj.isRemote)
		{
			if(mistLevel > 270 && canCrushOre())
			{
				System.out.println("Yay");
				if(inventory[2] == null || getStackInSlot(2).stackSize < 64)
				{
					processingTime++;
					if(this.processingTime >= 270)
					{
						addToStack();
						decrStackSize(0, 1);
						this.processingTime = 0;
					}
					this.mistLevel--;
				}
			}
		}
    }
	
	@Override
	public EnumMagiksType getType()
	{
		return type;
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
			if(this.mistLevel > 270) { return true; }
			if(this.inventory[2] != null)
			{ if(!this.inventory[2].isItemEqual(item)) { return false; } }
			int result;
			if(this.inventory[2] == null)
				result = item.stackSize;
			else
				result = this.inventory[2].stackSize + item.stackSize;
			
			return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());
		}
	}
	
	public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        
        loadNBT(nbtTagCompound);
    }
    
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        addToNBT(nbtTagCompound);
    }
    
    public void loadNBT(NBTTagCompound nbtTagCompound)
    {
    	mistLevel = nbtTagCompound.getInteger("Mist");
        
        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < 4)
            {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }

        }
    }
    
    public void addToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setInteger("Mist", mistLevel);
        
        NBTTagCompound tagCompound = new NBTTagCompound();

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
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
