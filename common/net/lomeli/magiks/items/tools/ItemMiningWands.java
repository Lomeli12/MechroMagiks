package net.lomeli.magiks.items.tools;

import net.lomeli.magiks.items.ItemGeneric;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class ItemMiningWands extends ItemGeneric
{
    // private int wandStrength;
    public ItemMiningWands(int par1, String Texture, boolean special,
            int durability, int strength)
    {
        super(par1, Texture, special);
        this.setMaxDamage(durability);
        // wandStrength = strength;
    }

    @SuppressWarnings("unused")
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world,
            EntityPlayer player)
    {
        Vec3 look = player.getLookVec();

        int x = (int) (player.posX + look.xCoord * 4);
        int y = (int) (player.posY + look.yCoord * 4);
        int z = (int) (player.posZ + look.zCoord * 4);

        int id = world.getBlockId(x, y, z);

        player.sendChatToPlayer(String.valueOf(id));
        if (id != 0 || world.blockExists(x, y, z)
                || !Block.blocksList[id].isAirBlock(world, x, y, z))
        {
            world.destroyBlock(x, y, z, false);
            world.setBlockToAir(x, y, z);
            try
            {
                ItemStack block = new ItemStack(Block.blocksList[id]);
                if (block != null)
                {
                    player.inventory.addItemStackToInventory(block);
                } else
                {
                    player.inventory.addItemStackToInventory(new ItemStack(
                            Block.dirt));
                }
            } catch (Exception e)
            {
            }
        }

        return itemStack;
    }

}
