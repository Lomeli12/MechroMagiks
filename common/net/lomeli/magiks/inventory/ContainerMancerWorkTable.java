package net.lomeli.magiks.inventory;

import net.lomeli.lomlib.util.InventoryUtil;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.client.gui.slot.SlotMistCrafting;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.lomeli.magiks.api.crafting.BluePrintRecipeManager;
import net.lomeli.magiks.api.crafting.MachineRecipeManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;


public class ContainerMancerWorkTable extends Container
{
	private TileEntityMancerWorkTable tileEntity;
	private InventoryCrafting craft;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	
	public ContainerMancerWorkTable(InventoryPlayer inventoryPlayer, 
			TileEntityMancerWorkTable tileEntity)
	{
		this.tileEntity = tileEntity;
		this.worldObj = this.tileEntity.worldObj;
		this.craft = new InventoryCrafting(new ContainerDummy(), 4, 4);
	    this.posX = this.tileEntity.xCoord;
	    this.posY = this.tileEntity.yCoord;
	    this.posZ = this.tileEntity.zCoord;
	    
	    this.addSlotToContainer(new SlotMistCrafting(inventoryPlayer.player, 
	    		this.tileEntity, this.tileEntity, 16, 124, 34, this.tileEntity));
	    
	    InventoryUtil.createCraftMatrix((Container)this, this.tileEntity, 4, 4, 0, 12, 17);
	    
	    /*int slot = 0;
        for(int l = 0; l < 4; l++)
        {
        	for(int k1 = 0; k1 < 4; k1++)
        	{
        		this.addSlotToContainer(new Slot(this.tileEntity, slot, 12 + k1 * 18, 17 + l * 18));
        		slot++;
        	}
        }
        */
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
        
        
		for(int i = 0; i < 16; i++)
		{
			craft.setInventorySlotContents(i, this.tileEntity.getStackInSlot(i));
		}
		
		this.onCraftMatrixChanged(this.tileEntity);
	}
	
	public void onCraftMatrixChanged(IInventory iinventory)
	{
		this.tileEntity.setItemStack(16, 
			CraftingManager.getInstance().findMatchingRecipe(craft, this.tileEntity.worldObj));
		this.tileEntity.setMode(2);
		
		if(this.tileEntity.getStackInSlot(17) != null 
			&& this.tileEntity.getStackInSlot(17).getItem() == Item.paper)
		{
			this.tileEntity.setItemStack(16, BluePrintRecipeManager.getInstance(this.tileEntity)
				.findMatchingRecipe(craft, this.tileEntity.worldObj));
			this.tileEntity.setMode(0);
		}
		else if(this.tileEntity.getStackInSlot(17) != null 
				&& this.tileEntity.getStackInSlot(17).getItem() == ModItemsMagiks.electroicCircuit)
		{
			this.tileEntity.setItemStack(16, MachineRecipeManager.getInstance(this.tileEntity)
				.findMatchingRecipe(craft, this.tileEntity.worldObj));
			this.tileEntity.setMode(1);
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
				if(!mergeItemStack(itemstack1, 18, 46, true))
					return null;
			}
			else 
			if(par2 >= 18 && par2 < 45)
			{
				if(!mergeItemStack(itemstack1, 45, 46, false))
					return null;
			}
			else 
			if(par2 >= 45 && par2 < 54)
			{
				if(!mergeItemStack(itemstack1, 18, 37, false))
					return null;
				
			} else
	        if(!mergeItemStack(itemstack1, 18, 46, false))
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
	
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
	{
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}
}
