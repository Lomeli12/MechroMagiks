package net.lomeli.magiks.tileentity;

import net.lomeli.magiks.api.magiks.IMagiks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagiks extends TileEntity implements IMagiks
{
	public TileEntityMagiks connected;
    public int maxMistLevel, mistLevel, heatLevel, generationTime = 0,
            coolDown = 0, spawnParticle = 0;

    @Override
    public int getMistLevel()
    {
        return this.mistLevel;
    }
    
    public int getMistPercentage()
    {
    	return (this.mistLevel / this.maxMistLevel);
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
    public int getHeatLevel()
    {
        return this.heatLevel;
    }

    @Override
    public void setHeatLevel(int temp)
    {
    	this.mistLevel = temp;
    }

    @Override
    public void addToHeatLevel(int temp)
    {
    	this.mistLevel += temp;
    }

    @Override
    public int getMaxMistLevel()
    {
        return this.maxMistLevel;
    }

    @Override
    public boolean hasMist()
    {
        if (this.getMistLevel() >= 0)
            return true;
        else
            return false;
    }

	@Override
	public boolean isConnected() {
		if(this.connected != null)
				return true;
			else
				return false;
	}

	@Override
	public void setConnection(TileEntityMagiks tileEntity) {
		if(getConnection() == null)
			this.connected = tileEntity;
		else
		{
		}
	}

	@Override
	public TileEntityMagiks getConnection() {
		return this.connected;
	}

}
