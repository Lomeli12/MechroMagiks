package com.lomeli.magiks.items.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.core.helper.NBTHelper;
import com.lomeli.magiks.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLevelingSword extends ItemSword
{
    private String itemTexture;
    public int level;

    public ItemLevelingSword(int par1, EnumToolMaterial par2EnumToolMaterial,
            String texture)
    {
        super(par1, par2EnumToolMaterial);
        this.setMaxDamage(1000);
        this.setCreativeTab(Magiks.modTab);
        itemTexture = texture;
        level = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Strings.modID + ":" + itemTexture);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world,
            EntityPlayer entityPlayer)
    {
        itemStack.damageItem(itemStack.getMaxDamage() - 1, entityPlayer);
        setLevel(itemStack, 0);
        level = getLevel(itemStack);
    }

    private int getLevel(ItemStack itemStack)
    {
        return NBTHelper.getInt(itemStack, "Level");
    }

    private void setLevel(ItemStack itemStack, int plus)
    {
        NBTHelper.setInteger(itemStack, "Level", level + plus);
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLiving entityLiving,
            EntityLiving player)
    {
        if (entityLiving != null)
        {
            itemStack.setItemDamage(itemStack.getItemDamage() - 10);
            if (itemStack.getItemDamage() == 0)
            {
                itemStack.setItemDamage(itemStack.getMaxDamage() - 1);
                setLevel(itemStack, 1);
            } else
            {
            }
        } else
        {
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onUpdate(ItemStack itemStack, World world, Entity entity,
            int par4, boolean par5)
    {
        level = getLevel(itemStack);
        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.inventory.currentItem == itemID)
                {
                    if (player.inventory.getCurrentItem().getItemDamage() == 0)
                    {
                        itemStack.setItemDamage(itemStack.getMaxDamage() - 1);
                        setLevel(itemStack, 1);
                    }
                }
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add("Level " + getLevel(itemStack) + " EXP: "
                + (max - itemStack.getItemDamage()) + "/" + max);
    }

}
