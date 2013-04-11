package com.lomeli.magiks.items.magik;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.lomeli.magiks.items.ItemGeneric;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPirasVarinha extends ItemGeneric
{

    public ItemPirasVarinha(int par1, String Texture, boolean special)
    {
        super(par1, Texture, special);
        this.setMaxDamage(751);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add("Mist Level: " + (max - itemStack.getItemDamage()) + "/" + max);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {

        if (itemStack.getItemDamage() <= itemStack.getMaxDamage() - 11)
        {
            Vec3 look = player.getLookVec();
            EntityLargeFireball entitylargefireball = new EntityLargeFireball(
                    world, player, 0, 0, 0);
            entitylargefireball.field_92057_e = 3;
            entitylargefireball.setSprinting(true);
            entitylargefireball.setPosition(player.posX + look.xCoord * 4,
                    player.posY + look.yCoord + 1, player.posZ + look.zCoord * 4);
            entitylargefireball.accelerationX = look.xCoord * 0.3;
            entitylargefireball.accelerationY = look.yCoord * 0.3;
            entitylargefireball.accelerationZ = look.zCoord * 0.3;
            world.spawnEntityInWorld(entitylargefireball);
            itemStack.damageItem(5, player);
        }
        else
        {
            player.sendChatToPlayer("Not enough Mist!");
        }
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
                if (itemStack.getItemDamage() <= itemStack.getMaxDamage() - 1)
                {
                    player.addPotionEffect(new PotionEffect(12, -1, 10));
                }
            }
        }else{}

    }

}
