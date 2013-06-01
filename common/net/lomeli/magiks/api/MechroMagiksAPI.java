package net.lomeli.magiks.api;

import net.lomeli.magiks.api.crafting.BasicRecipes;
import net.lomeli.magiks.api.crafting.BluePrintRecipes;
import net.lomeli.magiks.api.crafting.MachineRecipes;
import net.lomeli.magiks.api.machines.OreCrusherManager;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

@SuppressWarnings("unchecked")
public class MechroMagiksAPI 
{
	public static void addCrushableOre(int itemID, ItemStack output)
	{
		OreCrusherManager.getInstance().addCrushRecipe(itemID, output);
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
