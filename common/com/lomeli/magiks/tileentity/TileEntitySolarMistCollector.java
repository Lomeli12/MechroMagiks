package com.lomeli.magiks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.lomeli.magiks.api.magiks.IMagiks;
import com.lomeli.magiks.blocks.machine.BlockSolarMistCollector;
import com.lomeli.magiks.items.ModItemsMagiks;
import com.lomeli.magiks.lib.Strings;

public class TileEntitySolarMistCollector extends TileEntity implements IInventory, IMagiks
{
    private ItemStack[] inventory;
    private int maxMistLevel = 3000,
            mistLevel = 0, tick = 0;

    public TileEntitySolarMistCollector()
    {
        inventory = new ItemStack[2];
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
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
        return Strings.containerSolarGen;
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
            mistLevel = tagCompound.getInteger("Mist");
            if (slot >= 0 && slot < 2)
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
                tagCompound.setInteger("Mist", mistLevel);
        
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Inventory", tagList);
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
    public boolean isStackValidForSlot(int slot, ItemStack itemstack)
    {
        return true;
    }
    
    /*@Override
    public void updateEntity()
    {
        if(getStackInSlot(0).itemID == ModItemsMagiks.flyingRing.itemID)
        {
            ItemStack ring = getStackInSlot(0);
            if(ring.isItemDamaged())
            {
                ring.setItemDamage(ring.getItemDamage() - 1);
                addToMistLevel(-1);
            }
        }
        if (this.worldObj != null && !this.worldObj.isRemote && this.worldObj.getTotalWorldTime() % 20L == 0L)
        {
            this.blockType = this.getBlockType();
            if (this.blockType != null && this.blockType instanceof BlockSolarMistCollector)
            {
                if(getMistLevel() <= maxMistLevel)
                {
                    addToMistLevel(10);
                }
            }
            
        }
        
    }*/

    @Override
    public int getMistLevel()
    {
        return mistLevel;
    }

    @Override
    public void setMistLevel(int value)
    {
        mistLevel = value;
    }

    @Override
    public void addToMistLevel(int value)
    {
        mistLevel += value;
    }

    @Override
    public int getHeatLevel()
    {
        return 0;
    }

    @Override
    public void setHeatLevel(int temp)
    {
    }

    @Override
    public void addToHeatLevel(int temp)
    {
    }

    @Override
    public int getMaxMistLevel()
    {
        return maxMistLevel;
    }

    @Override
    public boolean hasMist()
    {
        if(getMistLevel() >= 0)
            return true;
        else
            return false;
    }
}
