package net.lomeli.magiks.blocks;

import java.util.Random;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

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
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":"
        	+ blockTexture);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModItemsMagiks.neoniteGem.itemID;
    }
}
