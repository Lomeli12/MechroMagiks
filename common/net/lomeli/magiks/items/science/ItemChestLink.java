package net.lomeli.magiks.items.science;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.lomlib.util.ToolTipUtil;
import net.lomeli.lomlib.util.NBTUtil;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.items.ItemGeneric;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class ItemChestLink extends ItemGeneric
{
	public ItemChestLink(int par1, String Texture) 
	{
		super(par1, Texture, false);
	}
	
	private void linkChest(ItemStack itemStack, int x, int y, int z, int world, String type)
	{
		NBTUtil.setInteger(itemStack, "world", world);
		NBTUtil.setInteger(itemStack, "chestX", x);
		NBTUtil.setInteger(itemStack, "chestY", y);
		NBTUtil.setInteger(itemStack, "chestZ", z);
		NBTUtil.setBoolean(itemStack, "linked", true);
		NBTUtil.setString(itemStack, "type", type);
	}
	
	private boolean isLinked(ItemStack itemStack)
	{
		return NBTUtil.getBoolean(itemStack, "linked");
	}
	
	private int getWorld(ItemStack itemStack)
	{
		return NBTUtil.getInt(itemStack, "world");
	}
	
	private int getChestX(ItemStack itemStack)
	{
		return NBTUtil.getInt(itemStack, "chestX");
	}
	
	private int getChestY(ItemStack itemStack)
	{
		return NBTUtil.getInt(itemStack, "chestY");
	}
	
	private int getChestZ(ItemStack itemStack)
	{
		return NBTUtil.getInt(itemStack, "chestZ");
	}
	
	private String getType(ItemStack itemStack)
	{
		return NBTUtil.getString(itemStack, "type");
	}
	
	private void unLinkChest(ItemStack itemStack)
	{
		NBTUtil.removeTag(itemStack, "world");
		NBTUtil.removeTag(itemStack, "chestX");
		NBTUtil.removeTag(itemStack, "chestY");
		NBTUtil.removeTag(itemStack, "chestZ");
		NBTUtil.removeTag(itemStack, "type");
		NBTUtil.setBoolean(itemStack, "linked", false);
	}

	@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		int regBlock = world.getBlockId(x, y, z);
		boolean result = false;
		
		if(Block.blocksList[regBlock] == ModBlocksMagiks.hollowWood && player.isSneaking())
		{
			linkChest(itemstack, x, y, z, world.provider.dimensionId, "Hallow Wood");
			
			result = true;
		}
		else if(Block.blocksList[regBlock] == ModBlocksMagiks.linkingChest && player.isSneaking())
		{
			linkChest(itemstack, x, y, z, world.provider.dimensionId, "Linking Chest");
			
			result = true;
		}
		
		return result;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
            EntityPlayer player)
    {
		if(!world.isRemote)
		{
			if(isLinked(itemstack))
			{
				if(player.isSneaking())
					unLinkChest(itemstack);
				else if(getWorld(itemstack) == world.provider.dimensionId)
				{
					TileEntity tileEntity = world
		            		.getBlockTileEntity(getChestX(itemstack), getChestY(itemstack), getChestZ(itemstack));
					if (tileEntity != null)
					{
						if(tileEntity instanceof TileEntityHollowWood)
							player.openGui(Magiks.instance, GuiIDs.hallowWood, world, getChestX(itemstack), getChestY(itemstack), getChestZ(itemstack));
						else if(tileEntity instanceof TileEntityLinkingChest)
							player.openGui(Magiks.instance, GuiIDs.chest, world, getChestX(itemstack), getChestY(itemstack), getChestZ(itemstack));
					}	
					else
						unLinkChest(itemstack);
				}
			}
		}
		return itemstack;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player,
            List infoList, boolean bool)
    {
		if(isLinked(itemStack))
		{
			if(ToolTipUtil.doAdditionalInfo())
			{
				infoList.add("World: " + WorldProvider.getProviderForDimension(getWorld(itemStack)).getDimensionName());
				infoList.add("Container Type: " + getType(itemStack));
				infoList.add("Chest X-Coord: " + getChestX(itemStack));
				infoList.add("Chest Y-Coord: " + getChestY(itemStack));
				infoList.add("Chest Z-Coord: " + getChestZ(itemStack));
			}
			else
				infoList.add(ToolTipUtil.additionalInfoInstructions("6"));
		}
		else
		{
			if(ToolTipUtil.doAdditionalInfo())
			{
				infoList.add("SHIFT+Right Click a Hollow Wood or");
				infoList.add("Linking chest to link to it.");
			}
			else
				infoList.add(ToolTipUtil.additionalInfoInstructions("6"));
		}
    }
	
}
