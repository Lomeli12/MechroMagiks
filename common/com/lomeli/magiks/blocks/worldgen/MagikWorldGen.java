package com.lomeli.magiks.blocks.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.lomeli.magiks.blocks.ModBlocksMagiks;

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
    }

    private void generateNether(World world, Random random, int chunkX,
            int chunkZ)
    {
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
                world, random, xCoord, random.nextInt(50), zCoord);
        new WorldGenMinable(ModBlocksMagiks.stamaticOre.blockID, 10).generate(
                world, random, xCoord, random.nextInt(65), zCoord);
        new WorldGenMinable(ModBlocksMagiks.igniousOre.blockID, 4).generate(
                world, random, xCoord, random.nextInt(35), zCoord);
    }

}
