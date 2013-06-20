package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.TileEntityMagiks;
import net.lomeli.magiks.api.magiks.EnumMachineTypes;
import net.lomeli.magiks.lib.Ints;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraftforge.common.ForgeDirection;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;

public class TileEntityJouleBox extends TileEntityMagiks implements IPowerReceptor
{
	private int mistLevel, maxMistLevel, coolDownTime;
	
	private float heatLevel;
	
	private EnumMachineTypes type;
	
	private IPowerProvider mJouleProvider;
	
	public TileEntityJouleBox()
	{
		type = EnumMachineTypes.GENERATOR;
		maxMistLevel = 600;
		mJouleProvider = PowerFramework.currentFramework.createPowerProvider();
		initPowerProvider();
	}
	
	@Override
	public void updateEntity() 
	{
		super.updateEntity();
		
		if(mJouleProvider.getEnergyStored() > 16)
		{
			if(mistLevel < maxMistLevel)
			{
				float energy = mJouleProvider.useEnergy(16, 32, true);
				int convertedEnergy = Math.round(energy / Ints.mjMistConversionRate);
				mistLevel += convertedEnergy;
				heatLevel += 0.05F;
			}
		}
		
		if(getHeatLevel() >= 100F)
			worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 5F, true);
			
		if(getHeatLevel() > 0)
		{
			coolDownTime++;
			if(coolDownTime >= 25)
			{
				addToHeatLevel(-0.05F);
				coolDownTime = 0;
			}
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) 
	{
		super.readFromNBT(nbttagcompound);
		this.loadFromNBT(nbttagcompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) 
	{
		super.writeToNBT(nbttagcompound);
		this.addToNBT(nbttagcompound);
	}
	
	public void addToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Mist", mistLevel);
		tag.setFloat("Heat", heatLevel);
		PowerFramework.currentFramework.savePowerProvider(this, tag);
	}
	
	public void loadFromNBT(NBTTagCompound tag)
	{
		mistLevel = tag.getInteger("Mist");
		heatLevel = tag.getFloat("Heat");
		PowerFramework.currentFramework.loadPowerProvider(this, tag);
	}
	
	@Override
    public Packet getDescriptionPacket() 
    {
		super.getDescriptionPacket();
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
        loadFromNBT(tag);
    }
	
	private void initPowerProvider() 
	{
		mJouleProvider.configure(20, 8, 64, 16, 300);
	}
	
	@Override
	public float getHeatLevel()
    {
        return heatLevel;
    }

	@Override
    public void setHeatLevel(float temp)
    {
    	heatLevel = temp;
    }

	@Override
    public void addToHeatLevel(float temp)
    {
    	heatLevel += temp;
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
	public int getMistLevel()
    {
		return mistLevel;
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
    public void setPowerProvider(IPowerProvider provider)
    {
		mJouleProvider = provider;
    }

	@Override
    public IPowerProvider getPowerProvider()
    {
	    return mJouleProvider;
    }

	@Override
    public void doWork(){}

	@Override
    public int powerRequest(ForgeDirection from)
    {
		return 16;
    }

}
