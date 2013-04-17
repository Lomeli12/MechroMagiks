package com.lomeli.magiks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

public class ContainerKineticGenerator extends Container
{
    private TileEntityKineticGenerator kinGen;
    public ContainerKineticGenerator(InventoryPlayer inventoryPlayer,
            TileEntityKineticGenerator kineticGen)
    {
        this.kinGen = kineticGen;
        
        this.addSlotToContainer(new Slot(kineticGen, 0, 56, 17)).slotNumber = 0;
        this.addSlotToContainer(new Slot(kineticGen, 1, 56, 53)).slotNumber = 1;

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer,
                        inventoryColumnIndex + inventoryRowIndex * 9 + 9,
                        8 + inventoryColumnIndex * 18,
                        84 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer,
                    actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 0)
            {
                if (!mergeItemStack(itemstack1, 1, 37, true))
                    return null;
            } else if (i >= 1 && i < 28)
            {
                if (!mergeItemStack(itemstack1, 28, 37, false))
                    return null;
            } else if (i >= 28 && i < 37)
            {
                if (!mergeItemStack(itemstack1, 1, 27, false))
                    return null;
            } else if (!mergeItemStack(itemstack1, 1, 37, false))
                return null;
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(player, itemstack1);
            } else
                return null;
        }

        return itemstack;
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }
    
    @Override
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.kinGen.heatLevel = par2;
        }

        if (par1 == 1)
        {
            this.kinGen.mistLevel = par2;
        }

    }

}
