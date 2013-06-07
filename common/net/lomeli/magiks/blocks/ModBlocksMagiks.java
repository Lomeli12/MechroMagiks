package net.lomeli.magiks.blocks;

import net.lomeli.lomlib.block.BlockGeneric;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.api.crafting.KineticGenFuel;
import net.lomeli.magiks.api.libs.MagiksArrays;

import net.lomeli.magiks.blocks.aesthetic.BlockMechroPanes;
import net.lomeli.magiks.blocks.aesthetic.BlockMecroGlass;
import net.lomeli.magiks.blocks.liquids.BlockNeoStill;
import net.lomeli.magiks.blocks.machine.BlockBuilderCore;
import net.lomeli.magiks.blocks.machine.BlockKineticGenerator;
import net.lomeli.magiks.blocks.machine.BlockMancerWorkTable;
import net.lomeli.magiks.blocks.machine.BlockMultiFurnaceCore;
import net.lomeli.magiks.blocks.machine.BlockSolarMistCollector;
import net.lomeli.magiks.blocks.machine.BlockOreCrusher;
import net.lomeli.magiks.blocks.machine.BlockCoil;
import net.lomeli.magiks.blocks.machine.parts.BlockBuilderDummy;
import net.lomeli.magiks.blocks.machine.parts.BlockMecroBlock;
import net.lomeli.magiks.blocks.machine.parts.BlockMultiFurnaceDummy;
import net.lomeli.magiks.lib.ItemIDs;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocksMagiks
{
	//Others
    public static Block manceryBlock, manceryGlass, burningStone, manceryBrick,
    	obsidianStairs, manceryBlockStairs, manceryBrickStairs, burningStoneStairs, 
        mancerWorkTable, hollowWood, linkingChest, manceryPane;
    
    //Ores
    public static Block neoniteOre, copperOre, tinOre, silverOre, igniousOre, 
    	neoniteBlock, netherIgnious, stamaticOre, stamaticBlock, igniousBlock;
    
    //Machines
    public static Block kineticGenerator, solarMistCollector, oreCrusher, 
    	smallCoil, builderBlock, builderCore, dupeFurnace;
    
    //Liquids
    public static Block neoStill;//, neoMoving;

    public static void registerBlocks()
    {
        neoniteOre = new BlockNeoniteOre(ItemIDs.enchantedOreID, "neoniteOre")
                .setUnlocalizedName("neoniteOre").setLightValue(0.5F)
                .setResistance(50F).setHardness(3.0F);
        neoniteBlock = new BlockMagiks(ItemIDs.enchantedBlockID, Material.rock,
                "neoniteBlock").setUnlocalizedName("neoniteBlock")
                .setLightValue(1.5F).setResistance(100F).setHardness(3.0F)
                .setLightOpacity(0).setStepSound(Block.soundStoneFootstep);
        kineticGenerator = new BlockKineticGenerator(ItemIDs.kineticGenID,
                Material.rock).setUnlocalizedName("kineticgen")
                .setResistance(1000F).setHardness(3F);
        manceryBlock = new BlockMecroBlock(ItemIDs.manceryBlockID, Material.rock,
                "manceryblock").setUnlocalizedName("manceryblock")
                .setHardness(0.5F).setResistance(5000F);
        manceryGlass = new BlockMecroGlass(ItemIDs.manceryGlassID, Material.glass,
                "manceryglass").setUnlocalizedName("manceryglass")
                .setHardness(0.5F).setResistance(5000F);
        stamaticOre = new BlockMagiks(ItemIDs.stamaticOreID, Material.rock,
                "orestamatic").setUnlocalizedName("orestamatic")
                .setHardness(3F).setResistance(5F);
        igniousOre = new BlockMagiks(ItemIDs.igniousOreID, Material.rock,
                "oreignious").setUnlocalizedName("oreignious").setHardness(3F)
                .setResistance(5F);
        solarMistCollector = new BlockSolarMistCollector(ItemIDs.solarGenID,
                Material.rock, "solarmistcollector")
                .setUnlocalizedName("solarmistcollector").setHardness(3F)
                .setResistance(100F);
        dupeFurnace = new BlockMultiFurnaceCore(ItemIDs.dupeFurnaceID).setHardness(0.5F).setResistance(5000F);
        burningStone = new BlockMultiFurnaceDummy(ItemIDs.dupeDummyID).setHardness(0.5F).setResistance(5000F);
        
        manceryBrick = new BlockMagiks(ItemIDs.manceryBrickID, Material.rock,
                "mancerybricks").setUnlocalizedName("mancerybricks")
                .setHardness(0.5F).setResistance(5000F);
        obsidianStairs = new BlockMagikStairs(ItemIDs.obsidianStairsID, Block.obsidian, 2,
        		"obsidian").setHardness(50.0F).setResistance(2000.0F)
        		.setUnlocalizedName("obsidianStairs");
        manceryBlockStairs = new BlockMagikStairs(ItemIDs.manceryStoneStairsID, manceryBlock, 3,
        		"manceryblock").setUnlocalizedName("manceryblockstairs")
                .setHardness(0.5F).setResistance(5000F);
        manceryBrickStairs = new BlockMagikStairs(ItemIDs.manceryBrickStairsID, manceryBrick, 4,
        		"mancerybricks").setUnlocalizedName("mancerybricksstairs")
                .setHardness(0.5F).setResistance(5000F);
        burningStoneStairs = new BlockMagikStairs(ItemIDs.burningBrickStairsID, burningStone, 5,
        		"burningstone").setUnlocalizedName("burningstonestairs")
                .setHardness(0.5F).setResistance(5000F);
        netherIgnious = new BlockMagiks(ItemIDs.netherIgniousID, Material.rock, "orenetherignious")
        		.setHardness(3F).setResistance(5F).setUnlocalizedName("netherignious");
        copperOre = new BlockMagiks(ItemIDs.copperOreID, Material.rock, "oreCopper")
        		.setHardness(3F).setResistance(5F).setUnlocalizedName("copperOre");
        tinOre = new BlockMagiks(ItemIDs.tinOreID, Material.rock, "oreTin")
				.setHardness(3F).setResistance(5F).setUnlocalizedName("tinOre");
        silverOre = new BlockMagiks(ItemIDs.silverOreID, Material.rock, "oreSilver")
				.setHardness(3F).setResistance(5F).setUnlocalizedName("silverOre");
        smallCoil = new BlockCoil(ItemIDs.smallCoilID, "oreSilver").setHardness(3F).setResistance(5F)
        	.setUnlocalizedName("smallcoil");
        mancerWorkTable = new BlockMancerWorkTable(ItemIDs.mancerWorkTableID, Material.wood, "wood")
        		.setHardness(2F).setResistance(5F).setUnlocalizedName("mancerWT");
        hollowWood = new BlockHollowWood(ItemIDs.hollowWoodID, Material.wood, "wood")
        		.setHardness(2F).setResistance(5F).setUnlocalizedName("hallowWood");
        linkingChest = new BlockLinkingChest(ItemIDs.linkingChestID, "mancerybricks")
        	.setHardness(0.5F).setResistance(5000F).setUnlocalizedName("linkingChest");
        manceryPane = new BlockMechroPanes(ItemIDs.manceryPaneID, "manceryglass")
        	.setUnlocalizedName("mancerypanes");
        oreCrusher = new BlockOreCrusher(ItemIDs.oreCrusherID).setUnlocalizedName("oreCrusher");
        stamaticBlock = new BlockGeneric(ItemIDs.stamaticBlockID, Material.iron, Strings.MOD_ID, 
        	"blockStamatic").setHardness(0.5F).setResistance(5000F)
        	.setUnlocalizedName("stamaticBlock").setCreativeTab(Magiks.modTab);
        igniousBlock = new BlockGeneric(ItemIDs.igniousBlockID, Material.iron, Strings.MOD_ID, 
            "blockIgnious").setHardness(0.5F).setResistance(5000F)
            .setUnlocalizedName("igniousBlock").setCreativeTab(Magiks.modTab);
        builderCore = new BlockBuilderCore(ItemIDs.builderCoreID).setUnlocalizedName("builderCore");
        builderBlock = new BlockBuilderDummy(ItemIDs.builderDummyID);
        neoStill = new BlockNeoStill(ItemIDs.neoStillID);
        
        GameRegistry.registerBlock(neoniteOre, "Neonite Ore");
        GameRegistry.registerBlock(neoniteBlock, "Neonite Block");
        GameRegistry.registerBlock(kineticGenerator, "Kinetic Generator");
        GameRegistry.registerBlock(manceryBlock, "Mancery Stone");
        GameRegistry.registerBlock(manceryGlass, "Mancery Glass");
        GameRegistry.registerBlock(stamaticOre, "Stamatic Ore");
        GameRegistry.registerBlock(igniousOre, "Ignious Ore");
        GameRegistry.registerBlock(solarMistCollector, "Solar Mist Collector");
        GameRegistry.registerBlock(dupeFurnace, "Piras Oven Core");
        GameRegistry.registerBlock(burningStone, "Burning Stone");
        GameRegistry.registerBlock(manceryBrick, "Mancery Brick");
        GameRegistry.registerBlock(netherIgnious, "Nether Ignious Ore");
        GameRegistry.registerBlock(copperOre, "Copper Ore");
        GameRegistry.registerBlock(tinOre, "Tin Ore");
        GameRegistry.registerBlock(silverOre, "Silver Ore");
        GameRegistry.registerBlock(smallCoil, "Small Mist Coil");
        GameRegistry.registerBlock(manceryPane, "Mancery Pane");
        GameRegistry.registerBlock(stamaticBlock, "Stamatic Block");
        GameRegistry.registerBlock(igniousBlock, "Ignious Block");
        GameRegistry.registerBlock(builderCore, "Builder");
        GameRegistry.registerBlock(builderBlock, "BuilderBlock");
        GameRegistry.registerBlock(neoStill, "Liquid Neonite");
        
        GameRegistry.registerBlock(obsidianStairs, "Obsidian Stairs");
        GameRegistry.registerBlock(manceryBlockStairs, "Mancery Stone Stairs");
        GameRegistry.registerBlock(manceryBrickStairs, "Mancery Brick Stairs");
        GameRegistry.registerBlock(burningStoneStairs, "Burning Stone Stairs");
        GameRegistry.registerBlock(mancerWorkTable, "Mancer WorkTable");
        GameRegistry.registerBlock(hollowWood, "Hollow Wood");
        GameRegistry.registerBlock(linkingChest, "Linking Chest");
        GameRegistry.registerBlock(oreCrusher, "Ore Crusher");

        LanguageRegistry.addName(neoniteOre, "Neonite Ore");
        LanguageRegistry.addName(neoniteBlock, "Neonite Block");
        LanguageRegistry.addName(kineticGenerator, "Kinetic Generator");
        LanguageRegistry.addName(manceryBlock, "Mancery Stone");
        LanguageRegistry.addName(manceryGlass, "Mancery Glass");
        LanguageRegistry.addName(stamaticOre, "Stamatic Ore");
        LanguageRegistry.addName(igniousOre, "Ignious Ore");
        LanguageRegistry.addName(solarMistCollector, "Solar Mist Collector");
        LanguageRegistry.addName(dupeFurnace, "Piras Oven Core");
        LanguageRegistry.addName(burningStone, "Burning Stone");
        LanguageRegistry.addName(manceryBrick, "Mancery Brick");
        LanguageRegistry.addName(netherIgnious, "Nether Ignious Ore");
        LanguageRegistry.addName(copperOre, "Copper Ore");
        LanguageRegistry.addName(tinOre, "Tin Ore");
        LanguageRegistry.addName(silverOre, "Silver Ore");
        LanguageRegistry.addName(smallCoil, "Small Coil");
        LanguageRegistry.addName(manceryPane, "Mancery Pane");
        LanguageRegistry.addName(stamaticBlock, "Stamatic Block");
        LanguageRegistry.addName(igniousBlock, "Ignious Block");
        LanguageRegistry.addName(builderCore, "Builder (In development, DO NOT USE)");
        LanguageRegistry.addName(builderBlock, "Builder (In development, DO NOT USE)");
        LanguageRegistry.addName(neoStill, "Liquid Neonite");
        
        LanguageRegistry.addName(obsidianStairs, "Obsidian Stairs");
        LanguageRegistry.addName(manceryBlockStairs, "Mancery Stone Stairs");
        LanguageRegistry.addName(manceryBrickStairs, "Mancery Brick Stairs");
        LanguageRegistry.addName(burningStoneStairs, "Burning Stone Stairs");
        LanguageRegistry.addName(mancerWorkTable, "Mancer WorkTable");
        LanguageRegistry.addName(hollowWood, "Hollow Wood");
        LanguageRegistry.addName(linkingChest, "Linking Chest");
        LanguageRegistry.addName(oreCrusher, "Ore Crusher");

        MinecraftForge.setBlockHarvestLevel(neoniteOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(neoniteBlock, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(stamaticOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(igniousOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(netherIgnious, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(tinOre, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(silverOre, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(mancerWorkTable, "axe", 0);
        MinecraftForge.setBlockHarvestLevel(hollowWood, "axe", 0);
        
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
