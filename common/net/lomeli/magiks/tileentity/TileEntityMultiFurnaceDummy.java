package net.lomeli.magiks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMultiFurnaceDummy extends TileEntity implements
        ISidedInventory
{
    TileEntityMultiFurnaceCore tileEntityCore;
    int coreX;
    int coreY;
    int coreZ;

    public TileEntityMultiFurnaceDummy()
    {
    }

    public void setCore(TileEntityMultiFurnaceCore core)
    {
        coreX = core.xCoord;
        coreY = core.yCoord;
        coreZ = core.zCoord;
        tileEntityCore = core;
    }

    public TileEntityMultiFurnaceCore getCore()
    {
        if (tileEntityCore == null)
        {
            tileEntityCore = (TileEntityMultiFurnaceCore) worldObj
                    .getBlockTileEntity(coreX, coreY, coreZ);
        }

        return tileEntityCore;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);

        loadNBT(tagCompound);
    }
    
    public void loadNBT(NBTTagCompound tagCompound)
    {
    	coreX = tagCompound.getInteger("CoreX");
        coreY = tagCompound.getInteger("CoreY");
        coreZ = tagCompound.getInteger("CoreZ");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);

        addToNBT(tagCompound);
    }
    
    public void addToNBT(NBTTagCompound tagCompound)
    {
    	tagCompound.setInteger("CoreX", coreX);
        tagCompound.setInteger("CoreY", coreY);
        tagCompound.setInteger("CoreZ", coreZ);
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
    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
        return tileEntityCore.isStackValidForSlot(i, itemstack);
    }

    @SuppressWarnings("static-access")
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		return var1 == 0 ? tileEntityCore.sidedSlotBottom : var1 == 1 ? tileEntityCore.sidedSlotTop
                : tileEntityCore.sidedSlotSides;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if(side == 1)
			return this.isStackValidForSlot(0, itemstack);
		if(side == 0)
			return this.isStackValidForSlot(1, itemstack);
		else
			return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		if(side == this.blockMetadata)
			return true;
		else
			return false;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		
	}
}
