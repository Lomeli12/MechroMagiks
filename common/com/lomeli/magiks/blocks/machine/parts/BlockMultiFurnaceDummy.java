package com.lomeli.magiks.blocks.machine.parts;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;
import com.lomeli.magiks.tileentity.TileEntityMultiFurnaceDummy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMultiFurnaceDummy extends BlockContainer
{
    public BlockMultiFurnaceDummy(int blockId)
    {
        super(blockId, Material.rock);

        setUnlocalizedName("blockMultiFurnaceDummy");
        setStepSound(Block.soundStoneFootstep);
        setHardness(3.5f);
        setCreativeTab(Magiks.modTab);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModBlocksMagiks.burningStone.blockID;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityMultiFurnaceDummy();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.modID.toLowerCase() + ":burningstone");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        TileEntityMultiFurnaceDummy dummy = (TileEntityMultiFurnaceDummy) world
                .getBlockTileEntity(x, y, z);

        if (dummy != null && dummy.getCore() != null)
        {
            dummy.getCore().invalidateMultiblock();
        }

        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;

        TileEntityMultiFurnaceDummy dummy = (TileEntityMultiFurnaceDummy) world
                .getBlockTileEntity(x, y, z);

        if (dummy != null && dummy.getCore() != null)
        {
            TileEntityMultiFurnaceCore core = dummy.getCore();
            return core.getBlockType().onBlockActivated(world, core.xCoord,
                    core.yCoord, core.zCoord, player, par6, par7, par8, par9);
        }

        return true;
    }
}
