package net.lomeli.magiks.inventory;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.client.gui.slot.SlotMistCrafting;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import net.lomeli.magiks.api.cafting.BluePrintRecipeManager;
import net.lomeli.magiks.api.cafting.MachineRecipeManager;

public class ContainerMancerWorkTable extends Container
{
	private TileEntityMancerWorkTable tileEntity;
	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	
	public ContainerMancerWorkTable(InventoryPlayer inventoryPlayer, 
			TileEntityMancerWorkTable tileEntity)
	{
		craftMatrix = new InventoryCrafting(this, 4, 4);
		craftResult = new InventoryCraftResult();
		this.tileEntity = tileEntity;
		this.worldObj = this.tileEntity.worldObj;
	    this.posX = this.tileEntity.xCoord;
	    this.posY = this.tileEntity.yCoord;
	    this.posZ = this.tileEntity.zCoord;
	    
	    this.addSlotToContainer(new SlotMistCrafting(inventoryPlayer.player, 
	    		craftMatrix, craftResult, 0, 124, 34, this.tileEntity));
	    
        for(int l = 0; l < 4; l++)
        {
        	for(int k1 = 0; k1 < 4; k1++)
        	{
        		this.addSlotToContainer(new Slot(craftMatrix, k1 + l * 4, 12 + k1 * 18, 17 + l * 18));
        	}
        }
        
        this.addSlotToContainer(new Slot(this.tileEntity, 17, 124, 71));
        
        for(int i1 = 0; i1 < 3; i1++)
        {
        	for(int l1 = 0; l1 < 9; l1++)
        	{
        		this.addSlotToContainer(new Slot(inventoryPlayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 108 + i1 * 18));
        	}
        }

        for(int j1 = 0; j1 < 9; j1++)
        {
        	this.addSlotToContainer(new Slot(inventoryPlayer, j1, 8 + j1 * 18, 166));
        }
        
        this.onCraftMatrixChanged(craftMatrix);
	}
	
	public void onCraftMatrixChanged(IInventory iinventory)
	{
		if(this.tileEntity.getStackInSlot(17) != null 
			&& this.tileEntity.getStackInSlot(17).getItem() == Item.paper)
		{
			craftResult.setInventorySlotContents(0, BluePrintRecipeManager.getInstance(this.tileEntity)
				.findMatchingRecipe(craftMatrix, worldObj));
			this.tileEntity.setMode(0);
		}
		else if(this.tileEntity.getStackInSlot(17) != null 
				&& this.tileEntity.getStackInSlot(17).getItem() == ModItemsMagiks.electroicCircuit)
		{
			craftResult.setInventorySlotContents(0, MachineRecipeManager.getInstance(this.tileEntity)
					.findMatchingRecipe(craftMatrix, worldObj));
				this.tileEntity.setMode(1);
		}
		else
		{
			craftResult.setInventorySlotContents(0, CraftingManager.getInstance()
				.findMatchingRecipe(craftMatrix, worldObj));
			this.tileEntity.setMode(2);
		}
    }

	public void onCraftGuiClosed(EntityPlayer entityplayer)
	{
		super.onCraftGuiClosed(entityplayer);
		if(worldObj.isRemote)
			return;
	    for(int i = 0; i < 25; i++)
	    {
	    	ItemStack itemstack = craftMatrix.getStackInSlot(i);
	    	if(itemstack != null)
	    		entityplayer.dropPlayerItem(itemstack);
	    }
	}
	
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		if(worldObj.getBlockId(posX, posY, posZ) != ModBlocksMagiks.mancerWorkTable.blockID)
			return false;
		else
			return entityplayer.getDistanceSq((double)posX + 0.5D, (double)posY + 0.5D, 
					(double)posZ + 0.5D) <= 64D;
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)inventorySlots.get(par2);
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if(par2 == 0)
			{
				if(!mergeItemStack(itemstack1, 10, 46, true))
					return null;
			}
			else 
			if(par2 >= 10 && par2 < 37)
			{
				if(!mergeItemStack(itemstack1, 37, 46, false))
					return null;
			}
			else 
			if(par2 >= 37 && par2 < 46)
			{
				if(!mergeItemStack(itemstack1, 10, 37, false))
					return null;
				
			} else
	        if(!mergeItemStack(itemstack1, 10, 46, false))
	        	return null;
			if(itemstack1.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
			if(itemstack1.stackSize != itemstack.stackSize)
				slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
			else
				return null;
			
		}
		return itemstack;
	}

}
