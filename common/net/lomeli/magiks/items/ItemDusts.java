package net.lomeli.magiks.items;

import java.util.List;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemDusts extends ItemGeneric
{
	public Icon[] iconArray = new Icon[9];
	public String[] itemNames = { "iron", "gold", "stamatic", "ignious", 
		"copper", "tin", "silver", "lead", "vesi" };

	public ItemDusts(int par1)
    {
	    super(par1, "dust", false);
	    this.setHasSubtypes(true);
	    this.setMaxDamage(0);
	    this.setCreativeTab(Magiks.modTab);
    }
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		for(int i = 0; i < 9; i++)
		{
			this.iconArray[i] = iconRegister.registerIcon(ModStrings.MOD_ID + ":dusts/" +  itemNames[i] + "dust");
		}
	}
	
	@Override
    public int getMetadata(int par1)
    {
        return par1;
    }
	
	@Override
    public Icon getIconFromDamage(int i)
	{
		return this.iconArray[i];
	}
	
	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(int itemID, CreativeTabs tabs, List list)
	{
		for(int i = 0; i < 9; i++)
		{
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

}
