package net.lomeli.magiks.api.helpers;

/**
 * This is a (very poorly written) helper that will allow you to make very 
 * simple multi-block structures.
 * 
 * All you have to do is have an if statement call upon these methods in your
 * block's onBlockActivated method, and have it do something when it's true
 * ex: replace it with a unique block, have it open a GUI if the correct blocks
 * are in the correct position.
 */

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MultiblockHelper
{

    public static boolean oneByTwo(Block block, World world, int x, int y, int z)
    {
        if (world.blockExists(x, y, z))
        {
            if (world.getBlockId(x, y, z) == block.blockID
                    && world.getBlockId(x, y + 1, z) == block.blockID)
                return true;
        } else
            return false;
        return false;
    }

    public static boolean oneByTwo(Block block1, Block block2, World world,
            int x, int y, int z)
    {
        if (world.blockExists(x, y, z))
        {
            if (world.getBlockId(x, y, z) == block1.blockID
                    && world.getBlockId(x, y + 1, z) == block2.blockID)
                return true;
        } else
            return false;
        return false;
    }

}
