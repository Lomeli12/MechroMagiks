package net.lomeli.magiks.items.tools;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.lib.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//import net.minecraft.util.MathHelper;
//import net.minecraft.util.Vec3;

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
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Strings.MOD_ID + ":" + itemTexture);
    }

    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack,
            String keyBinding)
    {
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        /*
         * Vec3 look = player.getLookVec();
         * 
         * int x = (int) (player.posX + look.xCoord * 4); int y = (int)
         * (player.posY + look.yCoord * 4); int z = (int) (player.posZ +
         * look.zCoord * 4);
         * 
         * int side = MathHelper .floor_double(player.rotationYaw * 4.0F /
         * 360.0F + 0.5D) & 3; world.destroyBlock(x, y, z, true);
         * player.sendChatToPlayer("" + side);
         */
        return itemStack;
    }

}
