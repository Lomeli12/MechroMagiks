package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

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
	
	public boolean checkIfProperlyFormed()
    {	
		if(!valid)
		{
			outerloop:
				for(int j = -1; j < 2; j++)
					for(int l = -1; l < 2; l++)
					{
						int k = checkArea(j, l);
						if(k > 7)
						{
							System.out.println("breaking");
							valid = true;
							break outerloop;
						}
					}
    	}
		return valid;
    }
	
	private int checkArea(int var1, int var2)
	{
		int x = this.xCoord, y = this.yCoord, z = this.zCoord;
		int connected = 1;
		int addX, addZ;
		if(var1 > 0)
			addX = -1;
		else
			addX = 1;
		
		if(var2 > 0)
			addZ = -1;
		else
			addZ = 1;
		
		for(int Ix = (x + var1); Ix <= x; Ix+=addX)
			for(int Iy = y; Iy <= (y+1); Iy++)
				for(int Iz = (z + var2); Iz <= z; Iz+=addZ)
				{
					System.out.println(worldObj.getBlockId(Ix, Iy, Iz));
					if(worldObj.getBlockId(Ix, Iy, Iz) == ModBlocksMagiks.builderCore.blockID)
						connected++;
				}
		
		return connected;
	}
	
	public void convertDummies()
	{
		int x = this.xCoord, y = this.yCoord, z = this.zCoord;
		int addX, addZ;
		
		for(int j = -1; j < 2; j++)
			for(int l = -1; l < 2; l++)
			{
				if(j > 0)
					addX = -1;
				else
					addX = 1;
				
				if(l > 0)
					addZ = -1;
				else
					addZ = 1;
				
				for(int Ix = (x + j); Ix <= x; Ix+=addX)
					for(int Iy = y; Iy <= (y+1); Iy++)
						for(int Iz = (z + l); Iz <= z; Iz+=addZ)
						{
							System.out.println(worldObj.getBlockId(Ix, Iy, Iz));
							if(Ix != x & Iz != z & Iy != y)
							{
								if(worldObj.getBlockId(Ix, Iy, Iz) == ModBlocksMagiks.builderCore.blockID)
								{
									worldObj.setBlock(Ix, Iy, Iz, ModBlocksMagiks.builderBlock.blockID);
									worldObj.markBlockForUpdate(Ix, Iy, Iz);
									TileEntityBuilderDummy dummy = (TileEntityBuilderDummy) worldObj
						                .getBlockTileEntity(Ix, Iy, Iz);
									dummy.setCore(this);
								}
							}
						}
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
    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
		if(getStackInSlot(i).isItemEqual(itemstack) && 
			getStackInSlot(i).stackSize <= getStackInSlot(i).getMaxDamage())
			return true;
		else
			return false;
    }

}
