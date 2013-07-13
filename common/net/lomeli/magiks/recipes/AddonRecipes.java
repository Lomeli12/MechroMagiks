package net.lomeli.magiks.recipes;

import net.lomeli.lomlib.block.BlockUtil;
import net.lomeli.lomlib.item.ItemUtil;
import net.lomeli.lomlib.util.ModLoaded;

import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.api.MechroMagiksAPI;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import ic2.api.recipe.ICraftingRecipeManager;
import ic2.api.recipe.Recipes;

import thermalexpansion.api.crafting.CraftingManagers;
import thermalexpansion.api.item.ItemRegistry;

public class AddonRecipes
{
	private static AddonRecipes instance = new AddonRecipes();
	
	public static AddonRecipes getInstance()
	{
		return instance;
	}
	
	public void loadAddons()
	{
		instance.ThermalExpansionSupport();
		instance.IndustrialCraftSupport();
	}
	
	private void ThermalExpansionSupport()
	{
		if(ModLoaded.isModInstalled("ThermalExpansion", false))
		{
			ItemStack richSlag = ItemRegistry.getItem("slagRich", 1);
			ItemStack dustObsidian = ItemRegistry.getItem("dustObsidian", 1);
			ItemStack dustNickel = ItemRegistry.getItem("dustNickel", 1);
			ItemStack dustNickel2 = ItemRegistry.getItem("dustNickel", 2);
			ItemStack dustPlatinum = ItemRegistry.getItem("dustPlatinum", 1);
			ItemStack dustElectrum = ItemRegistry.getItem("dustElectrum", 1);
			ItemStack dustInvar = ItemRegistry.getItem("dustInvar", 1);
			
			ItemStack ingots = ItemRegistry.getItem("ingotNickel", 1);
			
			ItemStack ferrousOre = BlockUtil.getBlockFromModWithMeta("blockOre", 4, "thermalexpansion.block.TEBlocks");
			
			CraftingManagers.pulverizerManager.addRecipe(400, 
				new ItemStack(ModBlocksMagiks.stamaticOre), new ItemStack(ModItemsMagiks.ingotDust, 2, 2));
			CraftingManagers.pulverizerManager.addRecipe(400, 
				new ItemStack(ModBlocksMagiks.igniousOre), new ItemStack(ModItemsMagiks.ingotDust, 2, 3));
			CraftingManagers.pulverizerManager.addRecipe(200, 
				new ItemStack(ModItemsMagiks.ingotStamatic), ModItemsMagiks.dustStamatic);
			CraftingManagers.pulverizerManager.addRecipe(200, 
				new ItemStack(ModItemsMagiks.ingotIgnious), ModItemsMagiks.dustIgnious);
			CraftingManagers.pulverizerManager.addRecipe(200, 
				new ItemStack(ModItemsMagiks.ingotVesi), ModItemsMagiks.dustVesi);
			
			CraftingManagers.smelterManager.addRecipe(320, new ItemStack(ModBlocksMagiks.stamaticOre), 
					new ItemStack(Block.sand), new ItemStack(ModItemsMagiks.ingotStamatic, 2));
			CraftingManagers.smelterManager.addRecipe(360, new ItemStack(ModBlocksMagiks.stamaticOre), 
					richSlag, new ItemStack(ModItemsMagiks.ingotStamatic, 2), 
					ModItemsMagiks.dustVesi, 75);
			CraftingManagers.smelterManager.addRecipe(400, new ItemStack(ModBlocksMagiks.stamaticOre), 
				new ItemStack(Block.sand), new ItemStack(ModItemsMagiks.ingotStamatic, 2), 
				ModItemsMagiks.dustVesi, 10);
			CraftingManagers.smelterManager.addRecipe(320, new ItemStack(ModBlocksMagiks.igniousOre), 
					new ItemStack(Block.sand), new ItemStack(ModItemsMagiks.ingotIgnious, 2));
			
			MechroMagiksAPI.addCrushableOre(Block.obsidian.blockID, dustObsidian);
			MechroMagiksAPI.addCrushableOre(ferrousOre.itemID, dustNickel2, 4);
			MechroMagiksAPI.addCrushableOre(ingots.itemID, dustNickel, 40);
			MechroMagiksAPI.addCrushableOre(ingots.itemID, dustPlatinum, 41);
			MechroMagiksAPI.addCrushableOre(ingots.itemID, dustElectrum, 38);
			MechroMagiksAPI.addCrushableOre(ingots.itemID, dustInvar, 39);
		}
	}
	
