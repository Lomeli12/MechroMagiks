package net.lomeli.magiks.blocks;

import net.lomeli.magiks.api.cafting.KineticGenFuel;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.machine.BlockKineticGenerator;
import net.lomeli.magiks.blocks.machine.BlockMultiFurnaceCore;
import net.lomeli.magiks.blocks.machine.BlockSolarMistCollector;
import net.lomeli.magiks.blocks.machine.BlockCoil;
import net.lomeli.magiks.blocks.machine.parts.BlockMecroBlock;
import net.lomeli.magiks.blocks.machine.parts.BlockMecroGlass;
import net.lomeli.magiks.blocks.machine.parts.BlockMultiFurnaceDummy;
import net.lomeli.magiks.lib.Ints;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocksMagiks
{
    public static Block neoniteOre, neoniteBlock, kineticGenerator,
            manceryBlock, manceryGlass, stamaticOre, igniousOre,
            solarMistCollector, mistCrafter, dupeFurnace, burningStone,
            manceryBrick, obsidianStairs, manceryBlockStairs, manceryBrickStairs,
            burningStoneStairs, netherIgnious, copperOre, tinOre, silverOre,
            smallCoil;

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
        dupeFurnace = new BlockMultiFurnaceCore(Ints.dupeFurnaceID).setHardness(0.5F).setResistance(5000F);
        burningStone = new BlockMultiFurnaceDummy(Ints.dupeDummyID).setHardness(0.5F).setResistance(5000F);
        mistCrafter = new BlockMistWorkBench(Ints.mistCrafterID, Material.anvil)
                .setUnlocalizedName("MistCrafter").setResistance(100F);
        manceryBrick = new BlockMagiks(Ints.manceryBrickID, Material.rock,
                "mancerybricks").setUnlocalizedName("mancerybricks")
                .setHardness(0.5F).setResistance(5000F);
        obsidianStairs = new BlockMagikStairs(Ints.obsidianStairsID, Block.obsidian, 2,
        		"obsidian").setHardness(50.0F).setResistance(2000.0F)
        		.setUnlocalizedName("obsidianStairs");
        manceryBlockStairs = new BlockMagikStairs(Ints.manceryStoneStairsID, manceryBlock, 3,
        		"manceryblock").setUnlocalizedName("manceryblockstairs")
                .setHardness(0.5F).setResistance(5000F);
        manceryBrickStairs = new BlockMagikStairs(Ints.manceryBrickStairsID, manceryBrick, 4,
        		"mancerybricks").setUnlocalizedName("mancerybricksstairs")
                .setHardness(0.5F).setResistance(5000F);
        burningStoneStairs = new BlockMagikStairs(Ints.burningBrickStairsID, burningStone, 5,
        		"burningstone").setUnlocalizedName("burningstonestairs")
                .setHardness(0.5F).setResistance(5000F);
        netherIgnious = new BlockMagiks(Ints.netherIgniousID, Material.rock, "orenetherignious")
        		.setHardness(3F).setResistance(5F).setUnlocalizedName("netherignious");
        copperOre = new BlockMagiks(Ints.copperOreID, Material.rock, "oreCopper")
        		.setHardness(3F).setResistance(5F).setUnlocalizedName("copperOre");
        tinOre = new BlockMagiks(Ints.tinOreID, Material.rock, "oreTin")
				.setHardness(3F).setResistance(5F).setUnlocalizedName("tinOre");
        silverOre = new BlockMagiks(Ints.silverOreID, Material.rock, "oreSilver")
				.setHardness(3F).setResistance(5F).setUnlocalizedName("silverOre");
        smallCoil = new BlockCoil(Ints.smallCoilID, Material.rock, "oreSilver");

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
        GameRegistry.registerBlock(netherIgnious, "Nether Ignious Ore");
        GameRegistry.registerBlock(copperOre, "Copper Ore");
        GameRegistry.registerBlock(tinOre, "Tin Ore");
        GameRegistry.registerBlock(silverOre, "Silver Ore");
        GameRegistry.registerBlock(smallCoil, "Small Coil");
        
        GameRegistry.registerBlock(obsidianStairs, "Obsidian Stairs");
        GameRegistry.registerBlock(manceryBlockStairs, "Mancery Stone Stairs");
        GameRegistry.registerBlock(manceryBrickStairs, "Mancery Brick Stairs");
        GameRegistry.registerBlock(burningStoneStairs, "Burning Stone Stairs");

        LanguageRegistry.addName(neoniteOre, "Neonite Ore");
        LanguageRegistry.addName(neoniteBlock, "Neonite Block");
        LanguageRegistry.addName(kineticGenerator, "Kinetic Generator");
        LanguageRegistry.addName(manceryBlock, "Mancery Stone");
        LanguageRegistry.addName(manceryGlass, "Mancery Glass");
        LanguageRegistry.addName(stamaticOre, "Stamatic Ore");
        LanguageRegistry.addName(igniousOre, "Ignious Ore");
        LanguageRegistry.addName(solarMistCollector, "Solar Mist Collector");
        LanguageRegistry.addName(mistCrafter, "Mist Crafting Table");
        LanguageRegistry.addName(dupeFurnace, "Piras Oven Core");
        LanguageRegistry.addName(burningStone, "Burning Stone");
        LanguageRegistry.addName(manceryBrick, "Mancery Brick");
        LanguageRegistry.addName(netherIgnious, "Nether Ignious Ore");
        LanguageRegistry.addName(copperOre, "Copper Ore");
        LanguageRegistry.addName(tinOre, "Tin Ore");
        LanguageRegistry.addName(silverOre, "Silver Ore");
        LanguageRegistry.addName(smallCoil, "Small Coil");
        
        LanguageRegistry.addName(obsidianStairs, "Obsidian Stairs");
        LanguageRegistry.addName(manceryBlockStairs, "Mancery Stone Stairs");
        LanguageRegistry.addName(manceryBrickStairs, "Mancery Brick Stairs");
        LanguageRegistry.addName(burningStoneStairs, "Burning Stone Stairs");

        MinecraftForge.setBlockHarvestLevel(neoniteOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(neoniteBlock, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(stamaticOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(igniousOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(netherIgnious, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(tinOre, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(silverOre, "pickaxe", 2);
        
        OreDictionary.registerOre("oreCopper", new ItemStack(copperOre));
        OreDictionary.registerOre("oreTin", new ItemStack(tinOre));
        OreDictionary.registerOre("oreSilver", new ItemStack(silverOre));
    }

    public static final int WILDCARD = Short.MAX_VALUE;
    public static void registerKinGenFuel()
    {
    	KineticGenFuel.fuel().addFuel(0, new ItemStack(Block.tnt), 75);
    	MagiksArrays.addNewKinGenFuel(new ItemStack(Block.tnt), 75, 0);
    }
}
