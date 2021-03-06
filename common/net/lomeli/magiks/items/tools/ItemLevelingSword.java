package net.lomeli.magiks.items.tools;

import java.util.List;

import net.lomeli.lomlib.util.NBTUtil;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLevelingSword extends ItemSword
{
    private String itemTexture;
    public int level = 0;

    public ItemLevelingSword(int par1, EnumToolMaterial par2EnumToolMaterial,
            String texture)
    {
        super(par1, par2EnumToolMaterial);
        this.setMaxDamage(500);
        this.setCreativeTab(Magiks.modTab);
        itemTexture = texture;
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(ModStrings.MOD_ID + ":" + itemTexture);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world,
            EntityPlayer entityPlayer)
    {
        itemStack.damageItem(itemStack.getMaxDamage() - 1, entityPlayer);
        setLevel(itemStack, 0);
        level = getLevel(itemStack);
    }

    protected int getLevel(ItemStack itemStack)
    {
        return NBTUtil.getInt(itemStack, "Level");
    }

    protected void setLevel(ItemStack itemStack, int plus)
    {
        NBTUtil.setInteger(itemStack, "Level", level + plus);
    }

    public boolean hitEntity(ItemStack itemStack, EntityLiving entityLiving,
            EntityLiving player)
    {
        if (entityLiving != null)
        {
            if (getLevel(itemStack) >= 2)
            {
                itemStack.setItemDamage(itemStack.getItemDamage()
                        - (int)entityLiving.prevHealth / getLevel(itemStack));
            } else
            {
                itemStack.setItemDamage(itemStack.getItemDamage()
                        - (int)entityLiving.prevHealth);
            }
            if (itemStack.getItemDamage() == 0 && getLevel(itemStack) < 25)
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
    public void onUpdate(ItemStack itemStack, World world, Entity entity,
            int par4, boolean par5)
    {
        level = getLevel(itemStack);
        if (entity != null)
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.inventory.hasItemStack(itemStack))
                {
                    if (player.inventory.getCurrentItem() == itemStack)
                    {
                        if (getLevel(itemStack) >= 5)
                        {
                            player.addPotionEffect(new PotionEffect(5, -1,
                                    getLevel(itemStack) / 5 - 1));
                        } else
                        {
                        }
                        if (player.inventory.getCurrentItem().getItemDamage() == 0
                                && getLevel(itemStack) < 25)
                        {
                            itemStack
                                    .setItemDamage(itemStack.getMaxDamage() - 1);
                            setLevel(itemStack, 1);
                        }

                    }
                }
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
        int max = itemStack.getMaxDamage() - 1;
        infoList.add("Level " + getLevel(itemStack) + " EXP: "
                + (max - itemStack.getItemDamage()) + "/" + max);
    }
}
