package com.lomeli.magiks.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockKineticGenerator extends Block
{

    public BlockKineticGenerator(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.67F, 1.0F);
        this.setBlockBoundsForItemRender();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Strings.modID + ":kineticgentexture");
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIDs.kineticGID;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityKineticGenerator();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t)
    {
        if (world.isBlockIndirectlyGettingPowered(x, y, z))
        {
            world.spawnParticle("largeexplode", (x+0.5), (y+1), (z+0.5), 1D, 0, 0);
        }
        
        if (player.inventory.currentItem == Block.tnt.blockID)
        {
            player.inventory.consumeInventoryItem(Block.tnt.blockID);
        }
        return true;
    }
}
