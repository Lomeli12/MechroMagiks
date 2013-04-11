package com.lomeli.magiks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.lomeli.magiks.api.magiks.IMagiks;
import com.lomeli.magiks.items.ModItemsMagiks;
import com.lomeli.magiks.lib.Strings;

public class TileEntityKineticGenerator extends TileEntity implements
        IInventory, IMagiks
{
    private ItemStack[] inventory;
    private int maxMistLevel = 3000, mistLevel = 100, heatLevel;

    public TileEntityKineticGenerator()
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
        return Strings.containterKineticGen;
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
            int mist = tagCompound.getInteger("Mist");
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
            NBTTagCompound tagCompound = new NBTTagCompound();
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
    
    @Override
    public void updateEntity()
    {
        this.blockType = this.getBlockType();

        ItemStack item = getStackInSlot(0);
        if(item !=  null)
        {
            if(getMistLevel() != 0)
            {
                if(item.itemID == ModItemsMagiks.flyingRing.itemID)
                {
                    if(item.isItemDamaged())
                    {
                        item.setItemDamage(item.getItemDamage() -1);
                        addToMistLevel(-1);
                    }
                }
            }
        }
    }

    @Override
    public boolean hasMist()
    {
        if (mistLevel >= 0)
            return true;
        else
            return false;
    }

    @Override
    public int getMistLevel()
    {
        return mistLevel;
    }

    @Override
    public int getHeatLevel()
    {
        return heatLevel;
    }
    
    @Override
    public void setMistLevel(int value)
    {
        mistLevel = value;
    }
    
    @Override
    public void addToMistLevel(int value)
    {
        setMistLevel(mistLevel + value);
    }

    @Override
    public void setHeatLevel(int temp)
    {
        mistLevel = temp;
    }

    @Override
    public void addToHeatLevel(int temp)
    {
        mistLevel += temp;
    }

    @Override
    public int getMaxMistLevel()
    {
        return maxMistLevel;
    }
}
