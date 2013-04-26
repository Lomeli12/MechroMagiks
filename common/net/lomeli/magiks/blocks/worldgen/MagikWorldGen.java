package net.lomeli.magiks.blocks.worldgen;

import java.util.Random;

import net.lomeli.magiks.blocks.ModBlocksMagiks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;

public class MagikWorldGen implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case 1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case -1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
        if(world.getBiomeGenForCoords(chunkX, chunkZ) == BiomeGenBase.hell)
        {
        	this.generateNether(world, random, chunkX, chunkZ);
        }
    }

    private void generateNether(World world, Random random, int chunkX,
            int chunkZ)
    {
        new WorldGenMinable(ModBlocksMagiks.netherIgnious.blockID, 30, Block.netherrack.blockID);
    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ)
    {
    }

    private void generateSurface(World world, Random random, int chunkX,
            int chunkZ)
    {
        int xCoord = chunkX;
        int zCoord = chunkZ;
        new WorldGenMinable(ModBlocksMagiks.neoniteOre.blockID, 5).generate(
                world, random, (xCoord + random.nextInt(16)), random.nextInt(50), (zCoord + random.nextInt(16)));
        new WorldGenMinable(ModBlocksMagiks.stamaticOre.blockID, 10).generate(
                world, random, (xCoord + random.nextInt(16)), random.nextInt(65), (zCoord + random.nextInt(16)));
        new WorldGenMinable(ModBlocksMagiks.igniousOre.blockID, 4).generate(
                world, random, (xCoord + random.nextInt(16)), random.nextInt(35), (zCoord + random.nextInt(16)));
    }

}
