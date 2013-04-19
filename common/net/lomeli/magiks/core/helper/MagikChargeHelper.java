package net.lomeli.magiks.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/*
 * This interface is used when something needs to be able to gather magik, but doesn't have
 * any special abilities
 */

public class MagikChargeHelper
{
    public static int getMagikCharge(ItemStack itemStack)
    {
        int max = itemStack.getMaxDamage() - 1;
        return max - itemStack.getItemDamage();
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
                }
            }
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
}
