package net.lomeli.magiks.tileentity;

import java.util.Random;

import net.lomeli.magiks.api.libs.MagiksArrays;
import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.blocks.ModBlocksMagiks;
import net.lomeli.magiks.blocks.machine.BlockMultiFurnaceCore;
import net.lomeli.magiks.items.ModItemsMagiks;
import net.lomeli.magiks.lib.ModStrings;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntityFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMultiFurnaceCore extends TileEntityMagiks implements
        ISidedInventory
{
    public static final int[] sidedSlotSides = new int[] { 0 };
    public static final int[] sidedSlotBottom = new int[] { 2, 1 };
    public static final int[] sidedSlotTop = new int[] { 1 };

    private ItemStack[] furnaceItems = new ItemStack[6];
    public int furnaceBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int furnaceCookTime = 0;
    public int upgrades = 1;
    
    private int mistLevel, maxMistLevel = 2000;

    private boolean isValidMultiblock;

    private EnumMachineTypes type;
    
    public TileEntityMultiFurnaceCore()
    {
    	type = EnumMachineTypes.MACHINE;
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
	public EnumMachineTypes getType()
	{
		return type;
	}
	
	@Override
	public void addToMistLevel(int value)
    {
		mistLevel += value;
    }

    public boolean getIsValid()
    {
        return isValidMultiblock;
    }

    public void invalidateMultiblock()
    {
        isValidMultiblock = false;

        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        metadata = metadata & BlockMultiFurnaceCore.MASK_DIR;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);

        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        furnaceCookTime = 0;
        revertDummies();
    }

    public boolean checkIfProperlyFormed()
    {
        int dir = getBlockMetadata() & BlockMultiFurnaceCore.MASK_DIR;

        int depthMultiplier = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_WEST ? 1 : -1;
        boolean forwardZ = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_SOUTH;

        /*
         * FORWARD BACKWARD North: -z +z South: +z -z East: +x -x West: -x +x
         * 
         * Should move BACKWARD for depth (facing = direction of block face, not
         * direction of player looking at face)
         */

        for (int horiz = -1; horiz <= 1; horiz++) // Horizontal (X or Z)
        {
            for (int vert = -1; vert <= 1; vert++) // Vertical (Y)
            {
                for (int depth = 0; depth <= 2; depth++) // Depth (Z or X)
                {
                    int x = xCoord
                            + (forwardZ ? horiz : depth * depthMultiplier);
                    int y = yCoord + vert;
                    int z = zCoord
                            + (forwardZ ? depth * depthMultiplier : horiz);

                    int blockId = worldObj.getBlockId(x, y, z);

                    if (horiz == 0 && vert == 0)
                    {
                        if (depth == 0)
                        {
                            continue;
                        }

                        if (depth == 1)
                        {
                            if (blockId != 0)
                                return false;
                            else
                            {
                                continue;
                            }
                        }
                    }

                    if (blockId != ModBlocksMagiks.burningStone.blockID)
                        return false;
                }
            }
        }

        return true;
    }

    public void convertDummies()
    {
        int dir = getBlockMetadata() & BlockMultiFurnaceCore.MASK_DIR;

        int depthMultiplier = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_WEST ? 1 : -1;
        boolean forwardZ = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_SOUTH;

        /*
         * FORWARD BACKWARD North: -z +z South: +z -z East: +x -x West: -x +x
         * 
         * Should move BACKWARD for depth (facing = direction of block face, not
         * direction of player looking at face)
         */

        for (int horiz = -1; horiz <= 1; horiz++) // Horizontal (X or Z)
        {
            for (int vert = -1; vert <= 1; vert++) // Vertical (Y)
            {
                for (int depth = 0; depth <= 2; depth++) // Depth (Z or X)
                {
                    int x = xCoord
                            + (forwardZ ? horiz : depth * depthMultiplier);
                    int y = yCoord + vert;
                    int z = zCoord
                            + (forwardZ ? depth * depthMultiplier : horiz);

                    if (horiz == 0 && vert == 0 && (depth == 0 || depth == 1))
                    {
                        continue;
                    }

                    worldObj.setBlock(x, y, z,
                            ModBlocksMagiks.burningStone.blockID);
                    worldObj.markBlockForUpdate(x, y, z);
                    TileEntityMultiFurnaceDummy dummyTE = (TileEntityMultiFurnaceDummy) worldObj
                            .getBlockTileEntity(x, y, z);
                    dummyTE.setCore(this);
                }
            }
        }

        isValidMultiblock = true;
    }

    private void revertDummies()
    {
        int dir = getBlockMetadata() & BlockMultiFurnaceCore.MASK_DIR;

        int depthMultiplier = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_WEST ? 1 : -1;
        boolean forwardZ = dir == BlockMultiFurnaceCore.META_DIR_NORTH
                || dir == BlockMultiFurnaceCore.META_DIR_SOUTH;

        /*
         * FORWARD BACKWARD North: -z +z South: +z -z East: +x -x West: -x +x
         * 
         * Should move BACKWARD for depth (facing = direction of block face, not
         * direction of player looking at face)
         */

        for (int horiz = -1; horiz <= 1; horiz++) // Horizontal (X or Z)
        {
            for (int vert = -1; vert <= 1; vert++) // Vertical (Y)
            {
                for (int depth = 0; depth <= 2; depth++) // Depth (Z or X)
                {
                    int x = xCoord
                            + (forwardZ ? horiz : depth * depthMultiplier);
                    int y = yCoord + vert;
                    int z = zCoord
                            + (forwardZ ? depth * depthMultiplier : horiz);

                    int blockId = worldObj.getBlockId(x, y, z);

                    if (horiz == 0 && vert == 0 && (depth == 0 || depth == 1))
                    {
                        continue;
                    }

                    if (blockId != ModBlocksMagiks.burningStone.blockID)
                    {
                        continue;
                    }

                    worldObj.setBlock(x, y, z,
                            ModBlocksMagiks.burningStone.blockID);
                    worldObj.markBlockForUpdate(x, y, z);
                }
            }
        }

        isValidMultiblock = false;
    }

    @Override
    public void updateEntity()
    {
        Random integer = new Random();
        int chance = integer.nextInt(10000);
        upgradeAmount();
        if (!isValidMultiblock)
            return;

        @SuppressWarnings("unused")
        boolean flag = furnaceBurnTime > 0;
        boolean flag1 = false;
        int metadata = getBlockMetadata();
        int isActive = metadata >> 3;

        if (furnaceBurnTime > 0)
        {
            furnaceBurnTime--;
        }

        if (!worldObj.isRemote)
        {
            if (furnaceBurnTime == 0 && canSmelt())
            {
                currentItemBurnTime = furnaceBurnTime = TileEntityFurnace
                        .getItemBurnTime(furnaceItems[1]);

                if (furnaceBurnTime > 0)
                {
                    flag1 = true;

                    if (furnaceItems[1] != null)
                    {
                        furnaceItems[1].stackSize--;

                        if (furnaceItems[1].stackSize == 0)
                        {
                            furnaceItems[1] = furnaceItems[1].getItem()
                                    .getContainerItemStack(furnaceItems[1]);
                        }
                    }
                }
            }

            if (isBurning() && canSmelt())
            {
                furnaceCookTime++;

                if (furnaceCookTime == cookingTime())
                {
                    furnaceCookTime = 0;
                    smeltItem(chance);
                    flag1 = true;
                }
            } else
            {
                furnaceCookTime = 0;
            }

            if (isActive == 0 && furnaceBurnTime > 0)
            {
                flag1 = true;
                metadata = getBlockMetadata();
                isActive = 1;
                metadata = isActive << 3 | metadata
                        & BlockMultiFurnaceCore.META_ISACTIVE;

                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                        metadata, 2);
            }
        }

        if (flag1)
        {
            onInventoryChanged();
        }
    }

    @Override
    public int getSizeInventory()
    {
        return furnaceItems.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return furnaceItems[slot];
    }
    
    public int cookingTime()
    {
    	int time = 100;
    	
    	for(int i = 3; i < 6; i++)
    	{
    		if(furnaceItems[i] != null)
    		{
    			if(getStackInSlot(i).itemID == ModItemsMagiks.smeltingUpgrade.itemID)
    			{
    				time -= (5 * getStackInSlot(i).stackSize);
    			}
    		}
    	}
    	
    	return time;
    }

    @Override
    public ItemStack decrStackSize(int slot, int count)
    {
        if (furnaceItems[slot] != null)
        {
            ItemStack itemStack;

            itemStack = furnaceItems[slot].splitStack(count);

            if (furnaceItems[slot].stackSize <= 0)
            {
                furnaceItems[slot] = null;
            }

            return itemStack;
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (furnaceItems[slot] != null)
        {
            ItemStack stack = furnaceItems[slot];
            furnaceItems[slot] = null;
            return stack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        furnaceItems[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return ModStrings.dupeFurnaceName;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false
                : entityPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openChest()
    {
    }

    @Override
    public void closeChest()
    {
    }

    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemStack)
    {
        return slot == 2 ? false : slot == 1 ? TileEntityFurnace
                .isItemFuel(itemStack) : true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);

        loadNBT(tagCompound);
    }
    
    public void loadNBT(NBTTagCompound tagCompound)
    {
    	@SuppressWarnings("unused")
        int md = tagCompound.getInteger("BlockMeta");
    	isValidMultiblock = tagCompound.getBoolean("isValidMultiblock");
        mistLevel = tagCompound.getInteger("Mist");

        NBTTagList itemsTag = tagCompound.getTagList("Items");
        furnaceItems = new ItemStack[getSizeInventory()];

        for (int i = 0; i < itemsTag.tagCount(); i++)
        {
            NBTTagCompound slotTag = (NBTTagCompound) itemsTag.tagAt(i);
            byte slot = slotTag.getByte("Slot");

            if (slot >= 0 && slot < furnaceItems.length)
            {
                furnaceItems[slot] = ItemStack.loadItemStackFromNBT(slotTag);
            }
        }
        furnaceBurnTime = tagCompound.getShort("BurnTime");
        furnaceCookTime = tagCompound.getShort("CookTime");
        currentItemBurnTime = TileEntityFurnace
                .getItemBurnTime(furnaceItems[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);

        addToNBT(tagCompound);
    }
    
    public void addToNBT(NBTTagCompound tagCompound)
    {
    	tagCompound.setBoolean("isValidMultiblock", isValidMultiblock);
        System.out.println("Is valid? " + (isValidMultiblock ? "Yes" : "No"));

        tagCompound.setInteger("Mist", mistLevel);
        tagCompound.setShort("BurnTime", (short) furnaceBurnTime);
        tagCompound.setShort("CookTime", (short) furnaceCookTime);
        NBTTagList itemsList = new NBTTagList();

        for (int i = 0; i < furnaceItems.length; i++)
        {
            if (furnaceItems[i] != null)
            {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                furnaceItems[i].writeToNBT(slotTag);
                itemsList.appendTag(slotTag);
            }

            tagCompound.setTag("Items", itemsList);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scaleVal)
    {
        return furnaceCookTime * scaleVal / 100;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scaleVal)
    {
        if (currentItemBurnTime == 0)
        {
            currentItemBurnTime = cookingTime();
        }

        return furnaceBurnTime * scaleVal / currentItemBurnTime;
    }

    @Override
    public Packet getDescriptionPacket() 
    {
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();

        addToNBT(tag);

        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) 
    {
        super.onDataPacket(net, pkt);
        NBTTagCompound tag = pkt.customParam1;
        loadNBT(tag);
    }
    
    public boolean isBurning()
    {
        return furnaceBurnTime > 0;
    }

    private boolean canSmelt()
    {
        if (furnaceItems[0] == null)
            return false;
        else
        {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(
                    furnaceItems[0]);
            if (itemStack == null)
                return false;
            if (furnaceItems[2] == null)
                return true;
            if (!furnaceItems[2].isItemEqual(itemStack))
                return false;

            int resultingStackSize = furnaceItems[2].stackSize
                    + itemStack.stackSize;
            return resultingStackSize <= getInventoryStackLimit()
                    && resultingStackSize <= itemStack.getMaxStackSize();
        }
    }

    public void smeltItem(int luck)
    {
        if (canSmelt())
        {
            ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(
                    furnaceItems[0]);

            if (furnaceItems[2] == null)
            {
                furnaceItems[2] = itemStack.copy();
                if(furnaceItems[2].itemID == Block.oreRedstone.blockID || 
                	furnaceItems[2].itemID == Block.oreLapis.blockID)
                	furnaceItems[2].stackSize += 3; 
                
                for(ItemStack ore : MagiksArrays.doubledOres)
                {
                	if(furnaceItems[0].isItemEqual(ore))
                	{	if(mistLevel >= 75)
                		{
                			++furnaceItems[2].stackSize;
                			if (luck <= 800 + (20 * upgrades))
                			{
                				++furnaceItems[2].stackSize;
                				mistLevel -= 75;
                			}
                		}
                	}
                }
                
            } else if (furnaceItems[2].isItemEqual(itemStack))
            {
                furnaceItems[2].stackSize += itemStack.stackSize;
                for(ItemStack ore : MagiksArrays.doubledOres)
                {
                	if(furnaceItems[0].isItemEqual(ore))
                	{
                		if(mistLevel >= 75)
                		{
                    		++furnaceItems[2].stackSize;
                			if (luck <= 800 + upgrades * 20)
                			{
                				++furnaceItems[2].stackSize;
                				mistLevel -= 75;
                			}
                		}
                	}
                }
            }

            furnaceItems[0].stackSize--;
            if (furnaceItems[0].stackSize <= 0)
            	furnaceItems[0] = null;
        }
    }

    public void upgradeAmount()
    {
    	for(int i = 3; i < 6; i++)
    	{
    		if (getStackInSlot(i) != null)
            {
    			if (getStackInSlot(i).itemID == ModItemsMagiks.burningUpgrade.itemID)
                    upgrades += getStackInSlot(i).stackSize;
            }
    	}
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		return var1 == 0 ? sidedSlotBottom : var1 == 1 ? sidedSlotTop
                : sidedSlotSides;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if(side == 1)
			return this.isStackValidForSlot(0, itemstack);
		if(side == 0)
			return this.isStackValidForSlot(1, itemstack);
		else
			return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		if(side == this.blockMetadata)
			return true;
		else
			return false;
	}

}
