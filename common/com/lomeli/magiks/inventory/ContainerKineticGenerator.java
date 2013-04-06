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
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

}
