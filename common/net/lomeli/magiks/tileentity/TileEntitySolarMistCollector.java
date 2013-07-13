package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntitySolarMistCollector extends TileEntityMagiks implements
        IInventory
{	
	public TileEntityMagiks connected;
    
	public int maxMistLevel = 2000, 
			mistLevel, 
			generationTime = 0, tick;
	
	private EnumMachineTypes type;
	
    private ItemStack[] inventory;

    public TileEntitySolarMistCollector()
    {
        inventory = new ItemStack[1];
        type = EnumMachineTypes.GENERATOR;
    }

    @Override
    public String getInvName()
    {
        return ModStrings.containerSolarGen;
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
    public void updateEntity()
    {
        if (this.worldObj != null)
        {
            ItemStack item = getStackInSlot(0);
            if (item != null)
            {
                if (this.getMistLevel() != 0)
                {
                    for (ItemStack chargeable : MagiksArrays.rechargeableItems)
                    {
                        if (item.itemID == chargeable.itemID)
                        {
                            if (item.isItemDamaged())
                            {
                                item.setItemDamage(item.getItemDamage() - 1);
                                this.addToMistLevel(-1);
                            }
                        }
                    }
                }
            }
            if (this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && this.worldObj.isDaytime()
            	&& !this.worldObj.isRaining() && !this.worldObj.isThundering())
            {
                if (this.getMistLevel() <= this.getMaxMistLevel())
                {
                	this.generationTime++;
                	if(this.generationTime >= 30)
                	{
                		this.addToMistLevel(1);
                		this.generationTime = 0;
                	}
                	this.worldObj.spawnParticle("portal", (xCoord+0.5), (yCoord+1), (zCoord+0.5), 1F, 1F, 1F);
                }
            }
        }
    }
    
    @Override
	public EnumMachineTypes getType()
	{
		return type;
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
    	this.mistLevel = nbtTagCompound.getInteger("Mist");
        
        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < 2)
            {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }

        }
    }
    
    public void addToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setInteger("Mist", this.mistLevel);
        
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
            setInventorySlotContents(slot, null);
        
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            itemStack.stackSize = getInventoryStackLimit();
        
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
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
	
	// TileEntityMagiks stufff
	
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
    public void setMaxMistLevel(int value)
    {
		this.maxMistLevel = value;
    }
	
	@Override
	public boolean hasMist()
    {
		if(this.getMistLevel() > 0)
			return true;
		else
			return false;
    }
}
