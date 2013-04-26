package net.lomeli.magiks.items.magik;

import net.lomeli.magiks.items.ItemGeneric;
import net.lomeli.magiks.tileentity.TileEntityMagiks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWands extends ItemGeneric
{
	//private static TileEntityMagiks connection;
    public ItemWands(int par1, String Texture, boolean special, int damage)
    {
        super(par1, Texture, special);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack)
    {
        return true;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack)
    {
        itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        return itemStack;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
    	int blockX = 0, blockY = 0,blockZ = 0;
    	if(player != null)
    	{
    		if(player.rayTrace(200, 1.0F) != null)
    		{
    			blockX = player.rayTrace(200, 1F).blockX;
    			blockY = player.rayTrace(200, 1F).blockY;
    			blockZ = player.rayTrace(200, 1F).blockZ;
    		}
    		if(world.blockExists(blockX, blockY, blockZ))
    		{}
    	}

		return itemStack;
    	
    }
    
    public static void setTile(TileEntityMagiks tile)
    {
    	//connection = tile;
    }
}
