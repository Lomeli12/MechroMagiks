package net.lomeli.magiks.client.gui.slot;

import net.lomeli.magiks.items.science.ItemMatter;
import net.lomeli.magiks.items.science.ItemUpgrades;
import net.lomeli.magiks.tileentity.TileEntityBuilder;
import net.lomeli.magiks.tileentity.TileEntityMancerWorkTable;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

public class RestrictedSlot extends Slot
{
	private TileEntity tile;
	public RestrictedSlot(IInventory par1iInventory, int par2, int par3, int par4, TileEntity tileEntity)
    {
	    super(par1iInventory, par2, par3, par4);
	    tile = tileEntity;
    }

	@Override
    public boolean isItemValid(ItemStack itemStack)
	{
		if(tile instanceof TileEntityMultiFurnaceCore)
		{
			if(itemStack.getItem() instanceof ItemUpgrades)
				return true;
			else
				return false;
		}
		else if(tile instanceof TileEntityBuilder)
		{
			if(itemStack.getItem() instanceof ItemMatter)
				return true;
			else
				return false;
		}
		else if(tile instanceof TileEntityMancerWorkTable)
		{
			for(ItemStack circut : OreDictionary.getOres("electronicCircuit"))
			{
				if(itemStack.isItemEqual(circut))
					return true;
				else
					return false;
			}
			if(itemStack.itemID == Item.paper.itemID)
				return true;
			else
				return false;
		}
		else 
			return false;
	}
}
