package net.lomeli.magiks.core.handler;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.items.magik.ItemWands;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;


import cpw.mods.fml.common.ICraftingHandler;

public class MagiksCraftingHandler implements ICraftingHandler
{
    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory inv)
    {
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            if (inv.getStackInSlot(i) != null)
            {
                ItemStack j = inv.getStackInSlot(i);
                for (ItemStack itemD : MagiksArrays.damageOnCraft)
                {
                    if (j.getItem() != null && j.getItem() == itemD.getItem())
                    {
                    	if(item.getItem() instanceof ItemWands){}
                    	else if(item.getItem() == ModItemsMagiks.pirasVarinha){}
                    	else if(item.getItem() == ModItemsMagiks.grindingPick){}
                    	else
                    	{
                    		int damage = 1;
                    		if(item.stackSize == 2)
                    			damage = 2;
                    		
                    		ItemStack k = new ItemStack(itemD.getItem(), 2,
                                j.getItemDamage() + damage);
                    		
                    		if(k.getItemDamage() >= k.getMaxDamage())
                    			k.damageItem(1, player);
                    		else
                    			inv.setInventorySlotContents(i, k);
                    	}
                    }
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {
    }

}
