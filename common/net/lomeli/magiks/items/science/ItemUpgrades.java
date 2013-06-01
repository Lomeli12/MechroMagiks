package net.lomeli.magiks.items.science;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.lomeli.lomlib.util.ToolTipUtil;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUpgrades extends Item
{
	private String itemTexture, infoText;

    public ItemUpgrades(int par1, String Texture)
    {
        super(par1);
        itemTexture = Texture;
        this.setMaxStackSize(2);
        this.setCreativeTab(Magiks.modTab);
    }
    
    public Item setDescription(String description)
    {
    	infoText = description;
    	return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Strings.MOD_ID + ":" + itemTexture);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
    	if(ToolTipUtil.doAdditionalInfo())
		{
    		infoList.add(infoText);
		}
    	else
			infoList.add(ToolTipUtil.additionalInfoInstructions("2"));
    }
}
