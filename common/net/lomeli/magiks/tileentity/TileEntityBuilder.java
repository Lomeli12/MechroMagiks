package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.EnumMagiksType;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileEntityBuilder extends TileEntityMagiks 
	implements IInventory
{
	private ItemStack[] inventory;
	
	private EnumMagiksType type;
	
	public TileEntityBuilder()
	{
		inventory = new ItemStack[2];
		type = EnumMagiksType.MACHINE;
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
	public EnumMagiksType getType()
	{
		return type;
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

}
