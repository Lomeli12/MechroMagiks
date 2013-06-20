package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.core.handler.ErrorHandler;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCoil extends TileEntityMagiks
	implements IInventory
{
	private ItemStack[] inventory;
	
	private int maxMistLevel, mistLevel, scanRadius;
	
	public int connectedGen, connectedMachine, localBatboxs;
	
	private EnumMachineTypes type;
	
	public TileEntityCoil()
	{
		inventory = new ItemStack[1];
		maxMistLevel = 7000;
		scanRadius = 10;
		type = EnumMachineTypes.BATBOX;
	}
	
	@Override
    public void updateEntity()
    {
		scanArea();
        if(inventory[0] != null)
        {
        	if(inventory[0].getItemDamage() > 0)
        	{
        		if(canRecharge(inventory[0]) && this.mistLevel > 0)
        		{
        			inventory[0].setItemDamage(inventory[0].getItemDamage() - 1);
        			this.mistLevel--;
        		}
        	}
        }
    }
	
	public boolean canRecharge(ItemStack stack)
	{
		for(ItemStack recharge : MagiksArrays.rechargeableItems)
		{
			if(stack.itemID == recharge.itemID)
				return true;
		}
		
		return false;
	}
	
	public void scanArea()
	{
		connectedGen = 0; 
		connectedMachine = 0; 
		localBatboxs = -1;
		for(int x = (this.xCoord - scanRadius); x < (this.xCoord + scanRadius); x++)
		{
			for(int y = (this.yCoord - scanRadius); y < (this.yCoord + scanRadius); y++)
			{
				for(int z = (this.zCoord - scanRadius); z < (this.zCoord + scanRadius); z++)
				{
					TileEntity tile = worldObj.getBlockTileEntity(x, y, z);
					if(tile != null && tile.getClass().getSuperclass().equals(TileEntityMagiks.class))
					{
						TileEntityMagiks mistMachine = (TileEntityMagiks)tile;
						if(mistMachine.getType().equals(EnumMachineTypes.MACHINE))
						{
							connectedMachine++;
							if(mistLevel > 0 && mistMachine.getMistLevel() < mistMachine.getMaxMistLevel())
							{
								mistLevel--;
								mistMachine.addToMistLevel(1);
							}
						}
						else if(mistMachine.getType().equals(EnumMachineTypes.GENERATOR))
						{
							connectedGen++;
							if(mistLevel < maxMistLevel && mistMachine.getMistLevel() > 0)
							{
								mistMachine.addToMistLevel(-1);
								mistLevel++;
							}
						}
						else if(mistMachine.getType().equals(EnumMachineTypes.BATBOX))
						{
							localBatboxs++;
							if(mistLevel < 100 && mistMachine.getMistLevel() > 0)
							{
								mistMachine.addToMistLevel(-1);
								mistLevel++;
							}
						}
						else
							ErrorHandler.unknowEnumMagiksType(this, mistMachine.getType());
					}
				}
			}
		}
	}
	
	@Override
	public int getMistLevel()
    {
		return mistLevel;
    }
	
	@Override
	public int getMaxMistLevel()
    {
		return maxMistLevel;
    }
	
	@Override
	public EnumMachineTypes getType()
	{
		return type;
	}
	
	@Override
    public int getSizeInventory()
    {
	    return inventory.length;
    }

	@Override
    public ItemStack getStackInSlot(int i)
    {
		if(inventory[i] != null)
			return inventory[i];
		else
			return inventory[0];
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
    public String getInvName()
    {
	    return Strings.containerBuilder;
    }

	@Override
    public boolean isInvNameLocalized()
    {
	    return true;
    }

	@Override
    public int getInventoryStackLimit()
    {
	    return 64;
    }

	@Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
	    return true;
    }

	@Override
    public void openChest()
    {}

	@Override
    public void closeChest()
    {}

	@Override
    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
		if(getStackInSlot(i).isItemEqual(itemstack) && 
			getStackInSlot(i).stackSize <= getStackInSlot(i).getMaxDamage())
			return true;
		else
			return false;
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
    	this.mistLevel = nbtTagCompound.getInteger("Mist");
        
        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < inventory.length)
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
}
