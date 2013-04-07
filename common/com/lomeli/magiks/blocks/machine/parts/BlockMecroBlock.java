package com.lomeli.magiks.blocks.machine.parts;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.lomeli.magiks.api.helpers.MultiblockHelper;
import com.lomeli.magiks.blocks.BlockMagiks;
import com.lomeli.magiks.blocks.ModBlocksMagiks;

public class BlockMecroBlock extends BlockMagiks
{

    public BlockMecroBlock(int par1, Material par2Material, String texture)
    {
        super(par1, par2Material, texture);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t)
    {
        if(MultiblockHelper.oneByTwo(this, ModBlocksMagiks.manceryGlass, world, x, y, z))
        {
            if(world.getBlockId(x, (y+2), z) == 0)
            {
                world.setBlockToAir(x, (y+1), z);
                world.setBlock(x, y, z, ModBlocksMagiks.kineticGenerator.blockID);
            }
        }
        /*if(world.blockExists(x, (y+1), z))
        {
            if(world.getBlockId(x, (y+1), z) == ModBlocksMagiks.manceryGlass.blockID)
            {
                if(world.getBlockId(x, (y+2), z) == 0)
                {
                    world.setBlockToAir(x, (y+1), z);
                    world.setBlock(x, y, z, ModBlocksMagiks.kineticGenerator.blockID);
                }
                else
                {
                    player.sendChatToPlayer("Must have air block above glass.");
                }
            }
            else{}
        }*/
        return true;
    }

}
