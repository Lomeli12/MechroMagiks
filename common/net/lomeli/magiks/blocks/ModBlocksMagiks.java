package net.lomeli.magiks.blocks;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.machine.BlockKineticGenerator;
import net.lomeli.magiks.blocks.machine.BlockMultiFurnaceCore;
import net.lomeli.magiks.blocks.machine.BlockSolarMistCollector;
import net.lomeli.magiks.blocks.machine.parts.BlockMecroBlock;
import net.lomeli.magiks.blocks.machine.parts.BlockMecroGlass;
import net.lomeli.magiks.blocks.machine.parts.BlockMultiFurnaceDummy;
import net.lomeli.magiks.lib.Ints;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocksMagiks
{
    public static Block neoniteOre, neoniteBlock, kineticGenerator,
            manceryBlock, manceryGlass, stamaticOre, igniousOre,
            solarMistCollector, mistCrafter, dupeFurnace, burningStone,
            manceryBrick;

    public static void registerBlocks()
    {
        neoniteOre = new BlockNeoniteOre(Ints.enchantedOreID, "neoniteOre")
                .setUnlocalizedName("neoniteOre").setLightValue(0.5F)
                .setResistance(50F).setHardness(3.0F);
        neoniteBlock = new BlockMagiks(Ints.enchantedBlockID, Material.rock,
                "neoniteBlock").setUnlocalizedName("neoniteBlock")
                .setLightValue(1.5F).setResistance(100F).setHardness(3.0F)
                .setLightOpacity(0).setStepSound(Block.soundStoneFootstep);
        kineticGenerator = new BlockKineticGenerator(Ints.kineticGenID,
                Material.rock).setUnlocalizedName("kineticgen")
                .setResistance(1000F).setHardness(3F);
        manceryBlock = new BlockMecroBlock(Ints.manceryBlockID, Material.rock,
                "manceryblock").setUnlocalizedName("manceryblock")
                .setHardness(0.5F).setResistance(5000F);
        manceryGlass = new BlockMecroGlass(Ints.manceryGlassID, Material.glass,
                "manceryglass").setUnlocalizedName("manceryglass")
                .setHardness(0.5F).setResistance(5000F);
        stamaticOre = new BlockMagiks(Ints.stamaticOreID, Material.rock,
                "orestamatic").setUnlocalizedName("orestamatic")
                .setHardness(3F).setResistance(5F);
        igniousOre = new BlockMagiks(Ints.igniousOreID, Material.rock,
                "oreignious").setUnlocalizedName("oreignious").setHardness(3F)
                .setResistance(5F);
        solarMistCollector = new BlockSolarMistCollector(Ints.solarGenID,
                Material.rock, "solarmistcollector")
                .setUnlocalizedName("solarmistcollector").setHardness(3F)
                .setResistance(100F);
        mistCrafter = new BlockMistCrafter(Ints.mistCrafterID, Material.anvil)
                .setUnlocalizedName("MistCrafter").setResistance(100F);
        manceryBrick = new BlockMagiks(Ints.manceryBrickID, Material.rock,
                "mancerybricks").setUnlocalizedName("mancerybricks")
                .setHardness(0.5F).setResistance(5000F);

        dupeFurnace = new BlockMultiFurnaceCore(Ints.dupeFurnaceID);
        burningStone = new BlockMultiFurnaceDummy(Ints.dupeDummyID);

        GameRegistry.registerBlock(neoniteOre, "Neonite Ore");
        GameRegistry.registerBlock(neoniteBlock, "Neonite Block");
        GameRegistry.registerBlock(kineticGenerator, "Kinetic Generator");
        GameRegistry.registerBlock(manceryBlock, "Mancery Stone");
        GameRegistry.registerBlock(manceryGlass, "Mancery Glass");
        GameRegistry.registerBlock(stamaticOre, "Stamatic Ore");
        GameRegistry.registerBlock(igniousOre, "Ignious Ore");
        GameRegistry.registerBlock(solarMistCollector, "Solar Mist Collector");
        GameRegistry.registerBlock(mistCrafter, "Mist Crafting Table");
        GameRegistry.registerBlock(dupeFurnace, "Piras Oven Core");
        GameRegistry.registerBlock(burningStone, "Burning Stone");
        GameRegistry.registerBlock(manceryBrick, "Mancery Brick");

        LanguageRegistry.addName(neoniteOre, "Neonite Ore");
        LanguageRegistry.addName(neoniteBlock, "Neonite Block");
        LanguageRegistry.addName(kineticGenerator, "Kinetic Generator");
        LanguageRegistry.addName(manceryBlock, "Mancery Stone");
        LanguageRegistry.addName(manceryGlass, "Mancery Glass");
        LanguageRegistry.addName(stamaticOre, "Statmatic Ore");
        LanguageRegistry.addName(igniousOre, "Ignious Ore");
        LanguageRegistry.addName(solarMistCollector, "Solar Mist Collector");
        LanguageRegistry.addName(mistCrafter, "Mist Crafting Table");
        LanguageRegistry.addName(dupeFurnace, "Piras Oven Core");
        LanguageRegistry.addName(burningStone, "Burning Stone");
        LanguageRegistry.addName(manceryBrick, "Mancery Brick");

        MinecraftForge.setBlockHarvestLevel(neoniteOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(neoniteBlock, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(stamaticOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(igniousOre, "pickaxe", 2);
    }

    public static void registerKinGenFuel()
    {
        MagiksArrays.kineticGenFuel.add(new ItemStack(Block.tnt));
    }
}
