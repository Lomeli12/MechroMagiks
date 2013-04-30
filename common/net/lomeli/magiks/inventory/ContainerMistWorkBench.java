package net.lomeli.magiks.inventory;

import net.lomeli.magiks.api.cafting.MagiksCraftingManager;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.client.gui.slot.SlotMistCrafting;
import net.lomeli.magiks.tileentity.TileEntityMistWorkBench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMistWorkBench extends Container
{
	private TileEntityMistWorkBench tileEntity;
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 9, 3);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerMistWorkBench(InventoryPlayer inventoryPlayer, TileEntityMistWorkBench tileEntity)
	{
		this.tileEntity = tileEntity;
	    this.worldObj = this.tileEntity.worldObj;
	    this.posX = this.tileEntity.xCoord;
	    this.posY = this.tileEntity.yCoord;
	    this.posZ = this.tileEntity.zCoord;
	    
        this.addSlotToContainer(new SlotMistCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        
        int l;
        int i1;

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
            }
        }
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
	     this.onCraftMatrixChanged(this.craftMatrix);
	}

    public void onCraftMatrixChanged(IInventory par1IInventory)
    {
        this.craftResult.setInventorySlotContents(0, MagiksCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }

	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
	{
		super.onCraftGuiClosed(par1EntityPlayer);

		if (!this.worldObj.isRemote)
		{
			for (int var2 = 0; var2 < 9; ++var2)
			{
				ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(var2);
				if (var3 != null)
				{
					par1EntityPlayer.dropPlayerItem(var3);
				}
			}
		}
	}

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != ModBlocksMagiks.mistCrafter.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

    public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot)
    {
        return par2Slot.inventory != this.craftResult && super.func_94530_a(par1ItemStack, par2Slot);
    }
}
