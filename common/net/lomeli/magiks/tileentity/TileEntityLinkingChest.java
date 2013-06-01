package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityLinkingChest extends TileEntity implements
		IInventory
{
	private ItemStack[] inventory;
	
	public float lidAngle;

    public float prevLidAngle;
    
    public int numUsingPlayers;

    private int ticksSinceSync;
    
    private ForgeDirection orientation;
    
	public TileEntityLinkingChest()
	{
		inventory = new ItemStack[55];
		orientation = ForgeDirection.SOUTH;
	}
	
	public ForgeDirection getOrientation() {

        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {

        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {

        this.orientation = ForgeDirection.getOrientation(orientation);
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
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            }
            else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        if (inventory[slot] != null) {
            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            return itemStack;
        }
        else
            return null;
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
        return Strings.containerLinkingChest;
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
        return true;
    }

    @Override
    public void updateEntity() 
    {
        super.updateEntity();

        if (++ticksSinceSync % 20 * 4 == 0)
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, Block.enderChest.blockID, 1, numUsingPlayers);

        prevLidAngle = lidAngle;
        float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (numUsingPlayers > 0 && lidAngle == 0.0F) 
        {
            adjustedXCoord = xCoord + 0.5D;
            adjustedZCoord = zCoord + 0.5D;
            worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
            float var8 = lidAngle;

            if (numUsingPlayers > 0)
                lidAngle += angleIncrement;
            else 
                lidAngle -= angleIncrement;

            if (lidAngle > 1.0F) 
                lidAngle = 1.0F;

            if (lidAngle < 0.5F && var8 >= 0.5F) 
            {
                adjustedXCoord = xCoord + 0.5D;
                adjustedZCoord = zCoord + 0.5D;
                worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (lidAngle < 0.0F)
                lidAngle = 0.0F;
        }
    }
    
    @Override
    public boolean receiveClientEvent(int eventID, int numUsingPlayers) 
    {
        if (eventID == 1)
        {
            this.numUsingPlayers = numUsingPlayers;
            return true;
        }
        else
            return super.receiveClientEvent(eventID, numUsingPlayers);
    }

    @Override
    public void openChest() 
    {
        ++numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocksMagiks.linkingChest.blockID, 1, numUsingPlayers);
    }

    @Override
    public void closeChest() 
    {
        --numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocksMagiks.linkingChest.blockID, 1, numUsingPlayers);
    }

    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemstack)
    {
        return true;
    }
    
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        addToNBT(nbtTagCompound);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        loadNBT(nbtTagCompound);
    }
    
    public void addToNBT(NBTTagCompound nbtTagCompound)
    {
    	NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");
            if (slot >= 0 && slot < 54)
            {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }

        }
    }
    
    public void loadNBT(NBTTagCompound nbtTagCompound)
    {
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
