package net.lomeli.magiks.blocks.machine;

import java.util.Random;

import net.lomeli.lomlib.item.ItemUtil;

import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.lib.ModStrings;
import net.lomeli.magiks.tileentity.TileEntityMultiFurnaceCore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMultiFurnaceCore extends BlockContainer
{
    public static final int META_ISACTIVE = 0x00000008;
    public static final int MASK_DIR = 0x00000007;
    public static final int META_DIR_NORTH = 2;
    public static final int META_DIR_SOUTH = 5;
    public static final int META_DIR_EAST = 3;
    public static final int META_DIR_WEST = 4;

    private Icon faceIconUnlit;
    private Icon faceIconLit;
    private Icon other;

    public BlockMultiFurnaceCore(int blockId)
    {
        super(blockId, Material.rock);

        setUnlocalizedName("blockMultiFurnaceCore");
        setStepSound(Block.soundStoneFootstep);
        setHardness(3.5f);
        setCreativeTab(Magiks.modTab);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) >> 3 == 0 ? 0 : 15;
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        faceIconUnlit = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":dupeFurnace_Front_Unlit");
        faceIconLit = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":dupeFurnace_Front_Lit");
        other = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":burningstone");
    }
    
    @Override
    public Icon getIcon(int side, int metadata)
    {
        boolean isActive = metadata >> 3 == 1;

        return side == 0 ? other : side == 1 ? other : isActive 
        		? faceIconLit : !isActive ? faceIconUnlit: faceIconUnlit;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLiving entity, ItemStack itemStack)
    {
        int metadata = 0;
        int facing = META_DIR_WEST;

        int dir = MathHelper.floor_double(entity.rotationYaw * 4F / 360F + 0.5) & 3;
        if (dir == 0)
        {
            facing = META_DIR_NORTH;
        }
        if (dir == 1)
        {
            facing = META_DIR_EAST;
        }
        if (dir == 2)
        {
            facing = META_DIR_SOUTH;
        }
        if (dir == 3)
        {
            facing = META_DIR_WEST;
        }

        metadata |= facing;
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
            return false;

        TileEntityMultiFurnaceCore tileEntity = (TileEntityMultiFurnaceCore) world
                .getBlockTileEntity(x, y, z);
        if (tileEntity != null)
        {
        	System.out.println(tileEntity.getIsValid());
            // Determine if the Multiblock is currently known to be valid
            if (tileEntity.getIsValid())
            {
            	player.openGui(Magiks.instance, GuiIDs.dupeFurnace, world, x,
                		y, z);
            }
            else
            {
                for (ItemStack wand : MagiksArrays.wands)
                {
                    if (player.inventory.hasItem(wand.itemID))
                    {
                        if(ItemUtil.getSlotContainingItem(wand.itemID, player.inventory.mainInventory)
                                == player.inventory.currentItem)
                        {
                            if (tileEntity.checkIfProperlyFormed())
                            {
                            	player.inventory.getStackInSlot(
                            		ItemUtil.getSlotContainingItem(wand.itemID, 
                            		player.inventory.mainInventory))
                            		.damageItem(10, player);
                                tileEntity.convertDummies();
                                if (world.isRemote)
                                	player.addChatMessage(ModStrings.dupeFurnaceName
                                        + "Created!");
                            }
                            else if(!tileEntity.getIsValid())
                            {
                            	player.inventory.getStackInSlot(
                            		ItemUtil.getSlotContainingItem(
                            		wand.itemID, player.inventory.mainInventory))
                            		.damageItem(10, player);
                            	tileEntity.convertDummies();
                            	
                            	if (world.isRemote)
                            		player.addChatMessage(ModStrings.dupeFurnaceName
                            			+ "Created!");
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random prng)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        if ((metadata & META_ISACTIVE) == 0)
            return;

        int facing = metadata & MASK_DIR;

        double yMod = 0.3 * prng.nextDouble();
        double xMod = -0.02;
        double zMod = 0.75 - 0.5 * prng.nextDouble();
        double temp = 0.0;

        switch (facing)
        {
            case META_DIR_EAST:
                xMod += 1.04;
                break;

            case META_DIR_NORTH:
                temp = xMod;
                xMod = zMod;
                zMod = temp;
                break;

            case META_DIR_SOUTH:
                temp = xMod;
                xMod = zMod;
                zMod = temp + 1.04;
                break;

            default:
                break;
        }

        world.spawnParticle("smoke", x + xMod, y + yMod, z + zMod, 0, 0, 0);
        world.spawnParticle("flame", x + xMod, y + yMod, z + zMod, 0, 0, 0);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityMultiFurnaceCore();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        TileEntityMultiFurnaceCore tileEntity = (TileEntityMultiFurnaceCore) world
                .getBlockTileEntity(x, y, z);

        if (tileEntity != null)
        {
            tileEntity.invalidateMultiblock();
        }

        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    @SuppressWarnings("unused")
	private static int getSideFromFacing(int facing)
    {
        switch (facing)
        {
            case META_DIR_WEST:
                return 4;

            case META_DIR_EAST:
                return 3;

            case META_DIR_NORTH:
                return 2;

            case META_DIR_SOUTH:
                return 5;

            default:
                return 4;
        }
    }

    private void dropItems(World world, int x, int y, int z)
    {
        Random prng = new Random();

        TileEntityMultiFurnaceCore tileEntity = (TileEntityMultiFurnaceCore) world
                .getBlockTileEntity(x, y, z);
        if (tileEntity == null)
            return;

        for (int slot = 0; slot < tileEntity.getSizeInventory(); slot++)
        {
            ItemStack item = tileEntity.getStackInSlot(slot);

            if (item != null && item.stackSize > 0)
            {
                float rx = prng.nextFloat() * 0.8f + 0.1f;
                float ry = prng.nextFloat() * 0.8f + 0.1f;
                float rz = prng.nextFloat() * 0.8f + 0.1f;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
                        + rz, item.copy());
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
}
