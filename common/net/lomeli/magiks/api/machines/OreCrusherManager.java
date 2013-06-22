package net.lomeli.magiks.api.machines;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class OreCrusherManager
{   
    private static final OreCrusherManager instance = new OreCrusherManager();
    
    @SuppressWarnings("rawtypes")
    private Map crushableOre = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaCrushableOre = new HashMap<List<Integer>, ItemStack>();
    
    public static final OreCrusherManager getInstance()
    {
    	return instance;
    }
    
    private OreCrusherManager() { }
    
    @SuppressWarnings("unchecked")
    public void addCrushRecipe(int itemID, ItemStack itemStack)
    {
    	this.crushableOre.put(Integer.valueOf(itemID), itemStack);
    }
    
    public void addCrushRecipe(int itemID, ItemStack itemStack, int metadata)
    {
    	this.metaCrushableOre.put(Arrays.asList(itemID, metadata), itemStack);
    }
    
    public ItemStack getCrushResult(ItemStack item)
    {
    	if(item == null)
    		return null;
    	
    	ItemStack ret = metaCrushableOre.get(Arrays.asList(item.itemID, item.getItemDamage()));
    	if(ret != null)
    		return ret;
    	
    	return (ItemStack)crushableOre.get(Integer.valueOf(item.itemID));
    }
    
    @SuppressWarnings("rawtypes")
    public Map getCrushableList()
    {
    	return this.crushableOre;
    }
}
