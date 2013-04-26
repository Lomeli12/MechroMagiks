package net.lomeli.magiks.recipes;

import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.api.cafting.MagikCraftingManager;
import net.lomeli.magiks.blocks.ModBlocksMagiks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MagiksRecipes 
{
	public static final int WILDCARD_DAMAGE_VALUE = Short.MAX_VALUE;
	
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
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.obsidianStairs, 4), new Object[]
			{
				"O  ","OO ","OOO", 'O',Block.obsidian
			});
		GameRegistry.addRecipe(new ItemStack(ModBlocksMagiks.obsidianStairs, 4), new Object[]
			{
				"  O"," OO","OOO", 'O',Block.obsidian
			});
	}
	
    public static void registerItemRecipes()
    {
        GameRegistry
                .addRecipe(new ItemStack(ModItemsMagiks.enchantedDiamond, 1), new Object[] {
                        "GEG", "EDE", "RER", 'G', Item.lightStoneDust, 'R',
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
                {
                    " CB","CSC","BC ", 'S',Item.stick, 'C',Item.coal,
                    'B',(new ItemStack(Item.dyePowder, 1, 15))
                });
    }

    public static void registerFurnaceRecipes()
    {
        GameRegistry.addSmelting(ModBlocksMagiks.stamaticOre.blockID,
                new ItemStack(ModItemsMagiks.ingotStamatic), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.igniousOre.blockID,
                new ItemStack(ModItemsMagiks.ingotIgnious), 10);
        GameRegistry.addSmelting(ModBlocksMagiks.netherIgnious.blockID, 
        		new ItemStack(ModItemsMagiks.ingotIgnious), 10);
        GameRegistry.addSmelting(ModBlocksMagiks.copperOre.blockID,
                new ItemStack(ModItemsMagiks.ingotCopper), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.tinOre.blockID,
                new ItemStack(ModItemsMagiks.ingotTin), 5);
        GameRegistry.addSmelting(ModBlocksMagiks.silverOre.blockID, 
        		new ItemStack(ModItemsMagiks.ingotSilver), 5);
    }

    public static void registerMistRecipes()
    {
    	MagikCraftingManager.getInstance().addRecipe(new ItemStack(Item.goldenCarrot),
    			new Object[] { "WW", 'W',Item.appleRed});
    }
}
