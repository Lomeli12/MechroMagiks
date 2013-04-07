package com.lomeli.magiks.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MultiblockHelper
{
    public static boolean oneByTwo(Block block, World world, int x, int y, int z)
    {
        if(world.blockExists(x, y, z))
        {
            if(world.getBlockId(x, y, z) == block.blockID && world.getBlockId(x, (y+1), z) == block.blockID)
                return true;
        }
        else
            return false;
        return false;
    }
    
    public static boolean oneByTwo(Block block1, Block block2, World world, int x, int y, int z)
    {
        if(world.blockExists(x, y, z))
        {
            if(world.getBlockId(x, y, z) == block1.blockID && world.getBlockId(x, (y+1), z) == block2.blockID)
                return true;
        }
        else
            return false;
        return false;
    }
    
    public static boolean twoByTwo(Block block, World world, int x, int y, int z)
    {
        if(world.blockExists(x, y, z))
        {
            if(world.getBlockId(x, y, z) == block.blockID && world.getBlockId((x + 1), y, z) == block.blockID &&
                    world.getBlockId(x, y, (z+1)) == block.blockID && world.getBlockId((x+1), y, (z + 1)) == block.blockID)
                return true;
            else if(world.getBlockId(x, y, z) == block.blockID && world.getBlockId((x + 1), y, z) == block.blockID &&
                    world.getBlockId(x, y, (z-1)) == block.blockID && world.getBlockId((x+1), y, (z-1)) == block.blockID)
                return true;
            else if(world.getBlockId(x, y, z) == block.blockID && world.getBlockId((x - 1), y, z) == block.blockID &&
                    world.getBlockId(x, y, (z+1)) == block.blockID && world.getBlockId((x-1), y, (z + 1)) == block.blockID)
                return true;
            else if(world.getBlockId(x, y, z) == block.blockID && world.getBlockId((x - 1), y, z) == block.blockID &&
                    world.getBlockId(x, y, (z-1)) == block.blockID && world.getBlockId((x-1), y, (z - 1)) == block.blockID)
                return true;
        }
        else
            return false;
        return false;
    }
}
