package net.lomeli.magiks.blocks.worldgen;

import java.util.Random;

import net.lomeli.lomlib.worldgen.WorldGenNether;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.ModInts;

import net.minecraft.world.World;
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
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateNether(World world, Random random, int chunkX,
            int chunkZ)
    {
    	int xCoord = chunkX + random.nextInt(16);
        int zCoord = chunkZ + random.nextInt(16);
        new WorldGenNether(ModBlocksMagiks.netherIgnious.blockID, ModInts.nIgniousGen)
        	.generate(world, random, xCoord, random.nextInt(126), zCoord);
    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ)
    {
    }

    private void generateSurface(World world, Random random, int chunkX,
            int chunkZ)
    {
        int xCoord = chunkX + random.nextInt(16);
        int zCoord = chunkZ + random.nextInt(16);
        new WorldGenMinable(ModBlocksMagiks.neoniteOre.blockID, ModInts.neoniteGen).generate(
                world, random, xCoord, random.nextInt(50), zCoord);
        new WorldGenMinable(ModBlocksMagiks.stamaticOre.blockID, ModInts.stamaticGen).generate(
                world, random, xCoord, random.nextInt(65), zCoord);
        new WorldGenMinable(ModBlocksMagiks.igniousOre.blockID, ModInts.igniousGen).generate(
                world, random, xCoord, random.nextInt(35), zCoord);
        new WorldGenMinable(ModBlocksMagiks.copperOre.blockID, ModInts.copperGen).generate(
        		world, random, xCoord, random.nextInt(75), zCoord);
        new WorldGenMinable(ModBlocksMagiks.tinOre.blockID, ModInts.tinGen).generate(
        		world, random, xCoord, random.nextInt(55), zCoord);
        new WorldGenMinable(ModBlocksMagiks.silverOre.blockID, ModInts.silverGen).generate(
        		world, random, xCoord, random.nextInt(35), zCoord);
        
    }

}
