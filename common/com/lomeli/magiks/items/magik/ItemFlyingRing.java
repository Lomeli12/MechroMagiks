package com.lomeli.magiks.items.magik;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.items.ModItemsMagiks;
import com.lomeli.magiks.core.helper.ItemHelper;
import com.lomeli.magiks.lib.Strings;

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

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.epic;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        iconIndex = iconRegister
                .registerIcon(Strings.modID + ":" + itemTexture);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add(max - itemStack.getItemDamage() + "/" + max);
    }

    public static void recharge(EntityPlayer player, ItemStack itemStack)
    {
        if (player != null)
        {
            if (itemStack.getItemDamage() != 0)
            {
                if (player.inventory.hasItem(Item.lightStoneDust.itemID))
                {
                    player.inventory
                            .consumeInventoryItem(Item.lightStoneDust.itemID);
                    itemStack.setItemDamage(itemStack.getItemDamage() - 100);
                } else if (player.inventory.hasItem(Item.coal.itemID))
                {
                    player.inventory.consumeInventoryItem(Item.coal.itemID);
                    itemStack.setItemDamage(itemStack.getItemDamage() - 50);
                } else if (player.inventory.hasItem(Item.redstone.itemID))
                {
                    player.inventory.consumeInventoryItem(Item.redstone.itemID);
                    itemStack.setItemDamage(itemStack.getItemDamage() - 25);
                } else
                {
                    player.capabilities.allowFlying = false;
                    player.capabilities.isFlying = false;
                }
            } else
            {
            }
        } else
        {
        }
    }

    public static void forcedRecharge(EntityPlayer player, ItemStack itemStack)
    {
        if (player != null)
        {
            if (itemStack.getItemDamage() > 0)
            {
                if (player.inventory.hasItem(Item.lightStoneDust.itemID))
                {
                    while (itemStack.getItemDamage() > 0)
                    {
                        player.inventory
                                .consumeInventoryItem(Item.lightStoneDust.itemID);
                        itemStack
                                .setItemDamage(itemStack.getItemDamage() - 100);
                    }
                } else if (player.inventory.hasItem(Item.coal.itemID))
                {
                    while (itemStack.getItemDamage() > 0)
                    {
                        player.inventory.consumeInventoryItem(Item.coal.itemID);
                        itemStack.setItemDamage(itemStack.getItemDamage() - 50);
                    }
                } else if (player.inventory.hasItem(Item.redstone.itemID))
                {
                    while (itemStack.getItemDamage() > 0)
                    {
                        player.inventory
                                .consumeInventoryItem(Item.redstone.itemID);
                        itemStack.setItemDamage(itemStack.getItemDamage() - 25);
                    }
                } else
                {
                }
            } else
            {
            }
        } else
        {
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        recharge(player, itemStack);
        // itemStack.setItemDamage(itemStack.getMaxDamage() - 1);
        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity,
            int par4, boolean par5)
    {
        ItemHelper helper = new ItemHelper();
        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.capabilities.isCreativeMode == false)
                {
                    if (player.inventory.hasItemStack(itemStack))
                    {
                        if (itemStack.getItemDamage() < itemStack
                                .getMaxDamage() - 1)
                        {
                            ItemStack amulet = new ItemStack(ModItemsMagiks.emeraldAmulet);
                            player.capabilities.allowFlying = true;
                            if (player.capabilities.isFlying == true)
                            { 
                                if(player.inventory.hasItemStack(amulet))
                                {
                                    helper.getItem(player, 
                                            helper.getSlotContainingItem(amulet.itemID, 
                                                    player.inventory.mainInventory)).damageItem(1, player);
                                }
                                else
                                {
                                    itemStack.damageItem(1, player);
                                }
                            } else if (player.fallDistance >= 4F)
                            {
                                if(player.inventory.hasItemStack(amulet))
                                {
                                    helper.getItem(player, 
                                            helper.getSlotContainingItem(amulet.itemID, 
                                                    player.inventory.mainInventory)).damageItem(1, player);
                                }
                                else
                                {
                                    itemStack.damageItem(1, player);
                                }
                            }
                        } else
                        {
                            forcedRecharge(player, itemStack);
                            player.capabilities.allowFlying = false;
                            player.capabilities.isFlying = false;
                        }
                    } else
                    {
                        player.capabilities.allowFlying = false;
                        player.capabilities.isFlying = false;
                    }
                } else
                {
                    player.capabilities.allowFlying = true;
                }
            }
        }
    }

}
