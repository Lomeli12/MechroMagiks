package net.lomeli.magiks.items.science;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.magiks.items.ItemGeneric;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemMachineBluePrints extends ItemGeneric
{
	public Icon[] iconArray = new Icon[7];
	
	public ItemMachineBluePrints(int par1)
    {
	    super(par1, "blueprint", false);
	    this.setHasSubtypes(true);
	    this.setMaxDamage(0);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		for(int i = 0; i < 7; i++)
		{
			this.iconArray[i] = iconRegister.registerIcon(Strings.MOD_ID + ":blueprint" );
		}
	}
	
	@Override
    public int getMetadata(int par1)
    {
        return par1;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int i)
	{
		return this.iconArray[i];
	}
	
	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list)
	{
		for(int i = 0; i < 7; i++)
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
