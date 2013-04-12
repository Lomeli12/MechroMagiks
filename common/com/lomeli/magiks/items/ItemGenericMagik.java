package com.lomeli.magiks.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.lomeli.magiks.core.helper.MagikChargeHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenericMagik extends ItemGeneric
{
    public ItemGenericMagik(int par1, String Texture, boolean special, int magik)
    {
        super(par1, Texture, special);
        this.setMaxDamage(magik + 1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add(max - itemStack.getItemDamage() + "/" + max);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        MagikChargeHelper.recharge(player, itemStack);
        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate(ItemStack itemStack, World world, Entity entity,
            int par4, boolean par5)
    {
        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.inventory.hasItemStack(itemStack))
                {
                    if (itemStack.getItemDamage() < itemStack.getMaxDamage() - 1)
                    {

                    } else
                    {
                        MagikChargeHelper.forcedRecharge(player, itemStack);
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
