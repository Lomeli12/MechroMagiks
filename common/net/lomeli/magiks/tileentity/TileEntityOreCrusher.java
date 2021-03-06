package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.machines.OreCrusherManager;
import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntityOreCrusher extends TileEntityMagiks implements
	ISidedInventory
{
	public static final int[] sidedTop = new int[] { 0 };
	public static final int[] sidedOther = new int[] { 1, 2};
	
	private ItemStack[] inventory;
	
	private int mistLevel, maxMistLevel; 
	
	public int processingTime;
	
	private EnumMachineTypes type;
	
	public TileEntityOreCrusher()
	{
		inventory = new ItemStack[3];
		this.maxMistLevel = 3000;
		type = EnumMachineTypes.MACHINE;
	}
	
	public int getProgress()
	{
		return (int)((processingTime / 269D) * 100D);
	}
	
	@Override
    public void updateEntity()
    {
		super.updateEntity();
		if(this.worldObj != null)
		{
			if(mistLevel > 270 && canCrushOre())
			{
				this.processingTime++;
				this.mistLevel--;
				if(inventory[1] == null || getStackInSlot(1).stackSize < 64)
				{	
					if(this.processingTime >= 270)
					{
						addToStack();
						decrStackSize(0, 1);
						this.processingTime = 0;
					}
				}
			}
			else
				this.processingTime = 0;
		}
    }
	
	@Override
	public EnumMachineTypes getType()
	{
		return type;
	}
	
	public ItemStack addToStack()
	{
		ItemStack itemStack = OreCrusherManager.getInstance().getCrushResult(this.inventory[0]);
		if(itemStack != null)
		{
			if(inventory[1] == null)
				this.setInventorySlotContents(1, itemStack);
			else if(inventory[1].isItemEqual(itemStack) && inventory[1].stackSize <= 64)
				inventory[1].stackSize += itemStack.stackSize;
			else if(inventory[2] == null)
				this.setInventorySlotContents(2, itemStack);
			else if(inventory[2].isItemEqual(itemStack) && inventory[2].stackSize <= 64)
				inventory[2].stackSize += itemStack.stackSize;
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
			if(this.inventory[1] != null)
			{ if(!this.inventory[1].isItemEqual(item)) { return false; } }
			int result;
			if(this.inventory[1] == null)
				result = item.stackSize;
			else
				result = this.inventory[1].stackSize + item.stackSize;
			
			return (result <= getInventoryStackLimit() && result <= item.getMaxStackSize());
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        
        loadNBT(nbtTagCompound);
    }
    
    @Override
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
            if (slot >= 0 && slot < 3)
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
    public Packet getDescriptionPacket() 
    {
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();

        addToNBT(tag);

        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) 
    {
        super.onDataPacket(net, pkt);
        NBTTagCompound tag = pkt.customParam1;
        loadNBT(tag);
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		ItemStack slotItem = this.inventory[i];
		if(slotItem == null)
			return true;
		else
		{
			if(slotItem.isItemEqual(itemstack))
				return true;
			else
				return false;
		}
	}

	@Override
    public String getInvName()
    {
	    return ModStrings.containerOreCrusher;
    }
	
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		return var1 == 1 ? sidedTop : sidedOther;
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		if(side == 1)
			return this.isItemValidForSlot(0, itemstack);
		else
			return false;
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) 
	{
		return true;
	}
}
