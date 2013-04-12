package com.lomeli.magiks.core.helper;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockHelper
{
    public static Block getBlock(World world, int x, int y, int z)
    {
        return Block.blocksList[world.getBlockId(x, y, z)];
    }
}
