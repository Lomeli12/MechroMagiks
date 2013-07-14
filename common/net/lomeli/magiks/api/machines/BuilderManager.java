package net.lomeli.magiks.api.machines;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class BuilderManager
{   
    private static final BuilderManager instance = new BuilderManager();
    
    @SuppressWarnings("rawtypes")
    private Map builderRecipes = new HashMap();
    private HashMap<List<Integer>, ItemStack> metabuilderRecipes= new HashMap<List<Integer>, ItemStack>();
    
    public static final BuilderManager getInstance()
    {
    	return instance;
    }
    
    private BuilderManager() { }
    
    @SuppressWarnings("unchecked")
    public void addCrushRecipe(int itemID, ItemStack itemStack)
    {
    	this.builderRecipes.put(Integer.valueOf(itemID), itemStack);
    }
    
    public void addCrushRecipe(int itemID, ItemStack itemStack, int metadata)
    {
    	this.metabuilderRecipes.put(Arrays.asList(itemID, metadata), itemStack);
    }
    
    public ItemStack getCrushResult(ItemStack item)
    {
    	if(item == null)
    		return null;
    	
    	ItemStack ret = metabuilderRecipes.get(Arrays.asList(item.itemID, item.getItemDamage()));
    	if(ret != null)
    		return ret;
    	
    	return (ItemStack)builderRecipes.get(Integer.valueOf(item.itemID));
    }
    
    @SuppressWarnings("rawtypes")
    public Map getCrushableList()
    {
    	return this.builderRecipes;
    }
}