	private void IndustrialCraftSupport()
	{
		String itemClassLoc = "ic2.core.Ic2Items";
		if(ModLoaded.isModInstalled("IC2", false))
		{
			Recipes.macerator.addRecipe(new ItemStack(ModBlocksMagiks.stamaticOre), 
				new ItemStack(ModItemsMagiks.ingotDust, 2, 2));
			Recipes.macerator.addRecipe(new ItemStack(ModBlocksMagiks.igniousOre), 
				new ItemStack(ModItemsMagiks.ingotDust, 2, 3));
			Recipes.macerator.addRecipe(new ItemStack(ModItemsMagiks.ingotStamatic), 
				ModItemsMagiks.dustStamatic);
			Recipes.macerator.addRecipe(new ItemStack(ModItemsMagiks.ingotIgnious), 
				ModItemsMagiks.dustIgnious);
			Recipes.macerator.addRecipe(new ItemStack(ModItemsMagiks.ingotVesi), 
				ModItemsMagiks.dustVesi);
			
			ICraftingRecipeManager advRecipes = Recipes.advRecipes;
			
    		ItemStack ironFurnace = ItemUtil.getItem("ironFurnace", itemClassLoc);
    		ItemStack electroFurnace = ItemUtil.getItem("electroFurnace", itemClassLoc);
    		ItemStack copperWire = ItemUtil.getItem("insulatedCopperCableItem", itemClassLoc);
    		ItemStack macerator = ItemUtil.getItem("macerator", itemClassLoc);
    		ItemStack machineBlock = ItemUtil.getItem("machine", itemClassLoc);
    		ItemStack electronicCircuit = ItemUtil.getItem("electronicCircuit", itemClassLoc);
    		ItemStack extractor = ItemUtil.getItem("extractor", itemClassLoc);
    		ItemStack treetap = ItemUtil.getItem("treetap", itemClassLoc);
    		ItemStack compressor = ItemUtil.getItem("compressor", itemClassLoc);
    		ItemStack miner = ItemUtil.getItem("miner", itemClassLoc);
    		ItemStack miningPipe = ItemUtil.getItem("miningPipe", itemClassLoc);
    		ItemStack pump = ItemUtil.getItem("pump", itemClassLoc);
    		ItemStack cell = ItemUtil.getItem("cell", itemClassLoc);
    		ItemStack electrolyzer = ItemUtil.getItem("electrolyzer", itemClassLoc);
    		ItemStack personalSafe = ItemUtil.getItem("personalSafe", itemClassLoc);
    		ItemStack energyOMat = ItemUtil.getItem("energyOMat", itemClassLoc);
    		ItemStack reBattery = ItemUtil.getItem("reBattery", itemClassLoc);
    		ItemStack canner = ItemUtil.getItem("canner", itemClassLoc);
    		ItemStack teslaCoil = ItemUtil.getItem("teslaCoil", itemClassLoc);
    		ItemStack mvTransformer = ItemUtil.getItem("mvTransformer", itemClassLoc);
    		ItemStack solarPanel = ItemUtil.getItem("solarPanel", itemClassLoc);
    		ItemStack generator = ItemUtil.getItem("generator", itemClassLoc);
    		ItemStack reactorHeatSwitch = ItemUtil.getItem("reactorHeatSwitch", itemClassLoc);
    		ItemStack denseCopperPlate = ItemUtil.getItem("denseCopperPlate", itemClassLoc);
    		ItemStack reactorHeatpack = ItemUtil.getItem("reactorHeatpack", itemClassLoc);
    		ItemStack lavaCell = ItemUtil.getItem("lavaCell", itemClassLoc);
    		ItemStack hvTransformer = ItemUtil.getItem("hvTransformer", itemClassLoc);
    		ItemStack trippleInsulatedIronCableItem = ItemUtil.getItem("trippleInsulatedIronCableItem", itemClassLoc);
    		ItemStack energyCrystal = ItemUtil.getItem("energyCrystal", itemClassLoc);
    		ItemStack remote = ItemUtil.getItem("remote", itemClassLoc);
    		ItemStack detectorCableItem = ItemUtil.getItem("detectorCableItem", itemClassLoc);
    		ItemStack lapotronCrystal = ItemUtil.getItem("lapotronCrystal", itemClassLoc);
    		ItemStack miningDrill = ItemUtil.getItem("miningDrill", itemClassLoc);
    		ItemStack chargedReBattery = ItemUtil.getItem("chargedReBattery", itemClassLoc);
    		ItemStack chainsaw = ItemUtil.getItem("chainsaw", itemClassLoc);
    		ItemStack odScanner = ItemUtil.getItem("odScanner", itemClassLoc);
    		ItemStack electricWrench = ItemUtil.getItem("electricWrench", itemClassLoc);
    		ItemStack wrench = ItemUtil.getItem("wrench", itemClassLoc);
    		ItemStack electricTreetap = ItemUtil.getItem("electricTreetap", itemClassLoc);
    		ItemStack ecMeter = ItemUtil.getItem("ecMeter", itemClassLoc);
    		ItemStack electricHoe = ItemUtil.getItem("electricHoe", itemClassLoc);
    		ItemStack frequencyTransmitter = ItemUtil.getItem("frequencyTransmitter", itemClassLoc);
    		ItemStack advancedCircuit = ItemUtil.getItem("advancedCircuit", itemClassLoc);
    		ItemStack batPack = ItemUtil.getItem("batPack", itemClassLoc);
    		
    		OreDictionary.registerOre("copperWire", copperWire);
            OreDictionary.registerOre("electronicCircuit", electronicCircuit);
    		
    		advRecipes.addRecipe(electroFurnace, new Object[]{ " C ", "RFR", 'C', 
    		"electronicCircuit", 'R', Item.redstone, 'F', ironFurnace});
    		advRecipes.addRecipe(macerator, new Object[] { "FFF", "SMS", " C ", 'F',Item.flint, 
    		'S',Block.cobblestone, 'M',machineBlock, 'C',"electronicCircuit"});
    		advRecipes.addRecipe(extractor, new Object[] { "TMT", "TCT", 'T', treetap, 
    		'M', machineBlock, 'C', "electronicCircuit" });
    		advRecipes.addRecipe(compressor, new Object[] { "S S", "SMS", "SCS", 'S', Block.stone, 
    		'M', machineBlock, 'C', "electronicCircuit" });
    		advRecipes.addRecipe(miner, new Object[] { "CMC", " P ", " P ", 'P', miningPipe, 
    		'M', machineBlock, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(pump, new Object[] { "cCc", "cMc", "PTP", 'c', cell, 'T', treetap, 
    	    'P', miningPipe, 'M', machineBlock, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electrolyzer, new Object[] { "c c", "cCc", "EME", 'E', cell, 'c', "copperWire", 
    	    'M', machineBlock, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(personalSafe, new Object[] { "c", "M", "C",'c', "electronicCircuit", 
    	    'C', Block.chest, 'M', machineBlock });
    	    advRecipes.addRecipe(energyOMat, new Object[] { "RBR", "CMC", 'R', Item.redstone, 'C', "copperWire", 
    	    'M', machineBlock, 'B', reBattery });
    	    advRecipes.addRecipe(canner, new Object[] { "TCT", "TMT", "TTT", 'T', "ingotTin", 'M', machineBlock, 
    	    'C', "electronicCircuit" });
    	    advRecipes.addRecipe(teslaCoil, new Object[] { "RRR", "RMR", "ICI", 'M', mvTransformer, 'R', Item.redstone, 
    	    'C', "electronicCircuit", 'I', "ingotRefinedIron" });
    	    advRecipes.addRecipe(solarPanel, new Object[] { "CgC", "gCg", "cGc", 'G', generator, 'C', "dustCoal", 
    	    'g', Block.glass, 'c', "electronicCircuit" });
    	    advRecipes.addRecipe(reactorHeatSwitch, new Object[] { " c ", "TCT", " T ", 'c', "electronicCircuit", 'T', "ingotTin", 
    	    'C', denseCopperPlate });
    	    advRecipes.addRecipe(reactorHeatpack, new Object[] { "c", "L", "C", 'c', "electronicCircuit", 'C', denseCopperPlate, 
    	    'L', lavaCell });
    	    advRecipes.addRecipe(hvTransformer, new Object[] { " c ", "CED", " c ", 'E', mvTransformer, 'c', 
    	    trippleInsulatedIronCableItem, 'D', energyCrystal, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(remote, new Object[] { " c ", "GCG", "TTT", 'c', "copperWire", 'G', Item.glowstone, 
    	    'C', "electronicCircuit", 'T', Block.tnt });
    	    advRecipes.addRecipe(detectorCableItem, new Object[] { " C ", "RIR", " R ", 'R', Item.redstone, 
    	    'I', trippleInsulatedIronCableItem, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(lapotronCrystal, new Object[] { "LCL", "LDL", "LCL", 'D', energyCrystal, 'C', "electronicCircuit", 
    	    'L', new ItemStack(Item.dyePowder, 1, 4) });
    	    advRecipes.addRecipe(miningDrill, new Object[] { " I ", "ICI", "IBI", 'I', "ingotRefinedIron", 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(miningDrill, new Object[] { " I ", "ICI", "IBI", 'I', "ingotRefinedIron", 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(chainsaw, new Object[] { " II", "ICI", "BI ", 'I', "ingotRefinedIron", 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(chainsaw, new Object[] { " II", "ICI", "BI ", 'I', "ingotRefinedIron", 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(odScanner, new Object[] { " G ", "CBC", "ccc", 'B', reBattery, 
    	    'c', "copperWire", 'G', Item.glowstone, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(odScanner, new Object[] { " G ", "CBC", "ccc", 'B', chargedReBattery, 
    	    'c', "copperWire", 'G', Item.glowstone, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricWrench, new Object[] { "  W", " C ", "B  ", 'W', wrench, 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricWrench, new Object[] { "  W", " C ", "B  ", 'W', wrench, 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricTreetap, new Object[] { "  W", " C ", "B  ", 'W', treetap, 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricTreetap, new Object[] { "  W", " C ", "B  ", 'W', treetap, 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(ecMeter, new Object[] { " G ", "cCc", "c c", 'G', Item.glowstone, 
    	    'c', "copperWire", 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricHoe, new Object[] { "II ", " C ", " B ", 'I', "ingotRefinedIron", 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricHoe, new Object[] { "II ", " C ", " B ", 'I', "ingotRefinedIron", 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(advancedCircuit, new Object[] { "RGR", "LCL", "RGR", 'L', new ItemStack(Item.dyePowder, 1, 4), 
    	    'G', Item.glowstone, 'R', Item.redstone, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(advancedCircuit, new Object[] { "RLR", "GCG", "RLR", 'L', new ItemStack(Item.dyePowder, 1, 4), 
    	    'G', Item.glowstone, 'R', Item.redstone, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electronicCircuit, new Object[] { "CCC", "RIR", "CCC", 'I', "ingotRefinedIron", 
    	    'R', Item.redstone, 'C', "copperWire" });
    	    advRecipes.addRecipe(electronicCircuit, new Object[] { "CRC", "CIC", "CRC", 'I', "ingotRefinedIron", 
    	    'R', Item.redstone, 'C', "copperWire" });
    	    advRecipes.addRecipe(batPack, new Object[] { "BCB", "BTB", "B B", 'T', "ingotTin", 'C', "electronicCircuit", 'B', chargedReBattery });
    	    advRecipes.addRecipe(batPack, new Object[] { "BCB", "BTB", "B B", 'T', "ingotTin", 'C', "electronicCircuit", 'B', reBattery });
    	    
    	    advRecipes.addShapelessRecipe(frequencyTransmitter, new Object[] { "electronicCircuit", "copperWire" });
		}
	}
}
