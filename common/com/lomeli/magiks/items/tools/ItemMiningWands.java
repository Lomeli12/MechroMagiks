package com.lomeli.magiks.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.lomeli.magiks.items.ItemGeneric;

public class ItemMiningWands extends ItemGeneric
{
    private int wandStrength;
    public ItemMiningWands(int par1, String Texture, boolean special, 
            int durability, int strength)
    {
        super(par1, Texture, special);
        this.setMaxDamage(durability);
        wandStrength = strength;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        /*if(world.blockExists(x, y, z) && Block.blocksList[id].isBlockNormalCube(world, x, y, z) &&
                Block.blocksList[id].blockID != 0)
        {
            world.destroyBlock(x, y, z, false);
            if(new ItemStack(Block.blocksList[id], 1) != null)
            {
                player.inventory.addItemStackToInventory(new ItemStack(Block.blocksList[id], 1));
            }
        } 
        /*switch(wandStrength)
        {
            case 0:
                if(blockHardness <= 3.0F)
                {
                    world.destroyBlock(x, y, z, false);
                    player.inventory.addItemStackToInventory(new ItemStack(Block.blocksList[id], 1));
                }
                break;
            case 1:
                if(blockHardness <= 3.0F)
                {
                    world.destroyBlock(x, y, z, false);
                    player.inventory.addItemStackToInventory(new ItemStack(Block.blocksList[id], 1));
                }
                break;
                
        }*/
        
        return itemStack;
    }
    
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        World world = player.worldObj;
        Vec3 look = player.getLookVec();
        
        int x = (int) (player.posX + look.xCoord * 4);
        int y = (int) (player.posY + look.yCoord * 4);
        int z = (int) (player.posZ + look.zCoord * 4);
        
        int id = world.getBlockId(x, y, z);
        
        float blockHardness = Block.blocksList[id]
                .getBlockHardness(world, x, y, z);
        
        player.sendChatToPlayer(String.valueOf(id));
        if((id != 0 || world.blockExists(x, y, z) || Block.blocksList[id].isAirBlock(world, x, y, z)))
            world.destroyBlock(x, y, z, true);
    }

}
