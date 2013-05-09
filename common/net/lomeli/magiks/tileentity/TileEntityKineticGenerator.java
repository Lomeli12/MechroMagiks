package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.crafting.KineticGenFuel;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.core.helper.BlockHelper;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityKineticGenerator extends TileEntityMagiks implements
        IInventory
{
    private ItemStack[] inventory;

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

        mistLevel = nbtTagCompound.getInteger("Mist");
        heatLevel = nbtTagCompound.getInteger("Heat");

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

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Mist", mistLevel);
        nbtTagCompound.setInteger("Heat", heatLevel);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
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
        if (worldObj != null && !worldObj.isRemote)
        {
            blockType = this.getBlockType();

            ItemStack item = getStackInSlot(0);
            if (item != null)
            {
                if (getMistLevel() != 0)
                {
                    for (ItemStack chargeable : MagiksArrays.rechargeableItems)
                    {
                        if (item.itemID == chargeable.itemID)
                        {
                            if (item.isItemDamaged())
                            {
                                item.setItemDamage(item.getItemDamage() - 1);
                                addToMistLevel(-1);
                            }
                        }
                    }
                }
            }

            ItemStack fuel = getStackInSlot(1);
            
            if (fuel != null)
            {
            	//System.out.println(canUseAsFuel(fuel));
            	if(canUseAsFuel(fuel))
            	{	
            		
            		++generationTime;
            		if (generationTime >= 100)
            		{
            			generationTime = 0;
            			this.decrStackSize(1, 1);
            			int index = MagiksArrays.kineticGenFuel.indexOf(new ItemStack(fuel.getItem()));
            			System.out.println(index);
            			this.addToMistLevel(75);
            			this.addToHeatLevel(50 / this.coolRate());
            			worldObj.playSoundEffect(xCoord, yCoord, zCoord,
                            "random.explode", 4F,
                            (1.0F + (worldObj.rand.nextFloat() - worldObj.rand
                                    .nextFloat()) * 0.2F) * 0.7F);
            			if (heatLevel < 80)
            				worldObj.spawnParticle("largeexplode", xCoord + 0.5,
            					yCoord + 1, zCoord + 0.5, 1D, 0, 0);
            			else
            				worldObj.spawnParticle("hugeexplosion", xCoord + 0.5,
                                yCoord + 1, zCoord + 0.5, 1D, 0, 0);
            		}
                }
            }

            if (heatLevel > 0)
            {
                ++coolDown;
                if (coolDown >= 400 / this.coolRate())
                {
                    this.addToHeatLevel(-5 * this.coolRate());
                } else if (coolDown >= 400 / this.coolRate()
                        && worldObj.isRaining())
                {
                    this.addToHeatLevel(-10 * this.coolRate());
                }
            } else if (heatLevel >= 250)
            {
                worldObj.destroyBlock(xCoord, yCoord, zCoord, true);
                worldObj.createExplosion((Entity) null, xCoord, yCoord, zCoord,
                        5, false);
            }
        }
    }

    private int coolRate()
    {
        int rate = 1;
        if (BlockHelper.getBlock(worldObj, xCoord + 1, yCoord, zCoord) == Block.ice
                || BlockHelper.getBlock(worldObj, xCoord, yCoord, zCoord + 1) == Block.ice
                || BlockHelper.getBlock(worldObj, xCoord + 1, yCoord,
                        zCoord + 1) == Block.ice
                || BlockHelper.getBlock(worldObj, xCoord - 1, yCoord, zCoord) == Block.ice
                || BlockHelper.getBlock(worldObj, xCoord, yCoord, zCoord - 1) == Block.ice
                || BlockHelper.getBlock(worldObj, xCoord - 1, yCoord,
                        zCoord - 1) == Block.ice)
        {
            rate++;
        }
        return rate;
    }

    public boolean isInUse()
    {
        return generationTime >= 0;
    }

    private boolean canUseAsFuel(ItemStack item)
    {
    	return KineticGenFuel.fuel().isValidItem(item);
    }
}
