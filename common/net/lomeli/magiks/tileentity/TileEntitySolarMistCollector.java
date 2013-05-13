package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.api.tiles.TileEntityMagiks;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntitySolarMistCollector extends TileEntityMagiks implements
        IInventory
{	
    private ItemStack[] inventory;

    public TileEntitySolarMistCollector()
    {
        inventory = new ItemStack[1];
        this.setMaxMistLevel(30000);
    }

    @Override
    public String getInvName()
    {
        return Strings.containerSolarGen;
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
                	//System.out.println(this.worldObj.getWorldTime());
                	this.addToMistLevel(1);
                	this.worldObj.spawnParticle("portal", (xCoord+0.5), (yCoord+1), (zCoord+0.5), 1F, 1F, 1F);
                }
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        
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

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Mist", this.mistLevel);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);

                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Inventory", tagList);
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
}
