package net.lomeli.magiks.items.magik;

import java.util.List;

import net.lomeli.lomlib.util.NBTUtil;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.core.helper.RechargeHelper;
import net.lomeli.magiks.lib.Strings;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFlyingRing extends Item
{
    private String itemTexture;

    // private float flyspeed;
    public ItemFlyingRing(int par1, String Texture)
    {
        super(par1);
        itemTexture = Texture;
        this.setCreativeTab(Magiks.modTab);
        this.setMaxDamage(4001);
        this.setMaxStackSize(1);
        // this.flyspeed = 0.05F;
    }
    
    public static boolean isOwnerSet(ItemStack itemStack)
    {
        return NBTUtil.getBoolean(itemStack, "ownerset");
    }
    
    public static String getOwner(ItemStack itemStack)
    {
    	return NBTUtil.getString(itemStack, "owner");
    }
    
    public static boolean isPlayerOwner(EntityPlayer player, ItemStack itemStack)
    {
    	if (isOwnerSet(itemStack) && player.username.equals(getOwner(itemStack)))
    		return true;
    	else if(!isOwnerSet(itemStack))
    		return true;
    	else
    		return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.epic;
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
        int max = itemStack.getMaxDamage() - 1;
        infoList.add("Mist Level: " + (max - itemStack.getItemDamage()) + "/"
                + max);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        RechargeHelper.recharge(player, itemStack);
        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity,
            int par4, boolean par5)
    {
        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.capabilities.isCreativeMode == false)
                {
                    if (player.inventory.hasItemStack(itemStack))
                    {
                        if (itemStack.getItemDamage() < itemStack.getMaxDamage())
                        {
                        	player.capabilities.allowFlying = true;
                        	for(ItemStack mistItem : player.inventory.mainInventory)
                        	{
                        		if(mistItem != null)
                        		{
                        			if(mistItem.getItem() instanceof ItemAmulets)
                        			{
                        				if(isPlayerOwner(player, mistItem))
                        				{
                        					if(mistItem.getItemDamage() < mistItem.getMaxDamage() 
                        						&& itemStack.getItemDamage() > 0)
                        					{
                        						mistItem.damageItem(1, player);
                        						itemStack.damageItem(-1, player);
                        					}
                        				}
                        			}
                        		}
                        	}
                        	if(player.capabilities.isFlying)
                        		itemStack.damageItem(1, player);
                        	else if(player.fallDistance >= 4F)
                        		itemStack.damageItem((int)(player.fallDistance/4F), player);
                        	else{}
                        } 
                        else
                        {
                            RechargeHelper.forcedRecharge(player, itemStack);
                            player.capabilities.allowFlying = false;
                            player.capabilities.isFlying = false;
                            player.capabilities.setPlayerWalkSpeed(0.1F);
                        	player.stepHeight = 0.5F;
                        }
                    } 
                    else
                    {
                        player.capabilities.allowFlying = false;
                        player.capabilities.isFlying = false;
                        player.capabilities.setPlayerWalkSpeed(0.1F);
                    	player.stepHeight = 0.5F;
                    }
                } 
                else
                {
                	player.capabilities.allowFlying = true;
                	player.capabilities.setPlayerWalkSpeed(0.1F);
                	player.stepHeight = 0.5F;
                }
                
                if (player.inventory.hasItemStack(itemStack))
                {
                    if (itemStack.getItemDamage() < itemStack.getMaxDamage())
                    {
                    	player.capabilities.setPlayerWalkSpeed(0.15F);
                    	player.stepHeight = 1F;
                    }
                    else
                    {
                    	player.capabilities.setPlayerWalkSpeed(0.1F);
                    	player.stepHeight = 0.5F;
                    }
                }
                else
                {
                	player.capabilities.setPlayerWalkSpeed(0.1F);
                	player.stepHeight = 0.5F;
                }
            }
        }
    }

}
