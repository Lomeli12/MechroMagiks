package net.lomeli.magiks.inventory;

import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import net.lomeli.magiks.client.gui.slot.RestrictedSlot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerMultiFurnace extends Container
{
    private TileEntityMultiFurnaceCore tileEntity;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerMultiFurnace(InventoryPlayer playerInventory,
            TileEntityMultiFurnaceCore tileEntity)
    {
        this.tileEntity = tileEntity;

        // Input
        addSlotToContainer(new Slot(tileEntity, 0, 56, 17));

        // Fuel
        addSlotToContainer(new Slot(tileEntity, 1, 56, 53));

        // Output
        addSlotToContainer(new SlotFurnace(playerInventory.player, tileEntity,
                2, 116, 35));

        // Upgrades
        addSlotToContainer(new RestrictedSlot(tileEntity, 3, 150, 15, tileEntity));
        addSlotToContainer(new RestrictedSlot(tileEntity, 4, 150, 35, tileEntity));
        addSlotToContainer(new RestrictedSlot(tileEntity, 5, 150, 55, tileEntity));

        bindPlayerInventory(playerInventory);
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting
                .sendProgressBarUpdate(this, 0, tileEntity.furnaceCookTime);
        par1ICrafting
                .sendProgressBarUpdate(this, 1, tileEntity.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2,
                tileEntity.currentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) crafters.get(i);

            if (lastCookTime != tileEntity.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0,
                        tileEntity.furnaceCookTime);
            }

            if (lastBurnTime != tileEntity.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1,
                        tileEntity.furnaceBurnTime);
            }

            if (lastItemBurnTime != tileEntity.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2,
                        tileEntity.currentItemBurnTime);
            }
        }

        lastCookTime = tileEntity.furnaceCookTime;
        lastBurnTime = tileEntity.furnaceBurnTime;
        lastItemBurnTime = tileEntity.currentItemBurnTime;
    }

    @Override
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            tileEntity.furnaceCookTime = par2;
        }

        if (par1 == 1)
        {
            tileEntity.furnaceBurnTime = par2;
        }

        if (par1 == 2)
        {
            tileEntity.currentItemBurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return tileEntity.isUseableByPlayer(entityPlayer);
    }

    private void bindPlayerInventory(InventoryPlayer playerInventory)
    {
        // Inventory
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 9; x++)
            {
                addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9,
                        8 + x * 18, 84 + y * 18));
            }
        }

        // Action Bar
        for (int x = 0; x < 9; x++)
        {
            addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            // Merges the item into the player inventory
            if (slot < 3)
            {
                if (!this.mergeItemStack(stackInSlot, 3, 39, true))
                    return null;
            } else if (!this.mergeItemStack(stackInSlot, 0, 3, false))
                return null;

            if (stackInSlot.stackSize == 0)
            {
                slotObject.putStack(null);
            } else
            {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize)
                return null;

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        return stack;
    }
}
