package net.lomeli.magiks.items.magik;

import java.util.List;

import net.lomeli.lomlib.item.ItemUtil;
import net.lomeli.lomlib.util.NBTUtil;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.items.ItemGeneric;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmulets extends ItemGeneric
{
	private int tick = 0;
	
    public ItemAmulets(int par1, String Texture, boolean special, int magik)
    {
        super(par1, Texture, special);
        this.setMaxDamage(magik);
        this.setMaxStackSize(1);
    }

    private void setOwner(ItemStack itemStack, EntityPlayer player)
    {
        NBTUtil.setString(itemStack, "owner", player.username);
        NBTUtil.setBoolean(itemStack, "ownerset", true);
    }

    private void removeOwner(ItemStack itemStack)
    {
        NBTUtil.setString(itemStack, "owner", "");
        NBTUtil.setBoolean(itemStack, "ownerset", false);
    }

    public String getOwner(ItemStack itemStack)
    {
        return NBTUtil.getString(itemStack, "owner");
    }

    public boolean isOwnerSet(ItemStack itemStack)
    {
        return NBTUtil.getBoolean(itemStack, "ownerset");
    }
    
    public boolean isPlayerOwner(EntityPlayer player, ItemStack itemStack)
    {
    	if (player.username.equals(this.getOwner(itemStack)))
    		return true;
    	else
    		return false;
    }
    
    public void rechargeItem(EntityPlayer player, ItemStack item)
    {
    	
    	for(ItemStack mistItem : player.inventory.mainInventory)
    	{
    		if(mistItem != null)
    		{
    			for(ItemStack check : MagiksArrays.rechargeableItems)
    			{
    				if(mistItem.itemID == check.itemID)
    				{	
    					ItemUtil.getSlotContainingItem(mistItem.itemID, player.inventory.mainInventory);
    					
    					if(item.getItemDamage() < (item.getMaxDamage()-1))
    					{
    						item.damageItem(item.getItemDamage(), player);
    						mistItem.setItemDamage(0);
    					}
    				}
    			}
    		}
    	}
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, 
    		World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (isOwnerSet(itemStack))
        {	
            if (isPlayerOwner(player, itemStack))
            {
            	if(player.isSneaking())
            		removeOwner(itemStack);
            	else
            	{
            		tick++;
            		if(tick >= 2)
            			rechargeItem(player, itemStack);
            	}
            }
            else
            {
            	player.sendChatToPlayer("You're not allowd to use this!");
            	player.setFire(10);
            }
        } 
        else
        {
        	if(player.isSneaking())
        	{
        		tick++;
        		if(tick >= 2)
        			setOwner(itemStack, player);
        	}
        }
        
        return false;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        infoList.add("Mist Level: "
                + (itemStack.getMaxDamage() - itemStack.getItemDamage()) + "/"
                + itemStack.getMaxDamage());
        if (isOwnerSet(itemStack))
        {
            infoList.add("Owner: " + this.getOwner(itemStack));
        }

    }
}
