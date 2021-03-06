package net.lomeli.magiks.tileentity;

import java.util.List;

import net.lomeli.magiks.lib.ModStrings;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityHollowWood extends TileEntity implements
		IInventory
{
	private ItemStack[] inventory;
	
	public TileEntityHollowWood()
	{
		inventory = new ItemStack[14];
	}
	
    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }
    
    public void setItemStack(int slot, ItemStack item)
    {
    	inventory[slot] = item;
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
            if (itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            } else
            {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
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
        return ModStrings.containerHollowWood;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return false;
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
    public void openChest()
    {
    }

    @Override
    public void closeChest()
    {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < 14)
            {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }

        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

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
    @SuppressWarnings("rawtypes")
    public void updateEntity()
    {
    	if (this.worldObj != null && !this.worldObj.isRemote)
        {
			List list = this.worldObj.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool()
    			.getAABB(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1.0D, this.yCoord + 1.0D, this.zCoord + 1.0D), 
    			IEntitySelector.selectAnything);
    		EntityItem entityItem = list.size() > 0 ? (EntityItem)list.get(0) : null;
    		if(entityItem != null)
    		{
    			//System.out.println(entityItem.getEntityName());
    			for(int i = 0; i < inventory.length; i++)
    			{
    				ItemStack itemStack = entityItem.getEntityItem().copy();
    				if(this.getStackInSlot(i) == null)
    				{
    					this.setInventorySlotContents(i, itemStack);
    					entityItem.setDead();
    				}
    			}
    		}
        }
    }
}
