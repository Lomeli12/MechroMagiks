package net.lomeli.magiks.items.magik;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
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
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	int regBlock = world.getBlockId(x, y, z);
    	System.out.println(regBlock);
    	boolean result = false;
    	
    	if(regBlock == 5)
    	{
    		System.out.println("wood");
    		if(!world.isRemote)
    		{
    			System.out.println("hello");
    			world.setBlock(x, y, z, ModBlocksMagiks.hollowWood.blockID);
    			world.notifyBlockOfNeighborChange(x, y, z, world.getBlockId(x, y, z));
    			world.markBlockForUpdate(x, y, z);
    			world.addBlockEvent(x, y, z, ModBlocksMagiks.hollowWood.blockID, 1, 1);
    			this.setItemDamageForStack(itemstack, (itemstack.getItemDamage() + 10));
    			result = true;
    		}
    	}
    	return result;
    }
    
    public static void setTile(TileEntityMagiks tile)
    {
    	//connection = tile;
    }
}
