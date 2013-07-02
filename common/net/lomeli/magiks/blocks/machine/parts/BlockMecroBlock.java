package net.lomeli.magiks.blocks.machine.parts;

import net.lomeli.magiks.api.helpers.MultiblockHelper;
import net.lomeli.magiks.blocks.BlockMagiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.items.ModItemsMagiks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


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
        if (player != null)
        {
            if (MultiblockHelper.oneByTwo(this, ModBlocksMagiks.manceryGlass,
                    world, x, y, z))
            {
                if (world.getBlockId(x, y + 2, z) == 0)
                {
                    if (player.inventory
                            .hasItem(ModItemsMagiks.darkMatter.itemID))
                    {
                        world.setBlockToAir(x, y + 1, z);
                        world.setBlock(x, y, z,
                                ModBlocksMagiks.kineticGenerator.blockID);
                        player.inventory
                                .consumeInventoryItem(ModItemsMagiks.darkMatter.itemID);
                    }
                }
            }
        }
        return true;
    }

}
