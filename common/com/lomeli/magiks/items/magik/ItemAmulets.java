package com.lomeli.magiks.items.magik;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.lomeli.magiks.core.helper.NBTHelper;
import com.lomeli.magiks.items.ItemGeneric;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAmulets extends ItemGeneric
{

    @SuppressWarnings("unused")
    private String owner;

    public ItemAmulets(int par1, String Texture, boolean special, int magik)
    {
        super(par1, Texture, special);
        this.setMaxDamage(magik);
        this.setMaxStackSize(1);
        owner = "";
    }

    private void setOwner(ItemStack itemStack, EntityPlayer player)
    {
        NBTHelper.setString(itemStack, "owner", player.username);
        NBTHelper.setBoolean(itemStack, "ownerset", true);
    }

    private void removeOwner(ItemStack itemStack)
    {
        NBTHelper.setString(itemStack, "owner", "");
        NBTHelper.setBoolean(itemStack, "ownerset", false);
    }

    public String getOwner(ItemStack itemStack)
    {
        return NBTHelper.getString(itemStack, "owner");
    }

    public boolean isOwnerSet(ItemStack itemStack)
    {
        return NBTHelper.getBoolean(itemStack, "ownerset");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        if (isOwnerSet(itemStack) && this.getOwner(itemStack) != ""
                || isOwnerSet(itemStack) || this.getOwner(itemStack) != "")
        {
            if (player.username.toLowerCase() == this.getOwner(itemStack))
            {
                removeOwner(itemStack);
            } else
            {
                player.setFire(10);
            }
        } else
        {
            setOwner(itemStack, player);
        }
        return itemStack;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        infoList.add("Mist Level: "
                + (itemStack.getMaxDamage() - itemStack.getItemDamage()) + "/"
                + itemStack.getMaxDamage());
        if (isOwnerSet(itemStack))
        {
            infoList.add("Owner: " + this.getOwner(itemStack));
        }

    }
}
