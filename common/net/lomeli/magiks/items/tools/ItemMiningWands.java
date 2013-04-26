package net.lomeli.magiks.items.tools;

import net.lomeli.magiks.items.ItemGeneric;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMiningWands extends ItemGeneric
{
	int tick = 0;
    // private int wandStrength;
    public ItemMiningWands(int par1, String Texture, boolean special,
            int durability, int strength)
    {
        super(par1, Texture, special);
        this.setMaxDamage(durability);
        // wandStrength = strength;
    }

    @SuppressWarnings("unused")
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
    	int x = 0, y = 0,z = 0;
    	if(player != null)
    	{
    		if(player.rayTrace(200, 1.0F) != null)
    		{
    			x = player.rayTrace(200, 1F).blockX;
    			y = player.rayTrace(200, 1F).blockY;
    			z = player.rayTrace(200, 1F).blockZ;
    		}
    	}

        int id = world.getBlockId(x, y, z);

        player.sendChatToPlayer(String.valueOf(id));
        if (id != 0 || world.blockExists(x, y, z)
                || !Block.blocksList[id].isAirBlock(world, x, y, z))
        {
        	if(tick == 0)
        	{
        		world.destroyBlock(x, y, z, false);
        		world.destroyBlock(x, y, z, false);
        		try
        		{
        			ItemStack block = new ItemStack(Block.blocksList[id]);
        			if (block != null)
        			{
        				player.inventory.addItemStackToInventory(block);
        			} else
        			{	
        				player.inventory.addItemStackToInventory(new ItemStack(
                            Block.dirt));
        			}
        		} catch (Exception e)
        		{
        		}
        		tick++;
        	}else if(tick >= 2)
        	{
        		tick = 0;
        	}
        }

        return itemStack;
    }

}
