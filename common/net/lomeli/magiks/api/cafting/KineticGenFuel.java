package net.lomeli.magiks.api.cafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings("rawtypes")
public class KineticGenFuel 
{
	private static final KineticGenFuel instance = new KineticGenFuel();

	
	private Map fuelList = new HashMap();
    private Map mistList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaFuelList = new HashMap<List<Integer>, ItemStack>();
    private HashMap<List<Integer>, Float> metaMistList = new HashMap<List<Integer>, Float>();
    
    public static final KineticGenFuel fuel()
    {
    	return instance;
    }
    
    private KineticGenFuel()
    {
    	this.addFuel(0, new ItemStack(Block.tnt), 75);
    	this.addFuel(1, new ItemStack(Item.gunpowder), 25);
    	
    }
    
    @SuppressWarnings("unchecked")
	public void addFuel(int par1, ItemStack itemStack, float mist)
    {
    	this.fuelList.put(Integer.valueOf(par1), itemStack);
    	this.mistList.put(Integer.valueOf(itemStack.itemID), Float.valueOf(mist));
    }
    
    public void addFuel(int itemID, int metadata, ItemStack itemstack, float mist)
    {
    	this.metaFuelList.put(Arrays.asList(itemID, metadata), itemstack);
    	this.metaMistList.put(Arrays.asList(itemID, metadata), mist);
    }
    
	public Map getFuelList()
    {
    	return fuelList;
    }
    
    public boolean isValidItem(ItemStack item)
    {
    	if(fuelList.containsKey(Integer.valueOf(item.itemID)))
    		return true;
    	else
    		return false;
    }
    
    public ItemStack getItem(ItemStack item)
    {
    	if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaFuelList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)fuelList.get(Integer.valueOf(item.itemID));
    }
    
    public float getMist(ItemStack item)
    {
    	if (item == null || item.getItem() == null)
        {
            return 0;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && metaMistList.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
        {
            ret = metaMistList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        }
        if (ret < 0 && mistList.containsKey(item.itemID))
        {
            ret = ((Float)mistList.get(item.itemID)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }
    
    public Map<List<Integer>, ItemStack> getMetaFuelList()
    {
        return metaFuelList;
    }
}
