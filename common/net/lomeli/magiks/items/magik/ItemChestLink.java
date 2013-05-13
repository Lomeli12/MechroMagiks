package net.lomeli.magiks.items.magik;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.lomeli.magiks.Magiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.core.handler.MiscHandler;
import net.lomeli.magiks.core.helper.NBTHelper;
import net.lomeli.magiks.items.ItemGeneric;
import net.lomeli.magiks.lib.GuiIDs;
import net.lomeli.magiks.tileentity.TileEntityHollowWood;
import net.lomeli.magiks.tileentity.TileEntityLinkingChest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemChestLink extends ItemGeneric
{
	public ItemChestLink(int par1, String Texture) 
	{
		super(par1, Texture, false);
	}
	
	private void linkChest(ItemStack itemStack, int x, int y, int z, int world, String type)
	{
		NBTHelper.setInteger(itemStack, "world", world);
		NBTHelper.setInteger(itemStack, "chestX", x);
		NBTHelper.setInteger(itemStack, "chestY", y);
		NBTHelper.setInteger(itemStack, "chestZ", z);
		NBTHelper.setBoolean(itemStack, "linked", true);
		NBTHelper.setString(itemStack, "type", type);
	}
	
	private boolean isLinked(ItemStack itemStack)
	{
		return NBTHelper.getBoolean(itemStack, "linked");
	}
	
	private int getWorld(ItemStack itemStack)
	{
		return NBTHelper.getInt(itemStack, "world");
	}
	
	private int getChestX(ItemStack itemStack)
	{
		return NBTHelper.getInt(itemStack, "chestX");
	}
	
	private int getChestY(ItemStack itemStack)
	{
		return NBTHelper.getInt(itemStack, "chestY");
	}
	
	private int getChestZ(ItemStack itemStack)
	{
		return NBTHelper.getInt(itemStack, "chestZ");
	}
	
	private String getType(ItemStack itemStack)
	{
		return NBTHelper.getString(itemStack, "type");
	}
	
	private void unLinkChest(ItemStack itemStack)
	{
		NBTHelper.removeTag(itemStack, "world");
		NBTHelper.removeTag(itemStack, "chestX");
		NBTHelper.removeTag(itemStack, "chestY");
		NBTHelper.removeTag(itemStack, "chestZ");
		NBTHelper.removeTag(itemStack, "type");
		NBTHelper.setBoolean(itemStack, "linked", false);
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
			if(MiscHandler.doAdditionalInfo())
			{
				if(getWorld(itemStack) == 0)
					infoList.add("World: Overworld");
				else if(getWorld(itemStack) == -1)
					infoList.add("World: Nether");
				else if(getWorld(itemStack) == 1)
					infoList.add("World: End");
				else 
					infoList.add("World ID: " + getWorld(itemStack));
				infoList.add("Container Type: " + getType(itemStack));
				infoList.add("Chest X-Coord: " + getChestX(itemStack));
				infoList.add("Chest Y-Coord: " + getChestY(itemStack));
				infoList.add("Chest Z-Coord: " + getChestZ(itemStack));
			}
			else
				infoList.add(MiscHandler.additionalInfoInstructions("6"));
		}
		else
		{
			if(MiscHandler.doAdditionalInfo())
			{
				infoList.add("SHIFT+Right Click a Hollow Wood or");
				infoList.add("Linking chest to link to it.");
			}
			else
				infoList.add(MiscHandler.additionalInfoInstructions("6"));
		}
    }
	
}
