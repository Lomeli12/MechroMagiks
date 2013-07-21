package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileEntityBuilder extends TileEntityMagiks 
	implements IInventory
{
	private ItemStack[] inventory;
	
	@SuppressWarnings("unused")
    private int mistLevel, maxMistLevel, processingTime, formationDir;
	
	private EnumMachineTypes type;
	
	private boolean valid;
	
	public TileEntityBuilder()
	{
		inventory = new ItemStack[2];
		type = EnumMachineTypes.MACHINE;
		maxMistLevel = 1000;
	}
	
	public boolean isValidMultiBlock()
	{
		return valid;
	}
	
	public void invalidateMultiBlock(World world, int x, int y, int z)
	{
		if(!checkIfFormedProperly(world, x, y, z))
		{
			revertDummies(world, x, y, z);
			this.valid = false;
		}
	}
	
	public boolean check(World world, int x, int y, int z)
	{
		if(checkIfFormedProperly(world, x, y, z))
		{
			this.valid = true;
			convertDummies(world, x, y, z);
			return true;
		}
		else
		{
			this.valid = false;
			return false;
		}
	}
	
	public boolean checkIfFormedProperly(World world, int x, int y, int z)
	{
		int count = 1;
		for(int xRange = (x - 1); xRange < (x + 2); xRange++)
		{
			if(world.getBlockId(xRange, y - 1, z) == ModBlocksMagiks.builderCore.blockID ||
				world.getBlockId(xRange, y - 1, z) == ModBlocksMagiks.builderBlock.blockID)
				count++;
		}
		if(world.getBlockId(x, y - 1, z + 1) == ModBlocksMagiks.builderCore.blockID ||
			world.getBlockId(x, y-1, z + 1)  == ModBlocksMagiks.builderBlock.blockID)
			count++;
		if(world.getBlockId(x, y - 1, z - 1) == ModBlocksMagiks.builderCore.blockID ||
			world.getBlockId(x, y - 1, z - 1) == ModBlocksMagiks.builderBlock.blockID)
			count++;
		if(world.getBlockId(x, y - 2, z) == ModBlocksMagiks.builderCore.blockID ||
			world.getBlockId(x, y - 2, z) == ModBlocksMagiks.builderBlock.blockID)
			count++;
		
		if(count == 7)
			return true;
		else
			return false;
	}
	
	public void revertDummies(World world, int x, int y, int z)
	{
		try
		{
			for(int xRange = (x - 1); xRange < (x + 2); xRange++)
			{
				world.setBlock(xRange, y - 1, z, ModBlocksMagiks.builderCore.blockID);
				world.markBlockForUpdate(xRange, y - 1, z);
			}
			world.setBlock(x, y - 2, z, ModBlocksMagiks.builderCore.blockID);
			world.markBlockForUpdate(x, y - 2, z);
		
			world.setBlock(x, y - 1, z - 1, ModBlocksMagiks.builderCore.blockID);
			world.markBlockForUpdate(x, y - 1, z - 1);
		
			world.setBlock(x, y - 1, z + 1, ModBlocksMagiks.builderCore.blockID);
			world.markBlockForUpdate(x, y - 1, z + 1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void convertDummies(World world, int x, int y, int z)
	{
		try	
		{	
			for(int xRange = (x - 1); xRange < (x + 2); xRange++)
			{
				world.setBlock(xRange, y - 1, z, ModBlocksMagiks.builderBlock.blockID);
				world.markBlockForUpdate(xRange, y - 1, z);
				TileEntityBuilderDummy tile = (TileEntityBuilderDummy)world.getBlockTileEntity(xRange, y - 1, z);
				tile.setCore(this);
			}
			world.setBlock(x, y - 2, z, ModBlocksMagiks.builderBlock.blockID);
			world.markBlockForUpdate(x, y - 2, z);
			TileEntityBuilderDummy tile1 = (TileEntityBuilderDummy)world.getBlockTileEntity(x, y - 2, z);
			tile1.setCore(this);
		
			world.setBlock(x, y - 1, z - 1, ModBlocksMagiks.builderBlock.blockID);
			world.markBlockForUpdate(x, y - 1, z - 1);
			TileEntityBuilderDummy tile2 = (TileEntityBuilderDummy)world.getBlockTileEntity(x, y - 1, z - 1);
			tile2.setCore(this);
		
			world.setBlock(x, y - 1, z + 1, ModBlocksMagiks.builderBlock.blockID);
			world.markBlockForUpdate(x, y - 1, z + 1);
			TileEntityBuilderDummy tile3 = (TileEntityBuilderDummy)world.getBlockTileEntity(x, y - 1, z + 1);
			tile3.setCore(this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(this.worldObj != null)
		{
			
		}
	}
	
	@Override
	public int getMistLevel()
	{
		return mistLevel;
	}
	
	@Override
	public int getMaxMistLevel()
    {
		return maxMistLevel;
    }
	
	@Override
	public void setMistLevel(int value)
    {
		mistLevel = value;
    }
	
	@Override
    public void addToMistLevel(int value)
    {
		mistLevel += value;
    }
	
	@Override
    public int getSizeInventory()
    {
	    return inventory.length;
    }

	@Override
    public ItemStack getStackInSlot(int i)
    {
		if(inventory[i] != null)
			return inventory[i];
		else
			return inventory[0];
    }

	@Override
    public ItemStack decrStackSize(int slot, int amount)
    {
		ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
        	if(itemStack.stackSize <= amount)
        		setInventorySlotContents(slot, null);
        	else
        	{
        		itemStack.splitStack(amount);
        		if (itemStack.stackSize == 0)
        			setInventorySlotContents(slot, null);
        	}
        }

        return itemStack;
    }

	@Override
	public EnumMachineTypes getType()
	{
		return type;
	}
	
	@Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

	@Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

	@Override
    public String getInvName()
    {
	    return ModStrings.containerBuilder;
    }

	@Override
    public boolean isInvNameLocalized()
    {
	    return true;
    }

	@Override
    public int getInventoryStackLimit()
    {
	    return 64;
    }

	@Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
	    return true;
    }

	@Override
    public void openChest()
    {}

	@Override
    public void closeChest()
    {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
		if(getStackInSlot(i).isItemEqual(itemstack) && 
			getStackInSlot(i).stackSize <= getStackInSlot(i).getMaxDamage())
			return true;
		else
			return false;
    }

}
