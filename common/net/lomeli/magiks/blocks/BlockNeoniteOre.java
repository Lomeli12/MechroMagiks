package net.lomeli.magiks.blocks;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNeoniteOre extends Block
{
    private String blockTexture;

    public BlockNeoniteOre(int par1, String texture)
    {
        super(par1, Material.rock);
        blockTexture = texture;
        this.setCreativeTab(Magiks.modTab);
        this.setStepSound(Block.soundStoneFootstep);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.MOD_ID  + ":"
                + blockTexture);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModItemsMagiks.neoniteGem.itemID;
    }
}
