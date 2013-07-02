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
	private int mistLevel, maxMistLevel = 600, coolDownTime = 0, tick = 0;
	
	private float heatLevel;
	
	private EnumMachineTypes type;
	
	private IPowerProvider mJouleProvider;
	
	public TileEntityJouleBox()
	{
		type = EnumMachineTypes.GENERATOR;
		mJouleProvider = PowerFramework.currentFramework.createPowerProvider();
		initPowerProvider();
	}
	
	@Override
	public void updateEntity() 
	{
		this.tick++;
		
		if(this.tick == 10)
		{
			System.out.println(this.getMistLevel() + "|" + this.mJouleProvider.getEnergyStored());
			this.tick = 0;
		}
		if(this.getMistLevel() < this.getMaxMistLevel())
		{
			if(this.mJouleProvider.getEnergyStored() > 16)
			{
				float energy = mJouleProvider.useEnergy(16, 32, true);
				int convertedEnergy = Math.round(energy / Ints.mjMistConversionRate);
				this.addToMistLevel(convertedEnergy);
				this.addToHeatLevel(0.05F);
			}
		}
		
		if(this.getHeatLevel() >= 50F)
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 5F, true);
			
		if(this.getHeatLevel() > 0)
		{
			this.coolDownTime++;
			if(this.coolDownTime >= 25)
			{
				this.addToHeatLevel(-0.05F);
				this.coolDownTime = 0;
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
		tag.setInteger("Mist", this.mistLevel);
		tag.setFloat("Heat", this.heatLevel);
		PowerFramework.currentFramework.savePowerProvider(this, tag);
	}
	
	public void loadFromNBT(NBTTagCompound tag)
	{
		this.mistLevel = tag.getInteger("Mist");
		this.heatLevel = tag.getFloat("Heat");
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
		this.mJouleProvider.configure(20, 8, 64, 16, 300);
	}
	
	@Override
	public float getHeatLevel()
    {
        return this.heatLevel;
    }

	@Override
    public void setHeatLevel(float temp)
    {
		this.heatLevel = temp;
    }

	@Override
    public void addToHeatLevel(float temp)
    {
		this.heatLevel += temp;
    }
	
	@Override
	public int getMaxMistLevel()
    {
		return this.maxMistLevel;
    }
	
	@Override
	public EnumMachineTypes getType()
	{
		return this.type;
	}
	
	@Override
	public int getMistLevel()
    {
		return this.mistLevel;
    }
	
	@Override
	public void setMistLevel(int value)
    {
		this.mistLevel = value;
    }

	@Override
    public void addToMistLevel(int value)
    {
		this.mistLevel += value;
    }

	@Override
    public void setPowerProvider(IPowerProvider provider)
    {
		this.mJouleProvider = provider;
    }

	@Override
    public IPowerProvider getPowerProvider()
    {
	    return this.mJouleProvider;
    }

	@Override
    public void doWork(){}

	@Override
    public int powerRequest(ForgeDirection from)
    {
		return 16;
    }

	@Override
	public boolean hasMist()
    {
		if(this.getMistLevel() > 0)
			return true;
		else
			return false;
    }
}
