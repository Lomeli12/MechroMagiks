package net.lomeli.magiks.api.cafting;

import java.util.Comparator;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

@SuppressWarnings("rawtypes")
public class MachineRecipeSorter implements Comparator
{
    final MachineRecipeManager recipeSorter;

    MachineRecipeSorter(MachineRecipeManager craftingManager)
    {
        recipeSorter = craftingManager;
    }

    public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe)
    {
    	return par1IRecipe instanceof MancerShapelessRecipes && 
    		par2IRecipe instanceof MancerShapedRecipes ? 1 : 
    		(par2IRecipe instanceof ShapelessRecipes && par1IRecipe instanceof ShapedRecipes ? -1 : 
    		(par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : 
    		(par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
    }

    @Override
    public int compare(Object par1Obj, Object par2Obj)
    {
        return this.compareRecipes((IRecipe) par1Obj, (IRecipe) par2Obj);
    }

}
