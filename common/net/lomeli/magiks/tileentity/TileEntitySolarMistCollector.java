package net.lomeli.magiks.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolarMistCollector extends TileEntity implements
        IInventory
{
	
	public int maxMistLevel = 30000, mistLevel = 0, heatLevel, generationTime = 0,
            coolDown = 0, spawnParticle = 0;
	
    private ItemStack[] inventory;

    public TileEntitySolarMistCollector()
    {
        inventory = new ItemStack[1];
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
        if (this.worldObj != null && !this.worldObj.isRemote)
        {
            ItemStack item = getStackInSlot(0);
            if (item != null)
            {
                if (this.mistLevel != 0)
                {
                    for (ItemStack chargeable : MagiksArrays.rechargeableItems)
                    {
                        if (item.itemID == chargeable.itemID)
                        {
                            if (item.isItemDamaged())
                            {
                                item.setItemDamage(item.getItemDamage() - 1);
                                this.mistLevel--;
                            }
                        }
                    }
                }
            }
            if (worldObj.isDaytime() && !worldObj.isRaining())
            {
                if (this.mistLevel <= this.maxMistLevel)
                {
                    this.mistLevel++;
                    System.out.println(""+this.mistLevel);
                    worldObj.spawnParticle("portal", (xCoord+0.5), (yCoord+1), (zCoord+0.5), 1F, 1F, 1F);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getMistScaled(int scaleVal)
    {
        return this.mistLevel * scaleVal / 100;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            this.mistLevel = tagCompound.getInteger("Mist");
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

        nbtTagCompound.setInteger("Mist", mistLevel);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
        	if(this.mistLevel != 0)
        	{
        		NBTTagCompound tagCompound = new NBTTagCompound();
        		tagCompound.setInteger("Mist", this.mistLevel);
        		
        		tagList.appendTag(tagCompound);
        	}
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
    
    public int getMistPercentage()
    {
    	return (this.mistLevel / this.maxMistLevel);
    }
}
