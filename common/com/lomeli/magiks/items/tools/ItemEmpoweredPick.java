package com.lomeli.magiks.items.tools;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEmpoweredPick extends ItemPickaxe
{
    private String itemTexture;

    public ItemEmpoweredPick(int par1, EnumToolMaterial par3EnumToolMaterial,
            String texture)
    {
        super(par1, par3EnumToolMaterial);
        this.setMaxDamage(-1);
        itemTexture = texture;
        this.setCreativeTab(Magiks.modTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        iconIndex = iconRegister
                .registerIcon(Strings.modID + ":" + itemTexture);
    }

    @SideOnly(Side.CLIENT)
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack,
            String keyBinding)
    {
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        Vec3 look = player.getLookVec();
        
        int x = (int) (player.posX + look.xCoord * 4);
        int y = (int) (look.yCoord) + 2;
        int z = (int) (player.posZ + look.zCoord * 4);
        
        world.destroyBlock(x, y, z, true);
        world.destroyBlock(x + 1, y, z, true);
        world.destroyBlock(x - 1, y, z, true);
        world.destroyBlock(x, y, z + 1, true);
        world.destroyBlock(x, y, z - 1, true);
        world.destroyBlock(x + 1, y, z - 1, true);
        world.destroyBlock(x -1 , y, z + 1, true);
        world.destroyBlock(x + 1, y, z + 1, true);
        world.destroyBlock(x - 1, y, z - 1, true);
        
        return itemStack;
    }

}
