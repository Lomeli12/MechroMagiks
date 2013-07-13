package net.lomeli.magiks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBuilderDummy extends TileEntity implements
	ISidedInventory
{
	TileEntityBuilder tileEntityCore;
	int coreX;
    int coreY;
    int coreZ;
    
    public TileEntityBuilderDummy()
    {
    }
    
    public void setCore(TileEntityBuilder core)
    {
        coreX = core.xCoord;
        coreY = core.yCoord;
        coreZ = core.zCoord;
        tileEntityCore = core;
    }
    
    public void setCore(int x, int y, int z)
    {
    	coreX = x;
    	coreY = y;
    	coreZ = z;
    	tileEntityCore = (TileEntityBuilder)worldObj.getBlockTileEntity(coreX, coreY, coreZ);
    }
    
    public TileEntityBuilder getCore()
    {
    	if (tileEntityCore == null)
        {
    		tileEntityCore = (TileEntityBuilder)worldObj.getBlockTileEntity(coreX, coreY, coreZ);
        }
    	return tileEntityCore;
    }
    
    public void addToNBT(NBTTagCompound nbtTagCompound)
    {
    	nbtTagCompound.setInteger("coreX", coreX);
    	nbtTagCompound.setInteger("coreY", coreY);
    	nbtTagCompound.setInteger("coreZ", coreZ);
    }
    
    public void loadNBT(NBTTagCompound nbtTag)
    {
    	coreX = nbtTag.getInteger("coreX");
    	coreY = nbtTag.getInteger("coreY");
    	coreZ = nbtTag.getInteger("coreZ");
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
    
    @Override
    public int getSizeInventory()
    {
        return tileEntityCore.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return tileEntityCore.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        return tileEntityCore.decrStackSize(i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        return tileEntityCore.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        tileEntityCore.setInventorySlotContents(i, itemstack);
    }

    @Override
    public String getInvName()
    {
        return tileEntityCore.getInvName();
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return tileEntityCore.isInvNameLocalized();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return tileEntityCore.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false
                : entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) <= 64.0;
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
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public int[] getAccessibleSlotsFromSide(int var1)
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j)
    {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j)
    {
	    // TODO Auto-generated method stub
	    return false;
    }

}
