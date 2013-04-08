package com.lomeli.magiks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

public class ContainerKineticGenerator extends Container
{
    public ContainerKineticGenerator(InventoryPlayer inventoryPlayer, TileEntityKineticGenerator keneticGen)
    {
        this.addSlotToContainer(new Slot(keneticGen, 0, 56, 17));
        this.addSlotToContainer(new Slot(keneticGen, 0, 56, 62));
        this.addSlotToContainer(new Slot(keneticGen, 0, 80, 36));
        
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {

        ItemStack var3 = null;
        Slot var4 = (Slot) inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(var5, 3, 39, true))
                    return null;

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 1 && par2 != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(var5) != null) {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                        return null;
                }
                else if (TileEntityFurnace.isItemFuel(var5)) {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                        return null;
                }
                else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                        return null;
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                    return null;
            }
            else if (!this.mergeItemStack(var5, 3, 39, false))
                return null;

            if (var5.stackSize == 0) {
                var4.putStack((ItemStack) null);
            }
            else {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
                return null;

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

}
