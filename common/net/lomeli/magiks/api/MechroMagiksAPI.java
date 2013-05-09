package net.lomeli.magiks.api;

import net.lomeli.magiks.api.cafting.BluePrintRecipes;
import net.lomeli.magiks.api.cafting.MachineRecipes;
import net.minecraft.item.ItemStack;

public class MechroMagiksAPI 
{
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
