package net.lomeli.magiks.recipes;

import net.lomeli.lomlib.item.ItemUtil;
import net.lomeli.lomlib.util.ModLoaded;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.api.MechroMagiksAPI;
import net.lomeli.magiks.api.crafting.BasicRecipeManager;
import net.lomeli.magiks.api.crafting.specialrecipes.MancerShapedOreRecipes;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.ModBlocksMagiks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

import ic2.api.recipe.ICraftingRecipeManager;
import ic2.api.recipe.Recipes;

public class MagiksRecipes 
{
	public static final int WILDCARD_DAMAGE_VALUE = Short.MAX_VALUE;
	
	public static void registerRecipes()
	{
		ic2Compatibility();
		registerBlockRecipes();
		registerItemRecipes();
		registerFurnaceRecipes();
		registerMachineRecipes();
		register4x4Recipes();
		registerBlueprintRecipes();
		registerOreCrusherRecipes();
	}
	
	public static void registerBlockRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.neoniteBlock, 1), new Object[] {
            "GGG", "GGG", "GGG", 'G', ModItemsMagiks.neoniteGem });
		GameRegistry.addShapelessRecipe(new ItemStack(
            ModItemsMagiks.neoniteGem, 9), new Object[] { ModBlocksMagiks.neoniteBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.manceryBlock, 4), new Object[] {
            "RSR", "SIS", "RSR", 'R', Item.redstone, 'S', Block.stone, 'I',
            new ItemStack(ModItemsMagiks.ingotStamatic, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.manceryGlass, 4), new Object[] {
            "RGR", "GIG", "RGR", 'R', Item.redstone, 'G', Block.glass, 'I',
            new ItemStack(ModItemsMagiks.ingotStamatic, 4) });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.solarMistCollector, 1),
            new Object[] { "D D", "PPP", "MMM", 'D',
                    ModItemsMagiks.ingotVesi, 'P',
                    ModItemsMagiks.mistPanel, 'M', ModBlocksMagiks.manceryBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.burningStone, 4), new Object[] {
            "IBI", "BRB", "IBI", 'I', ModItemsMagiks.ingotIgnious, 'B',
            ModBlocksMagiks.manceryBrick, 'R', Item.ingotIron });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.manceryBrick, 4), new Object[] {
            "BB", "BB", 'B', ModBlocksMagiks.manceryBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.dupeFurnace, 1),
            new Object[] { "BBB", "BFB", "BBB", 'B', ModBlocksMagiks.burningStone, 'F',
                    Block.furnaceIdle });
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.dupeFurnace, 1), new Object[] {
            "BBB", "BFB", "BBB", 'B', ModBlocksMagiks.burningStone, 'F',
            Block.furnaceBurning });
		
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.manceryPane, 16), new Object[]
			{ "GGG","GGG", 'G', ModBlocksMagiks.manceryGlass });
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocksMagiks.mancerWorkTable, 1),
			new Object[]{"RPR","WCW", "S S", 'S', Item.stick, 'W',Block.planks, 'C', Block.workbench,
			'P',Item.paper, 'R',Item.redstone});
		
	}
	
    public static void registerItemRecipes()
    {    	
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.enchantedDiamond, 1), 
        		new Object[] { "GEG", "EDE", "RER", 'G', Item.lightStoneDust, 'R',
                Item.redstone, 'D', Item.diamond, 'E', ModItemsMagiks.neoniteGem });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.ironStick, 2), new Object[] {
                "  I", " I ", "I  ", 'I', Item.ingotIron });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.ironBand, 1), new Object[] {
                " I ", "IBI", " I ", 'I', ModItemsMagiks.ironStick, 'B', Item.bucketLava });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.flyingRing, 1), new Object[] {
                "GLR", "DID", "FEF", 'G', ModItemsMagiks.enchantedDiamond, 'D', Item.diamond,
                'L', Item.lightStoneDust, 'E', Item.enderPearl, 'I', ModItemsMagiks.ironBand,
                'R', Item.redstone, 'F', Item.feather });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.darkMatter, 1), new Object[] {
                "GMG", "MOM", "GMG", 'G', ModItemsMagiks.neoniteGem, 'M',
                ModBlocksMagiks.manceryBlock, 'O', ModItemsMagiks.enchantedDiamond });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItemsMagiks.deprivedDust, 1),
                new Object[] { Item.lightStoneDust, Block.slowSand,
        		ModItemsMagiks.ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.mistPanel, 1), new Object[] {
                "RDR", "DSD", "III", 'R', Item.redstone, 'S',
                Block.daylightSensor, 'D', ModItemsMagiks.deprivedDust, 'I', ModItemsMagiks.ironPlate });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItemsMagiks.ironPlate, 1),
                new Object[] { Item.ingotIron, Item.netherQuartz,
                        Block.stoneSingleSlab });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.burningUpgrade, 1), new Object[] {
                " F ", "BEB", "III", 'B', Item.blazePowder, 'E',
                Item.eyeOfEnder, 'I', ModItemsMagiks.ironPlate, 'F',Item.emerald });

        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.neonitePick, 1), new Object[] {
                "GDG", "EIE", " I ", 'G', ModItemsMagiks.enchantedDiamond, 'D',
                Item.pickaxeDiamond, 'E', ModItemsMagiks.neoniteGem, 'I', ModItemsMagiks.ironStick });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.pirasVarinha, 1), new Object[] {
                "GNG", "IGI", "IWI", 'G', Item.ingotGold, 'N',
                ModItemsMagiks.enchantedDiamond, 'I', ModItemsMagiks.ingotIgnious, 'W',
                new ItemStack(ModItemsMagiks.alchemistWand, 1, WILDCARD_DAMAGE_VALUE) });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.levelingSword), new Object[] {
                " I ", " I ", "GDG", 'I', Item.ingotIron, 'G', ModItemsMagiks.neoniteGem, 'D',
                ModItemsMagiks.darkMatter });
        
        stairRecipes(ModBlocksMagiks.obsidianStairs, Block.obsidian);
        stairRecipes(ModBlocksMagiks.burningStoneStairs, ModBlocksMagiks.burningStone);
        stairRecipes(ModBlocksMagiks.manceryBlockStairs, ModBlocksMagiks.manceryBlock);
        stairRecipes(ModBlocksMagiks.manceryBrickStairs, ModBlocksMagiks.manceryBrick);
        
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.basicWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', Item.emerald, 'R', Item.redstone,
                'S', Item.stick, 'I', ModItemsMagiks.ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.chemistWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', Item.lightStoneDust, 'R',
                Item.diamond, 'S', ModItemsMagiks.basicWand, 'I', ModItemsMagiks.ingotStamatic });
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.alchemistWand, 1), new Object[] {
                " IE", "ISI", "RI ", 'E', ModItemsMagiks.enchantedDiamond, 'R', ModItemsMagiks.darkMatter,
                'S', ModItemsMagiks.chemistWand, 'I', ModItemsMagiks.ingotStamatic });
        
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.wand, 1), new Object[]
                {" CB","CSC","BC ", 'S',Item.stick, 'C',Item.coal,
                    'B',(new ItemStack(Item.dyePowder, 1, 15)) });
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItemsMagiks.copperWire, 4), true,
        		" CS", "CIC", "SC ", 'S',Item.silk, Character.valueOf('C'),"ingotCopper",
        		'I',ModItemsMagiks.ironStick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItemsMagiks.electroicCircuit, 1), true,
        		"DIG","CPC","GID", 'G',Item.goldNugget, 'I',ModItemsMagiks.ironPlate,
        		'C',"copperWire", Character.valueOf('P'),"ingotSilver", 
        		'D', new ItemStack(Item.dyePowder, 1, 2)));
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.smeltingUpgrade, 1), new Object[]
        		{ " G ", "SPS", "III", 'G', Item.ingotGold, 'S', Item.sugar, 'I',ModItemsMagiks.ironPlate,
        		'P',new ItemStack(Item.potion, 1,8194)});
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItemsMagiks.dustVesi, 4), "dustTin",
    			ModItemsMagiks.dustStamatic, "dustSilver", "dustSilver"));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocksMagiks.stamaticBlock), new Object[]
        		{ ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic,
        		ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic,
        		ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic, ModItemsMagiks.ingotStamatic});
        
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocksMagiks.igniousBlock), new Object[]
        		{ ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious,
        		ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious,
        		ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious, ModItemsMagiks.ingotIgnious});
    }

    public static void registerFurnaceRecipes()
    {
        GameRegistry.addSmelting(ModBlocksMagiks.stamaticOre.blockID,
                new ItemStack(ModItemsMagiks.ingotStamatic), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.igniousOre.blockID,
                new ItemStack(ModItemsMagiks.ingotIgnious), 10);
        GameRegistry.addSmelting(ModBlocksMagiks.netherIgnious.blockID, 
        		new ItemStack(ModItemsMagiks.ingotIgnious, 2), 10);
        GameRegistry.addSmelting(ModBlocksMagiks.copperOre.blockID,
                new ItemStack(ModItemsMagiks.ingotCopper), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.tinOre.blockID,
                new ItemStack(ModItemsMagiks.ingotTin), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.silverOre.blockID, 
        		new ItemStack(ModItemsMagiks.ingotSilver), 5);
        GameRegistry.addSmelting(ModItemsMagiks.dustIron.itemID, 
        		new ItemStack(Item.ingotIron), 5);
        GameRegistry.addSmelting(ModItemsMagiks.dustGold.itemID, 
        		new ItemStack(Item.ingotGold), 5);
        GameRegistry.addSmelting(ModItemsMagiks.dustStamatic.itemID, 
        		new ItemStack(ModItemsMagiks.ingotStamatic), 5);
        GameRegistry.addSmelting(ModItemsMagiks.dustIgnious.itemID, 
        		new ItemStack(ModItemsMagiks.ingotIgnious), 5);
        GameRegistry.addSmelting(ModItemsMagiks.dustVesi.itemID, 
        		new ItemStack(ModItemsMagiks.ingotVesi), 5);
        
        GameRegistry.addSmelting(ModItemsMagiks.dustCopper.itemID, 
        		new ItemStack(ModItemsMagiks.ingotCopper), 5);
    	GameRegistry.addSmelting(ModItemsMagiks.dustTin.itemID, 
    			new ItemStack(ModItemsMagiks.ingotTin), 5);
    	GameRegistry.addSmelting(ModItemsMagiks.dustSilver.itemID, 
    			new ItemStack(ModItemsMagiks.ingotSilver), 5);
    	
    	for(ItemStack lead : OreDictionary.getOres("ingotLead"))
    	{
    		if(lead != null)
    			GameRegistry.addSmelting(ModItemsMagiks.dustLead.itemID, 
    	        	lead, 5);
    	}
    }
    
    public static void registerOreCrusherRecipes()
    {
    	MechroMagiksAPI.addCrushableOre(Block.oreIron.blockID, new ItemStack(ModItemsMagiks.dustIron, 2));
    	MechroMagiksAPI.addCrushableOre(Block.oreGold.blockID, new ItemStack(ModItemsMagiks.dustGold, 2));
    	MechroMagiksAPI.addCrushableOre(ModBlocksMagiks.stamaticOre.blockID, 
    		new ItemStack(ModItemsMagiks.dustStamatic, 2));
    	MechroMagiksAPI.addCrushableOre(ModBlocksMagiks.igniousOre.blockID, 
    		new ItemStack(ModItemsMagiks.dustIgnious, 2));
    	
    	MechroMagiksAPI.addCrushableOre(Item.ingotIron.itemID, new ItemStack(ModItemsMagiks.dustIron));
    	MechroMagiksAPI.addCrushableOre(Item.ingotGold.itemID, new ItemStack(ModItemsMagiks.dustGold));
    	MechroMagiksAPI.addCrushableOre(ModItemsMagiks.ingotStamatic.itemID, 
    		new ItemStack(ModItemsMagiks.dustStamatic));
    	MechroMagiksAPI.addCrushableOre(ModItemsMagiks.ingotIgnious.itemID, 
        		new ItemStack(ModItemsMagiks.dustIgnious));
    	MechroMagiksAPI.addCrushableOre(ModItemsMagiks.ingotVesi.itemID, 
    		new ItemStack(ModItemsMagiks.dustVesi));
    	
    	for(ItemStack copper : OreDictionary.getOres("ingotCopper"))
    	{
    		MechroMagiksAPI.addCrushableOre(copper.itemID, copper.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustCopper));
    	}
    	for(ItemStack tin : OreDictionary.getOres("ingotTin"))
    	{
    		MechroMagiksAPI.addCrushableOre(tin.itemID, tin.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustTin));
    	}
    	for(ItemStack silver : OreDictionary.getOres("ingotSilver"))
    	{
    		MechroMagiksAPI.addCrushableOre(silver.itemID, silver.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustSilver));
    	}
    	for(ItemStack lead : OreDictionary.getOres("ingotLead"))
    	{
    		if(lead != null)
    			MechroMagiksAPI.addCrushableOre(lead.itemID, lead.getItemDamage(),
    				new ItemStack(ModItemsMagiks.dustLead));
    	}
    	
    	for(ItemStack copper : OreDictionary.getOres("oreCopper"))
    	{
    		MechroMagiksAPI.addCrushableOre(copper.itemID, copper.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustCopper, 2));
    	}
    	for(ItemStack tin : OreDictionary.getOres("oreTin"))
    	{
    		MechroMagiksAPI.addCrushableOre(tin.itemID, tin.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustTin, 2));
    	}
    	for(ItemStack silver : OreDictionary.getOres("oreSilver"))
    	{
    		MechroMagiksAPI.addCrushableOre(silver.itemID, silver.getItemDamage(),
    	    	new ItemStack(ModItemsMagiks.dustSilver, 2));
    	}
    	for(ItemStack lead : OreDictionary.getOres("oreLead"))
    	{
    		if(lead != null)
    			MechroMagiksAPI.addCrushableOre(lead.itemID, lead.getItemDamage(),
    				new ItemStack(ModItemsMagiks.dustLead, 2));
    	}
    }

    public static void registerMachineRecipes()
    {
    	MechroMagiksAPI.addMachineRecipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.wirelessReciever), true,
		"  T ", " TET", " IT ", "I   ", 'I',ModItemsMagiks.ironStick, 'E', Item.enderPearl,
    	'T',"ingotTin"));
    	MechroMagiksAPI.addMachineRecipe(new ItemStack(ModItemsMagiks.chestLinker), new Object[]
			{ "  IE"," ISI", "IRI ", "II  ", 'I',ModItemsMagiks.ironPlate, 'R',Item.redstone,
			'E',ModItemsMagiks.wirelessReciever, 'S',Block.stoneButton });
    	MechroMagiksAPI.addMachineRecipe(new ItemStack(ModBlocksMagiks.linkingChest, 1), new Object[]
    		{ "BBBB","BRCB","BCRB","BBBB", 'B', ModBlocksMagiks.manceryBlock, 'R',Item.redstone,
    			'C',Block.chest });
    	MechroMagiksAPI.addMachineRecipe(new MancerShapedOreRecipes(new ItemStack(ModBlocksMagiks.smallCoil), true, " CAC",
    	" ICI", " SiS", "BBBB", 'C',"ingotCopper", 'I', ModItemsMagiks.ironPlate, 'i',Block.blockIron, 
    	'S',ModItemsMagiks.ingotStamatic, 'B',ModBlocksMagiks.manceryBrick, 'A',ModItemsMagiks.advReciever));
    	MechroMagiksAPI.addMachineRecipe(new MancerShapedOreRecipes(new ItemStack(ModBlocksMagiks.smallCoil), true, "CAC ",
    	"ICI ", "SiS ", "BBBB", 'C',"ingotCopper", 'I', ModItemsMagiks.ironPlate, 'i',Block.blockIron, 
    	'S',ModItemsMagiks.ingotStamatic, 'B',ModBlocksMagiks.manceryBrick, 'A',ModItemsMagiks.advReciever));
    	MechroMagiksAPI.addMachineRecipe(new MancerShapedOreRecipes(new ItemStack(ModBlocksMagiks.oreCrusher),
    	true, "SIIS","PFFP","CTTC","JJJJ", 'S',Block.stone, 'P',Block.pistonBase, 'F', Item.flint, 
    	'J',Item.ingotIron, 'I',ModItemsMagiks.ironPlate, 'C',"copperWire", 'T',"ingotTin"));
    }
    
    @SuppressWarnings("unchecked")
    public static void register4x4Recipes()
    {
    	BasicRecipeManager.getInstance().recipes.addAll(CraftingManager.getInstance().getRecipeList());
    	MechroMagiksAPI.add4x4Recipe(new ItemStack(ModItemsMagiks.emeraldAmulet),
    	new Object[]{" GGG", "G  G", "GN G", "EGG ", 'G',Item.ingotGold, 'N',ModItemsMagiks.neoniteGem,
    	'E',Item.emerald });
    	MechroMagiksAPI.add4x4Recipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.advReciever, 1),
    	true, " GSE", "GSWG", "SGS ", "I   ", 'G',Item.ingotGold, 'S',"ingotSilver", 'E',Item.enderPearl,
    	'I', ModItemsMagiks.ironStick, 'W',ModItemsMagiks.wirelessReciever));
    	MechroMagiksAPI.add4x4Recipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.diggersWandS),
    	true, "  GD", "  IG", "SiS ","i   ", 'D',Item.pickaxeStone, 'G',Item.ingotGold, 'I',ModItemsMagiks.ironBand,
    	'i',ModItemsMagiks.ironStick, 'S',ModItemsMagiks.ingotStamatic));
    	MechroMagiksAPI.add4x4Recipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.diggersWandS),
    	true, "  GD", "  IG", "SiS ","i   ", 'D',Item.pickaxeIron, 'G',"ingotTin", 
    	'I',ModItemsMagiks.diggersWandI, 'i',ModItemsMagiks.ironStick, 'S',ModItemsMagiks.ingotStamatic));
    	MechroMagiksAPI.add4x4Recipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.diggersWandS),
    	true, "  GD", "  IG", "SiS ","i   ", 'D',Item.pickaxeDiamond, 'G',"ingotSilver", 
    	'I',ModItemsMagiks.diggersWandD, 'i',ModItemsMagiks.ironStick, 'S',ModItemsMagiks.ingotVesi));
    }
    
    public static void registerBlueprintRecipes()
    {
    	MechroMagiksAPI.addBluePrintRecipe(new MancerShapedOreRecipes(new ItemStack(ModItemsMagiks.bluePrint, 1, 2),
    	true, "SIIS","PFFP","CTTC","JJJJ", 'S',Block.stone, 'P',Block.pistonBase, 'F', Item.flint, 
    	'J',Item.ingotIron, 'I',ModItemsMagiks.ironPlate, 'C',"copperWire", 'T',"ingotTin"));
    }
    
    public static void stairRecipes(Block ouput, Block input)
    {
    	GameRegistry.addRecipe(new ItemStack(ouput, 4), new Object[]
    	{ "O  ","OO ","OOO", 'O',input });
    	GameRegistry.addRecipe(new ItemStack(ouput, 4), new Object[]
    		{ "  O"," OO","OOO", 'O',input });
    }
    
    public static void addDoubleOres()
    {
    	MagiksArrays.doubledOres.add(new ItemStack(Block.sand));
    	MagiksArrays.doubledOres.add(new ItemStack(Item.clay));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreGold));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreIron));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreDiamond));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreEmerald));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreRedstone));
    	MagiksArrays.doubledOres.add(new ItemStack(Block.oreLapis));
    	
    	MagiksArrays.doubledOres.add(new ItemStack(ModBlocksMagiks.igniousOre));
    	MagiksArrays.doubledOres.add(new ItemStack(ModBlocksMagiks.stamaticOre));
    	
    	for(ItemStack copper : OreDictionary.getOres("oreCopper"))
    	{
    		MagiksArrays.doubledOres.add(copper);
    	}
    	for(ItemStack tin : OreDictionary.getOres("oreTin"))
    	{
    		MagiksArrays.doubledOres.add(tin);
    	}
    	for(ItemStack silver : OreDictionary.getOres("oreSilver"))
    	{
    		MagiksArrays.doubledOres.add(silver);
    	}
    	for(ItemStack lead : OreDictionary.getOres("oreLead"))
    	{
    		MagiksArrays.doubledOres.add(lead);
    	}
    	for(ItemStack oreZinc : OreDictionary.getOres("oreZinc"))
    	{
    		MagiksArrays.doubledOres.add(oreZinc);
    	}
    	for(ItemStack oreAluminum : OreDictionary.getOres("oreAluminum"))
    	{
    		MagiksArrays.doubledOres.add(oreAluminum);
    	}
    }
    
    public static void ic2Compatibility()
    {
    	String ic2ID = "IC2";
    	String itemClassLoc = "ic2.core.Ic2Items";
    	if(ModLoaded.isModInstalled(ic2ID))
    	{
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
    	    advRecipes.addRecipe(remote, new Object[] { " c ", "GCG", "TTT", 'c', "copperWire", 'G', Item.lightStoneDust, 
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
    	    'c', "copperWire", 'G', Item.lightStoneDust, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(odScanner, new Object[] { " G ", "CBC", "ccc", 'B', chargedReBattery, 
    	    'c', "copperWire", 'G', Item.lightStoneDust, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricWrench, new Object[] { "  W", " C ", "B  ", 'W', wrench, 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricWrench, new Object[] { "  W", " C ", "B  ", 'W', wrench, 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricTreetap, new Object[] { "  W", " C ", "B  ", 'W', treetap, 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricTreetap, new Object[] { "  W", " C ", "B  ", 'W', treetap, 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(ecMeter, new Object[] { " G ", "cCc", "c c", 'G', Item.lightStoneDust, 
    	    'c', "copperWire", 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricHoe, new Object[] { "II ", " C ", " B ", 'I', "ingotRefinedIron", 
    	    'B', reBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(electricHoe, new Object[] { "II ", " C ", " B ", 'I', "ingotRefinedIron", 
    	    'B', chargedReBattery, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(advancedCircuit, new Object[] { "RGR", "LCL", "RGR", 'L', new ItemStack(Item.dyePowder, 1, 4), 
    	    'G', Item.lightStoneDust, 'R', Item.redstone, 'C', "electronicCircuit" });
    	    advRecipes.addRecipe(advancedCircuit, new Object[] { "RLR", "GCG", "RLR", 'L', new ItemStack(Item.dyePowder, 1, 4), 
    	    'G', Item.lightStoneDust, 'R', Item.redstone, 'C', "electronicCircuit" });
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
