package net.lomeli.magiks.items.magik;

import java.util.List;

import net.lomeli.magiks.core.config.ConfigMod;
import net.lomeli.magiks.items.ItemGeneric;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemPirasVarinha extends ItemGeneric
{

    public ItemPirasVarinha(int par1, String Texture, boolean special)
    {
        super(par1, Texture, special);
        this.setMaxDamage(751);
        this.setMaxStackSize(1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add("Mist Level: " + (max - itemStack.getItemDamage()) + "/"
                + max);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (itemStack.getItemDamage() <= itemStack.getMaxDamage() - 11)
        {
            if (!ConfigMod.disablePiras)
            {
                Vec3 look = player.getLookVec();
                EntityLargeFireball entitylargefireball = new EntityLargeFireball(
                        world, player, 0, 0, 0);
                entitylargefireball.field_92057_e = 3;
                entitylargefireball.setSprinting(true);
                entitylargefireball.setPosition(player.posX + look.xCoord * 4,
                        player.posY + look.yCoord + 1, player.posZ
                                + look.zCoord * 4);
                entitylargefireball.accelerationX = look.xCoord * 0.3;
                entitylargefireball.accelerationY = look.yCoord * 0.3;
                entitylargefireball.accelerationZ = look.zCoord * 0.3;
                world.spawnEntityInWorld(entitylargefireball);
                itemStack.damageItem(5, player);
            }
        } else
        {
            player.addChatMessage("Not enough Mist!");
        }
        return true;
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
        } else
        {
        }

    }

}
