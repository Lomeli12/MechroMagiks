package net.lomeli.magiks.inventory;

import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLinkingChest extends Container
{
	private TileEntityLinkingChest tileEntity;
	
	public ContainerLinkingChest(InventoryPlayer playerInventory, TileEntityLinkingChest tileEntity)
	{
		this.tileEntity = tileEntity;
		
		int c = 0;
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(this.tileEntity, c, 8 + j * 18, 18 + i * 18));
				c++;
			}
		}
		
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(playerInventory,
                        inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                        8 + inventoryColumnIndex * 18,
                        140 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(playerInventory,
                    actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 198));
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