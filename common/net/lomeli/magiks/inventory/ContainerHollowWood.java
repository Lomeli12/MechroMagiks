package net.lomeli.magiks.inventory;

import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHollowWood extends Container
{
	private TileEntityHollowWood tileEntity;
	
	public ContainerHollowWood(InventoryPlayer playerInventory, TileEntityHollowWood tileEntityHW)
	{
		this.tileEntity = tileEntityHW;
		
		this.addSlotToContainer(new Slot(this.tileEntity , 0, 26, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 1, 44, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 2, 62, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 3, 80, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 4, 98, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 5, 116, 17));
		this.addSlotToContainer(new Slot(this.tileEntity , 6, 134, 17));
		
		this.addSlotToContainer(new Slot(this.tileEntity , 7, 26, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 8, 44, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 9, 62, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 10, 80, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 11, 98, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 12, 116, 35));
		this.addSlotToContainer(new Slot(this.tileEntity , 13, 134, 35));
		
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(playerInventory,
                        inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                        8 + inventoryColumnIndex * 18,
                        68 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(playerInventory,
                    actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 126));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) 
	{
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 2 * 7)
            {
                if (!this.mergeItemStack(itemstack1, 2 * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 2 * 7, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

}
