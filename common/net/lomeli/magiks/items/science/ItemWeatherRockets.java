package net.lomeli.magiks.items.science;

import java.util.List;

import net.lomeli.magiks.items.ItemGeneric;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemWeatherRockets extends ItemGeneric
{
	public Icon[] iconArray = new Icon[6];
	public String itemTexture;
	
	public ItemWeatherRockets(int par1, String Texture) 
	{
		super(par1, Texture, false);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.itemTexture = Texture;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		for(int i = 0; i < 6; i++)
		{
			this.iconArray[i] = iconRegister.registerIcon(Strings.MOD_ID + ":" + itemTexture + "_" + i);
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
		for(int i = 0; i < 6; i++)
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
