package net.lomeli.magiks.recipes;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.api.MechroMagiksAPI;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.blocks.ModBlocksMagiks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagiksRecipes 
{
	public static final int WILDCARD_DAMAGE_VALUE = Short.MAX_VALUE;
	
	public static void registerRecipes()
	{
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
                    ModItemsMagiks.darkMatter, 'P',
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
        
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.copperWire, 4), new Object[]
        		{" CS", "CIC", "SC ", 'S',Item.silk, 'C',ModItemsMagiks.ingotCopper,
        		'I',ModItemsMagiks.ironStick});
        GameRegistry.addRecipe(new ItemStack(ModItemsMagiks.electroicCircuit, 1), 
        		new Object[]{ "DIG","CPC","GID", 'G',Item.goldNugget, 'I',ModItemsMagiks.ironPlate,
        		'C',ModItemsMagiks.copperWire, 'P', ModItemsMagiks.ingotSilver, 
        		'D', new ItemStack(Item.dyePowder, 1, 2)});
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
    }
    
    public static void registerOreCrusherRecipes()
    {
    	MechroMagiksAPI.addCrushableOre(Block.oreIron.blockID, new ItemStack(Item.diamond, 2));
    }

    public static void registerMachineRecipes()
    {
    	MechroMagiksAPI.addMachineRecipe(new ItemStack(ModItemsMagiks.wirelessReciever), new Object[]
			{"  T ", " TET", " IT ", "I   ", 'I',ModItemsMagiks.ironStick, 'E', Item.enderPearl,
			'T',ModItemsMagiks.ingotTin});
    	MechroMagiksAPI.addMachineRecipe(new ItemStack(ModItemsMagiks.chestLinker), new Object[]
			{ "  IE"," ISI", "IRI ", "II  ", 'I',ModItemsMagiks.ironPlate, 'R',Item.redstone,
			'E',ModItemsMagiks.wirelessReciever, 'S',Block.stoneButton });
    	MechroMagiksAPI.addMachineRecipe(new ItemStack(ModBlocksMagiks.linkingChest, 1), new Object[]
    		{ "BBBB","BRCB","BCRB","BBBB", 'B', ModBlocksMagiks.manceryBlock, 'R',Item.redstone,
    			'C',Block.chest });
    	
    }
    
    public static void register4x4Recipes()
    {
    	MechroMagiksAPI.add4x4Recipe(new ItemStack(ModItemsMagiks.emeraldAmulet), new Object[]
    		{ " GGG", "G  G", "GN G", "EGG ", 'G',Item.ingotGold, 'N',ModItemsMagiks.neoniteGem,
    		'E',Item.emerald });
    }
    
    public static void registerBlueprintRecipes()
    {
    	MechroMagiksAPI.addBluePrintRecipe(new ItemStack(ModItemsMagiks.bluePrint, 1, 2), new Object[]
    		{ "SIIS","PFFP","CTTC","JJJJ", 'S',Block.stone, 'P',Block.pistonBase, 'F', Item.flint, 
    		'J',Item.ingotIron, 'I',ModItemsMagiks.ironPlate, 
    		'C',ModItemsMagiks.copperWire, 'T',ModItemsMagiks.ingotTin});
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
}
