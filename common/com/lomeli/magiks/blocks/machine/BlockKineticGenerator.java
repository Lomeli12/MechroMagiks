package com.lomeli.magiks.blocks.machine;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lomeli.magiks.Magiks;
import com.lomeli.magiks.blocks.ModBlocksMagiks;
import com.lomeli.magiks.lib.GuiIDs;
import com.lomeli.magiks.lib.RenderIDs;
import com.lomeli.magiks.lib.Strings;
import com.lomeli.magiks.tileentity.TileEntityKineticGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockKineticGenerator extends BlockContainer
{

    public BlockKineticGenerator(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.67F, 1.0F);
        this.setBlockBoundsForItemRender();
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModBlocksMagiks.manceryBlock.blockID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(Strings.modID
                + ":kineticgentexture");
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return RenderIDs.kineticGID;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityKineticGenerator();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        dropInventory(world, x, y, z);

        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float f, float g, float t)
    {
        if (player.isSneaking())
            return false;
        else
        {
            if (!world.isRemote)
            {
                TileEntityKineticGenerator kineticGen = (TileEntityKineticGenerator) world
                        .getBlockTileEntity(x, y, z);
                if (kineticGen != null)
                {
                    player.openGui(Magiks.instance, GuiIDs.kineticGen, world,
                            x, y, z);
                }
            }
        }
        return true;
    }

    private Random rand = new Random();

    private void dropInventory(World world, int x, int y, int z)
    {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z
                        + dZ, new ItemStack(itemStack.itemID,
                        itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound(
                            (NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }
}
