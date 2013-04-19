package com.lomeli.magiks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.lomeli.magiks.api.cafting.MagikCraftingManager;
import com.lomeli.magiks.client.gui.slot.SlotMistCrafting;

public class ContainerMistWorkBench extends Container
{
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;

    // private int posX;
    // private int posY;
    // private int posZ;

    public ContainerMistWorkBench(InventoryPlayer inventoryPlayer, World world,
            int par3, int par4, int par5)
    {
        worldObj = world;
        // this.posX = par3;
        // this.posY = par4;
        // this.posZ = par5;
        this.addSlotToContainer(new SlotMistCrafting(inventoryPlayer.player,
                craftMatrix, craftResult, 0, 124, 35));
        // int var6;
        // int var7;

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

        this.onCraftMatrixChanged(craftMatrix);
    }

    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        craftResult.setInventorySlotContents(0, MagikCraftingManager
                .getInstance().findMatchingRecipe(craftMatrix, worldObj));
    }

    @Override
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);

        if (!worldObj.isRemote)
        {
            for (int var2 = 0; var2 < 9; ++var2)
            {
                ItemStack var3 = craftMatrix.getStackInSlotOnClosing(var2);
                if (var3 != null)
                {
                    par1EntityPlayer.dropPlayerItem(var3);
                }
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot) inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 == 0)
            {
                if (!this.mergeItemStack(var5, 10, 46, true))
                    return null;
                var4.onSlotChange(var5, var3);
            } else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(var5, 37, 46, false))
                    return null;
            } else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(var5, 10, 37, false))
                    return null;
            } else if (!this.mergeItemStack(var5, 10, 46, false))
                return null;

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack) null);
            } else
            {
                var4.onSlotChanged();
            }
            if (var5.stackSize == var3.stackSize)
                return null;
            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }
        return var3;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

}
