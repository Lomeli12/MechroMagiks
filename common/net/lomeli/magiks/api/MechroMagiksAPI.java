package net.lomeli.magiks.api;

import net.lomeli.magiks.api.crafting.BasicRecipes;
import net.lomeli.magiks.api.crafting.BluePrintRecipes;
import net.lomeli.magiks.api.crafting.MachineRecipes;
import net.lomeli.magiks.api.machines.OreCrusherManager;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

@SuppressWarnings("unchecked")
public class MechroMagiksAPI 
{
	public static void addCrushableOre(int itemID, ItemStack output)
	{
		OreCrusherManager.getInstance().addCrushRecipe(itemID, output);
	}
	
	public static void addCrushableOre(Object input, Object output, int outSize)
	{
		int inputID = 0;
		if(input instanceof Block)
			inputID = ((Block) input).blockID;
		else if(input instanceof Item)
			inputID = ((Item) input).itemID;
		
		ItemStack outStack = null;
		
		if(output instanceof Block)
			outStack = new ItemStack((Block) output, outSize);
		else if(output instanceof Item)
			outStack = new ItemStack((Item) output, outSize);
		
		OreCrusherManager.getInstance().addCrushRecipe(inputID, outStack);
	}
	
	public static void addCrushableOre(Object input, int inMeta, Object output, int outMeta, int outSize)
	{
		int inputID = 0;
		if(input instanceof Block)
			inputID = ((Block) input).blockID;
		else if(input instanceof Item)
			inputID = ((Item) input).itemID;
		
		ItemStack outStack = null;
		
		if(output instanceof Block)
			outStack = new ItemStack((Block) output, outSize, outMeta);
		else if(output instanceof Item)
			outStack = new ItemStack((Item) output, outSize, outMeta);
		
		OreCrusherManager.getInstance().addCrushRecipe(inputID, outStack, inMeta);
	}
	
	public static void addCrushableOre(int itemID, int metadata, ItemStack output)
	{
		OreCrusherManager.getInstance().addCrushRecipe(itemID, output, metadata);
	}
	
	public static void add4x4Recipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		BasicRecipes.addRecipe(par1ItemStack, par2ArrayOfObj);
	}
	
	public static void add4x4Recipe(IRecipe recipe)
	{
		BasicRecipes.getRecipeList().add(recipe);
	}
	
	public static void add4x4ShapelessRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		BasicRecipes.addShapelessRecipe(par1ItemStack, par2ArrayOfObj);
	}
	
	public static void add4x4ShapelessRecipe(IRecipe recipe)
	{
		BasicRecipes.getRecipeList().add(recipe);
	}
	
	/**
	 * Adds new 4x4 recipe to the WorkTable.
	 * Recipes added will also require a electronic circuit
	 * in the appropriate slot.
	 * 
	 * @param par1ItemStack Output item
	 * @param par2ArrayOfObj Recipe
	 * @author Lomeli12
	 */
	public static void addMachineRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		MachineRecipes.addRecipe(par1ItemStack, par2ArrayOfObj);
	}
	
	public static void addMachineRecipe(IRecipe recipe)
	{
		MachineRecipes.getRecipeList().add(recipe);
	}
	
	/**
	 * Adds a shapeless recipe to the Worktable
	 * this recipe can have up to 16 items and will also
	 * need an electronic circuit in the appropriate slot
	 * 
	 * @param par1ItemStack Output item
	 * @param par2ArrayOfObj Require items
	 * @author Lomeli12
	 */
	public static void addMachineShapelessRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		MachineRecipes.addRecipe(par1ItemStack, par2ArrayOfObj);
	}
	
	public static void addMachineShapelessRecipe(IRecipe recipe)
	{
		MachineRecipes.getRecipeList().add(recipe);
	}
	
	/**
	 * Adds a recipe for a new BluePrint. You will also need to add a
	 * output for the machine builder.
	 * 
	 * @param par1ItemStack Output BluePrint
	 * @param par2ArrayOfObj Recipe
	 * @author Lomeli12
	 */
	public static void addBluePrintRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
	{
		BluePrintRecipes.addRecipe(par1ItemStack, par2ArrayOfObj);
	}
	
    public static void addBluePrintRecipe(IRecipe recipe)
    {
		BluePrintRecipes.getRecipeList().add(recipe);
    }
	/**
	 * Adds an output for when the blueprint is put into a machine builder 
	 * @param blueprint The blueprint
	 * @param output Whatever the machine builder will create
	 * @author Lomeli12
	 */
	public static void addBluePrintMachine(ItemStack blueprint, ItemStack output)
	{
		
	}
}
